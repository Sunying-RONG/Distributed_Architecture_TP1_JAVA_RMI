package tp1_client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import tp1_common.Espece;
import tp1_common.IAnimal;
import tp1_common.ICabinet;
import tp1_common.IObservable;

public class ClientTP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String host = (args.length < 1) ? null : args[0];
		try {
			Registry registry = LocateRegistry.getRegistry(host);
//			IAnimal stubIAnimal = (IAnimal) registry.lookup("animal");
			ICabinet stubICabinet = (ICabinet) registry.lookup("cabinet");
//			String responseA = stubIAnimal.helloAnimal();
//			System.out.println("Response animal : " + responseA);
//			stubIAnimal.printAnimal();
			String responseCA = stubICabinet.helloCabinet();
			System.out.println("Reponse cabinet : " + responseCA);
			
			IObservable stubIObservable = (IObservable) registry.lookup("observable");
			stubIObservable.addObserver(new ObserverImpl());
			
			Espece chat = new Chat("chat", 10);
			Espece chien = new Chien("chien", 15);
			Espece lapin = new Lapin("lapin", 8);

			Scanner sc = new Scanner(System.in).useDelimiter("\n");
			int input = -1;
			do {
				// toujours afficher si input != 0
				System.out.println(
						"Entrer 1 ou 2 ou 3 ou 4 ou 5 ou 6 ou 7 ou 8 ou 9 ou 0 pour : \n"+
						"1 Consulter tous les noms d'animaux.\n"+
						"2 Consulter tous les informations de tous les animaux.\n"+
						"3 Créer un nouveau animal (patient), choisir ou décrire son espèce.\n"+
						"4 Rechercher d'animal (patient) par nom et pour modifier son dossier suivi ou pour le supprimer.\n"+
						"5 Simuler l'augmentation de nombre de patients jusqu'à 100.\n"+
						"6 Simuler l'augmentation de nombre de patients jusqu'à 500.\n"+
						"7 Simuler l'augmentation de nombre de patients jusqu'à 1000.\n"+
						"8 Supprimer tous les patients.\n"+
						"9 Acquérir le nombre d'animaux dans le cabinet.\n"+
						"0 Quitter."
				);
				
				try {
					input = sc.nextInt();
				} catch (Exception e) {
					System.out.println("Saisie non valide ! Relancer le programme pour recommencer.");
					e.printStackTrace();
					break;
				}
				
				// 1 Consulter tous les noms d'animaux.
				if (input == 1) {
					System.out.println("Tous les animaux (patients) existants : \n" + stubICabinet.consulterNoms());
				}
				
				// 2 Consulter tous les informations de tous les animaux.
				if (input == 2) {
					System.out.println("Tous les patients existants : \n" + stubICabinet.consulterTous());
				}
				
				// 3 Créer un nouveau animal (patient).
				if (input == 3) {
					System.out.println("Entrer le nom d'animal : ");
					String nom = sc.next();
					if (stubICabinet.rechercheAnimal(nom) == null) {
						System.out.println("Entrer le nom Maître d'animal : ");
						String nomMaitre = sc.next();
						System.out.println("Entrer la race d'animal : ");
						String race = sc.next();
						System.out.println("Entrer le dossier suivi : ");
						String ds = sc.next();
						
						System.out.println(
								"Entrer l'espèce d'animal ('chat' ou 'chien' ou 'lapin' ou 'autre') : \n" +
								"Si saisir 'chat' ou 'chien' ou 'lapin', PAS besoin de saisir 'nom d'espèce' ou 'durée de vie moyenne'. \n" +
								"Si saisir 'autre', veuillez saisir 'nom d'espèce' et 'durée de vie moyenne' après.");
						String inputEspece = sc.next();
						if (inputEspece.equals("chat")) {
							stubICabinet.createAnimalE(nom, nomMaitre, race, ds, chat);
							System.out.println("Animal (chat) crée. \n");
						} else if (inputEspece.equals("chien")) {
							stubICabinet.createAnimalE(nom, nomMaitre, race, ds, chien);
							System.out.println("Animal (chien) crée. \n");
						} else if (inputEspece.equals("lapin")) {
							stubICabinet.createAnimalE(nom, nomMaitre, race, ds, lapin);
							System.out.println("Animal (lapin) crée. \n");
						} else if (inputEspece.equals("autre")) {
							System.out.println("Entrer l'espèce d'animal : ");
							String nomEspece = sc.next();
							System.out.println("Entrer la durée moyenne de cette espèce : ");
							int dureeVieMoy = sc.nextInt();
							stubICabinet.createAnimal(nom, nomMaitre, race, ds, nomEspece, dureeVieMoy);
							System.out.println("Animal crée. \n");
						} else {
							System.out.println("Saisi non valable, animal n'est pas crée. \n");
						}
					} else {
						System.out.println("Nom d'animal déjà existe, ne peut pas être crée.\n");
					}
				}

				// 4 Rechercher d'animal (patient) par nom.
				if (input == 4) {
					System.out.println("Entrer le nom d'animal pour la recherche : ");
					String nom = sc.next();
					IAnimal rechercheAnim = stubICabinet.rechercheAnimal(nom);
					if (rechercheAnim == null) {
						System.out.println("Animal n'existe pas ! \n");
					} else {
						System.out.println(stubICabinet.infoAnimal(rechercheAnim));
						// modifier son dossier suivi
						System.out.println("Modifier le dossier suivi de cet animal ? (y/n)");
						if (sc.next().charAt(0) == 'y') {
							System.out.println("Entrer le nouveau dossier de suivi : ");
							String ds = sc.next();
							rechercheAnim.setDossierSuivi(ds);
						}
						System.out.println("Supprimer cet animal ? (y/n)");
						if (sc.next().charAt(0) == 'y') {
							stubICabinet.removeAnimal(nom);
							System.out.println("Animal supprimé.\n");
						}
					}
				}
				
				// 5 Simuler l'augmentation de nombre de patients jusqu'à 100.
				if (input == 5) {
					stubICabinet.removeAll();
					System.out.println("Clear cabinet.");
					for (int i=0; i<100; i++) {
						System.out.println("Un patient ajouté.");
						stubICabinet.createAnimalE(""+i, "nomMaitre", "race", "ds", chat);
					}
				}
				
				// 6 Simuler l'augmentation de nombre de patients jusqu'à 500.
				if (input == 6) {
					stubICabinet.removeAll();
					System.out.println("Clear cabinet.");
					for (int i=0; i<500; i++) {
						System.out.println("Un patient ajouté.");
						stubICabinet.createAnimalE(""+i, "nomMaitre", "race", "ds", chat);
					}
				}
				
				// 7 Simuler l'augmentation de nombre de patients jusqu'à 1000.
				if (input == 7) {
					stubICabinet.removeAll();
					System.out.println("Clear cabinet.");
					for (int i=0; i<1000; i++) {
						System.out.println("Un patient ajouté.");
						stubICabinet.createAnimalE(""+i, "nomMaitre", "race", "ds", chat);
					}
				}
				
				// 8 Supprimer tous les patients.
				if (input == 8) {
					stubICabinet.removeAll();
				}
				
				// 9 Acquérir le nombre d'animaux dans le cabinet.
				if (input == 9) {
					System.out.println("Il y a "+stubICabinet.cabinetSize()+" patients dans le cabinet.\n");
				}
			
				// 0 Quitter le programme
				if (input == 0) {
					System.out.println("Quitter le programme. Relancer le programme pour recommencer.");
					sc.close();
				}
			} while (input != 0);
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}

}
