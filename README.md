# TP1_JAVA_RMI
cours HAI704I Architectures logicielles distribuées - une pratique de Java RMI

La conception de la solution: 
![alt text](https://github.com/Sunying-RONG/Distributed_Architecture_TP1_JAVA_RMI-workspace/blob/main/conception.jpg)

### Un peu d'explication :
1. Stub, squelette, registry (flèches rouges dans le diagramme)  
Serveur crée registry sur le port 1099 par défaut, crée deux objets qui peuvent être distribués : objetCabinet (de classe CabinetImpl) et objetObservable (de classe ObservableImpl), et les bind avec deux noms spécifiques. Client utilise le même port, cherche par les noms binded et reçoit deux stubs d'objets distants (stubICabinet, stubIObservable). Client appelle des méthodes sur les stubs. Squelette reçoit des invocations de méthodes et invoque les méthodes sur l'implémentation de l'objet distant : CabinetImpl et ObservableImpl, et squelette transmet le résultat à Client. Donc, le programme de Client peut invoquer les méthodes des classes sur Serveur.  
Dans la première version, avant la création de Cabinet, objetAnimal (de classe AnimalImpl) est distribué.

2. Project common, Java Build Path - projects (deux flèches bleues dans le diagramme)  
Le project common qui comprend IAnimal, ICabinet, Espece, IObserver, IObservable est ajouté dans (Eclipse) Java Build Path - projects du project server et aussi du projet client. Donc le projet server et le projet client peuvent les trouver. En cas réel, doivent les mettre dans un autre serveur (e.g. accéder par https),et le projet server et le projet client mettent en œuvre codebases pour les accéder.  

3. Cabinet
À côté Serveur, classe CabinetImpl extends UnicastRemoteObject implements interface ICabinet qui extends Remote et possède un attribut cabinet de type ArrayList<IAnimal>. Comme expliqué dans partie 1, Client peut invoquer les méthodes de CabinetImpl pour rechercher des animaux, créer des nouveaux animaux, etc.

4. DossierSuivi
Classe DossierSuivi est sur le côté Serveur avec AnimalImpl. AnimalImpl possède un attribut de type DossierSuivi, Client peut récupérer et modifier dossier suivi par invoquer les méthodes d'AnimalImpl sur stubAnimal, ou après la mise en œuvre de Cabinet, invoquer sur les Animaux de Cabinet. Les Animaux dans Cabinet est de type IAnimal, AnimalImpl implements IAnimal, donc les méthodes par rapport dossier suivi d'AnimalImpl peuvent être invoquées.

5. Espece, codebase
Il y a deux façons de créer un animal avec l'espèce sur côté Client.

   a. Saisie les éléments d'un animal ainsi que nom d'espèce et durée vie moyenne. Dans ce cas, appeler la méthode void createAnimal(String nom, String nomMaitre, String race, String ds, String nomEspece, int dureeVieMoy) sur stubICabinet,

   b. Pour créer les espèces Chat, Chien, Lapin, passer l'objet d'espèce (qui comprend les informations de nom d'espèce et durée vie moyenne) directement dans la méthode void createAnimalE(String nom, String nomMaitre, String race, String ds, Espece esp) appelé sur stubICabinet.  

   À côté client, il y a classes Chat, Chien, Lapin qui tous extends Espece, dans ces trois classes, leurs nom d'espèces et durées vie moyenne sont déjà indiqués. Dans le programme de Client, créer un objet de type Espece par new Chat() par exemple, c'est le terme polymorphisme, pour passer objet d'Espece, il doit être sérialisable et envoyer par copie, donc implements Serializable.   

   À côté serveur, car l'objet passé est un type Chat par exemple, même passé comme type Espece, il faut que serveur connaisse la classe Chat pour compléter l'invocation de la méthode createAnimalE. Donc, le codebase et le gestionnaire de sécurité sont mis en œuvre dans le serveur (un flèches bleues dans le diagramme). Pour être plus sécurité et juste, copier les trois classes dans un autre dossier "codebaseSharedClasses -> tp1_client", du coup, avec le codebase path vers codebaseSharedClasses, le serveur ne peut télécharger que les classes Chat, Chien et Lapin.

6. Observer, Observable, stub (flèches vertes dans le diagramme)
À côté serveur, la classe ObservableImpl extends UnicastRemoteObject implements IObservable qui extends Remote, comme expliqué dans partie 1, objObservable est distribué.
À côté client, la classe ObserverImpl extends UnicastRemoteObject implements IObserver qui extends Remote, donc son objet peut être distribué. Après client reçoit le stubIObservable, client appelle la méthode addObserver sur stubIObservable et passe un objet (new ObserverImpl()) comme paramètre de la méthode addObserver, donc cet objet est distribué, et côté serveur reçoit un stub de cet objet.  
À côté serveur, add cet objet distribué dans un observerList qui est un type ArrayList<IObserver> peut recevoir plusieurs observers pour plusieurs clients. Dans les méthodes createAnimal, createAnimalE, removeAnimal de CabinetImpl, ça veut dire quand ajouter ou supprimer les animaux, vérifier le nombre total des animaux dans le Cabinet, si le nombre est 100, 500, 1000, appeler la méthode update sur chaque stub d'observer, et passer le nombre d'animaux comme paramètre.
Alors, squelette de côté client reçoit des invocations de méthode update et invoque cette méthodes sur l'implémentation de l'objet distant : ObserverImpl, qui est juste afficher une phrase avec le nombre sur le console de client.

### Pour lancer/exécuter le projet:
1. Télécharger le code sur local par git clone ou Download ZIP.  
   - Le nom du dossier est "TP1_JAVA_RMI-workspace". 
   - Si par Download ZIP, unzip le dossier, le nom de dossier peut être "TP1_JAVA_RMI-workspace-main", dans ce cas, tous les "TP1_JAVA_RMI-workspace" doivent être "TP1_JAVA_RMI-workspace-main" dans les étapes suivantes.
2. Lancer Eclipse, choisir le dossier "TP1_JAVA_RMI-workspace" comme le Workspace.
3. Importer les projets.
   - Cliquer sur "import projects" dans Package Explorer. 
   - Puis choisir "General" -> 
   - "Existing Projects into Workspace" ->
   - "Next>" -> 
   - Select root directory, cliquer sur "Browse" -> 
   - Choisir "TP1_JAVA_RMI-workspace" et cliquer sur "Ouvrir" -> 
   - Les trois projets "TP1_JAVA_RMI_client", "TP1_JAVA_RMI_common", "TP1_JAVA_RMI_server" doivent être tous cochés -> 
   - "Finish". 
   - Vous devez voir trois projets dans Package Explorer.
4. Lancer ServerTP.java, qui est dans le projet "TP1_JAVA_RMI_server" -> src -> tp1_server.
   - "ServerCV ready !" doit être affiché et le chemin de codebase path est généré par le code.
5. Lancer ClientTP.java qui est dans le projet "TP1_JAVA_RMI_client" -> src -> tp1_client.
   - À vous de jouer selon les instructions dans le Console.




