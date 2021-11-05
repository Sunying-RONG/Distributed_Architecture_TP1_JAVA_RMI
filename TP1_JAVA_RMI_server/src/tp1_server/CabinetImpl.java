package tp1_server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

import tp1_common.Espece;
import tp1_common.IAnimal;
import tp1_common.ICabinet;

public class CabinetImpl extends UnicastRemoteObject implements ICabinet {
	private ArrayList<IAnimal> cabinet = new ArrayList<>();

	protected CabinetImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		IAnimal defaultAnimal = new AnimalImpl("Titi", "Pierre", "Bleu russe", "premier traitement", 
			"chat", 10);
		this.cabinet.add(defaultAnimal);
	}
	
	@Override
	public String helloCabinet() throws RemoteException {
		return "hello cabinet d'animal !";
	}
	
	@Override
	public int cabinetSize() throws RemoteException {
		return this.cabinet.size();
	}
	
	@Override
	public void createAnimal(String nom, String nomMaitre, String race, String ds, String nomEspece, int dureeVieMoy) throws RemoteException {
		IAnimal animal = new AnimalImpl(nom, nomMaitre, race, ds, nomEspece, dureeVieMoy);
		this.cabinet.add(animal);
		if (this.cabinetSize() == 100 || this.cabinetSize() == 500 || this.cabinetSize() == 1000) {
	        ObserverUpdater.numberChanged(this.cabinetSize());
		}
	}
	
	@Override
	public void createAnimalE(String nom, String nomMaitre, String race, String ds, Espece esp) throws RemoteException {
		IAnimal animal = new AnimalImpl(nom, nomMaitre, race, ds, esp);
		this.cabinet.add(animal);
		if (this.cabinetSize() == 100 || this.cabinetSize() == 500 || this.cabinetSize() == 1000) {
	        ObserverUpdater.numberChanged(this.cabinetSize());
		}
	}
	
	@Override
	public void removeAnimal(String nom) throws RemoteException {
		for (int i=0; i<this.cabinetSize(); i++) {
			if (this.cabinet.get(i).getNom().equals(nom)) {
				this.cabinet.remove(i);
			}
		}
		if (this.cabinetSize() == 100 || this.cabinetSize() == 500 || this.cabinetSize() == 1000) {
	        ObserverUpdater.numberChanged(this.cabinetSize());
		}
	}
	
	@Override
	public void removeAll() throws RemoteException {
		this.cabinet.removeAll(this.cabinet);
	}

	@Override
	public IAnimal rechercheAnimal(String rechercheNom) throws RemoteException {
		IAnimal rechercheAnim = null;
		for (IAnimal animal : this.cabinet) {
			if (animal.getNom().equals(rechercheNom)) {
				rechercheAnim = animal;
			}
		}
		return rechercheAnim;
	}
	
	@Override
	public String infoAnimal(IAnimal animal) throws RemoteException {
		return "Nom d'animal : " + animal.getNom() + 
				", Nom du maître : " + animal.getNomMaitre() +
				", Race : " + animal.getRace() +
				", Dossier de suivi : " + animal.getDossierSuivi() +
				", Espece : " + animal.getNomEspece() +
				", Durée de la vie : " + animal.getDureeVieMoy();
	}
	
	@Override
	public String consulterTous() throws RemoteException {
		String tousInfo = "";
		for (IAnimal animal : this.cabinet) {
			tousInfo += this.infoAnimal(animal) + "\n";
		}
		return tousInfo;
	}
	
	@Override
	public String consulterNoms() throws RemoteException {
		String nomCabinet = "";
		for (IAnimal animal : this.cabinet) {
			nomCabinet += animal.getNom() + "\n";
		}
		return nomCabinet;
	}

}
