package tp1_server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

import tp1_common.IAnimal;
import tp1_common.ICollectionAnimal;

public class CollectionAnimalImpl extends UnicastRemoteObject implements ICollectionAnimal {
	private ArrayList<IAnimal> collectionAnimal = new ArrayList<>();

	protected CollectionAnimalImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String helloCollection() throws RemoteException {
		return "hello collection d'animal !";
	}
	
	public void ajoutAnimalImpl(IAnimal animalImpl) throws RemoteException {
		collectionAnimal.add(animalImpl);
	}

	public IAnimal rechercheAnimal(String rechercheNom) throws RemoteException {
		IAnimal rechercheAnim = null;
		for (IAnimal animal : collectionAnimal) {
			if (animal.getNom().equals(rechercheNom)) {
				rechercheAnim = animal;
			}
		}
		return rechercheAnim;
	}

	public String consulterNoms() throws RemoteException {
		String nomCollection = "";
		for (IAnimal animal : collectionAnimal) {
			nomCollection += animal.getNom() + "| ";
		}
		return nomCollection;
	}
	
	
	

}
