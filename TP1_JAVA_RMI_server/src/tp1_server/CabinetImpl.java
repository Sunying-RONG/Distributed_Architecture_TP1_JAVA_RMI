package tp1_server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

import tp1_common.Espece;
import tp1_common.IAnimal;
import tp1_common.ICabinet;

public class CabinetImpl extends UnicastRemoteObject implements ICabinet {
	public static ArrayList<IAnimal> cabinet = new ArrayList<>();

	protected CabinetImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		IAnimal defaultAnimal = new AnimalImpl("Titi", "Pierre", "Bleu russe", "premier traitement", 
			"chat", 10);
		cabinet.add(defaultAnimal);
	}
	
	public String helloCabinet() throws RemoteException {
		return "hello cabinet d'animal !";
	}
	
	public void createAnimal(String nom, String nomMaitre, String race, String ds, String nomEspece, int dureeVieMoy) throws RemoteException {
		IAnimal animal = new AnimalImpl(nom, nomMaitre, race, ds, nomEspece, dureeVieMoy);
		cabinet.add(animal);
        ObserverUpdater.update(CabinetImpl.cabinet.size());
	}
	
	public void createAnimalE(String nom, String nomMaitre, String race, String ds, Espece esp) throws RemoteException {
		IAnimal animal = new AnimalImpl(nom, nomMaitre, race, ds, esp);
		cabinet.add(animal);
        ObserverUpdater.update(CabinetImpl.cabinet.size());
	}

	public IAnimal rechercheAnimal(String rechercheNom) throws RemoteException {
		IAnimal rechercheAnim = null;
		for (IAnimal animal : cabinet) {
			if (animal.getNom().equals(rechercheNom)) {
				rechercheAnim = animal;
			}
		}
		return rechercheAnim;
	}
	
	public String infoAnimal(IAnimal animal) throws RemoteException {
		return "Nom d'animal : " + animal.getNom() + 
				", Nom du maître : " + animal.getNomMaitre() +
				", Race : " + animal.getRace() +
				", Dossier de suivi : " + animal.getDossierSuivi() +
				", Espece : " + animal.getNomEspece() +
				", Durée de la vie : " + animal.getDureeVieMoy();
	}
	
	public String consulterTous() throws RemoteException {
		String tousInfo = "";
		for (IAnimal animal : cabinet) {
			tousInfo += this.infoAnimal(animal) + "\n";
		}
		return tousInfo;
	}

	public String consulterNoms() throws RemoteException {
		String nomCabinet = "";
		for (IAnimal animal : cabinet) {
			nomCabinet += animal.getNom() + "\n";
		}
		return nomCabinet;
	}

}
