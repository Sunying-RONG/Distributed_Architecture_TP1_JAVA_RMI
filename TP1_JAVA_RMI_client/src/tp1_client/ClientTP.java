package tp1_client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

import tp1_common.Espece;
import tp1_common.IAnimal;
import tp1_common.ICabinet;

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
			Scanner sc = new Scanner(System.in).useDelimiter("\n");
			int input = 0;
			while (input != 5) {
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
						Espece chat = (Espece) new Chat("chat", 10);
						stubICabinet.createAnimalE(nom, nomMaitre, race, ds, chat);
						//!!!! verifier si nom existe
						System.out.println("Animal (chat) crée. \n");
					} else if (inputEspece.equals("chien")) {
						Espece chien = (Espece) new Chien("chien", 15);
						stubICabinet.createAnimalE(nom, nomMaitre, race, ds, chien);
						System.out.println("Animal (chien) crée. \n");
					} else if (inputEspece.equals("lapin")) {
						Espece lapin = (Espece) new Lapin("lapin", 8);
						System.out.println(lapin.toString());
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
				}

				// 4 Rechercher d'animal (patient) par nom.
				if (input == 4) {
					System.out.println("Entrer le nom d'animal pour la recherche : ");
					IAnimal rechercheAnim = stubICabinet.rechercheAnimal(sc.next());
					if (rechercheAnim == null) {
						System.out.println("Animal n'existe pas ! \n");
					} else {
						System.out.println(stubICabinet.infoAnimal(rechercheAnim));
						// 4 modifier son dossier suivi
						System.out.println("Modifier le dossier suivi de cet animal " + " ? (y/n)");
						if (sc.next().charAt(0) == 'y') {
							System.out.println("Entrer le nouveau dossier de suivi : ");
							String ds = sc.next();
							rechercheAnim.setDossierSuivi(ds);
						}
					}
				}
				// toujours afficher si input != 5
				System.out.println(
						"Entrer 1 ou 2 ou 3 ou 4 ou 5 pour : \n"+
						"1 Consulter tous les noms d'animaux.\n"+
						"2 Consulter tous les informations de tous les animaux.\n"+
						"3 Créer un nouveau animal (patient), choisir ou décrire son espèce.\n"+
						"4 Rechercher d'animal (patient) par nom et pour modifier son dossier suivi.\n"+
						"5 Quitter."
				);
				input = sc.nextInt();
				// 5 Quitter le programme
				if (input ==5) {
					System.out.println("Quitter le programme. Relancer le programme pour recommencer.");
					sc.close();
				}
			} 
		} catch (Exception e) {
			System.err.println("Client exception: " + e.toString());
			e.printStackTrace();
		}
	}

}
