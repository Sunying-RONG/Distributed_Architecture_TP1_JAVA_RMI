package tp1_common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICollectionAnimal extends Remote {

	String helloCollection() throws RemoteException;

	void ajoutAnimalImpl(IAnimal stubIAnimal) throws RemoteException;

	IAnimal rechercheAnimal(String rechercheNom) throws RemoteException;

	String consulterNoms() throws RemoteException;

}

