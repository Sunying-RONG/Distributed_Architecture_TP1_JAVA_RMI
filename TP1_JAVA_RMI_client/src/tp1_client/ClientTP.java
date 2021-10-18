package tp1_client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import tp1_common.Espece;
import tp1_common.IAnimal;
import tp1_common.ICollectionAnimal;

public class ClientTP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String host = (args.length < 1) ? null : args[0];
		try {
			Registry registry = LocateRegistry.getRegistry(host);
			IAnimal stubIAnimal = (IAnimal) registry.lookup("animal");
			ICollectionAnimal stubICollection = (ICollectionAnimal) registry.lookup("collection");
			
			String responseA = stubIAnimal.helloAnimal();
			System.out.println("Response animal : " + responseA);
//			stubIAnimal.printAnimal();
			
			String responseCA = stubICollection.helloCollection();
			System.out.println("Reponse collection : " + responseCA);
			
			Scanner sc = new Scanner(System.in).useDelimiter("\n");
			// consulter collection d'animal (patients existants)
			System.out.println("Consulter les noms de la collection d'animal (patients existants) ? (y/n)");
			if (sc.next().charAt(0) == 'y') {
				System.out.println("Tous les patients existants : " + stubICollection.consulterNoms());
			}
			
			// créer un nouveau animal(patient), entrer les infos et l'ajouter dans la collection
			System.out.println("Créer un nouveau patient ? (y/n)");
			if (sc.next().charAt(0) == 'y') {
				System.out.println("Entrer le nom d'animal : ");
				stubIAnimal.setNom(sc.next());
				System.out.println("Entrer le nom Maître d'animal : ");
				stubIAnimal.setNomMaitre(sc.next());
				System.out.println("Entrer la race d'animal : ");
				stubIAnimal.setRace(sc.next());
				System.out.println("Entrer le dossier suivi : ");
				stubIAnimal.setDossierSuivi(sc.next());
				stubICollection.ajoutAnimalImpl(stubIAnimal);
				System.out.println("Fin de saisi, animal crée.");
				System.out.println("Tous les patients existants : " + stubICollection.consulterNoms());
			};
			
			// recherche d'animal par nom
			System.out.println("Rechercher d'animal (ancient patient) par nom ? (y/n)");
			if (sc.next().charAt(0) == 'y') {
				System.out.println("Entrer le nom d'animal pour la recherche : ");
				IAnimal rechercheAnim = stubICollection.rechercheAnimal(sc.next());
				if (rechercheAnim != null) {
					System.out.println(
							"Nom d'animal : " + rechercheAnim.getNom() + 
							", Nom du maître : " + rechercheAnim.getNomMaitre() +
							", Race : " + rechercheAnim.getRace() +
							", Dossier de suivi : " + rechercheAnim.getDossierSuivi());
				} else {
					System.out.println("Animal n'existe pas !");
				}
				
				// consulter espèce par numéro d'espèce 1,2,3,4
				System.out.println("Consulter l'espèce d'animal par numéro d'espèce ? (y/n)");
				if (sc.next().charAt(0) == 'y') {
					System.out.println("Entrer numéro d'espèce d'animal pour consulter : "
							+ "1 : chien. 2 : chat. 3 : lapin. 4 : cheval.");
					int espNum = sc.nextInt();
					Espece esp = stubIAnimal.consultEspece(espNum); // esp est un objet passé de Server à client, faut sérialisé
					System.out.println("D'après numéro d'espèce saisi : " + esp.toString());
				}
				
				// get, modifier dossier suivi
				System.out.println("Modifier le dossier suivi d'animal " + " ? (y/n)");
				char mds = sc.next().charAt(0);
				if (mds == 'y') {
					System.out.println("L'ancien dossier suivui : " + stubIAnimal.getDossierSuivi());
					System.out.println("Entrer le nouveau dossier de suivi : ");
					String ds = sc.next();
					stubIAnimal.setDossierSuivi(ds);
					System.out.println("Vous avez saisi nouveau dossier suivi : " + stubIAnimal.getDossierSuivi());
				}
			}
			
			sc.close();
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}

}
