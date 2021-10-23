package tp1_common;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Scanner;

public interface ICabinet extends Remote {

	String helloCabinet() throws RemoteException;

	IAnimal rechercheAnimal(String rechercheNom) throws RemoteException;

	String consulterNoms() throws RemoteException;

	void createAnimal(String nom, String nomMaitre, String race, String ds, String nomEspece, int dureeVieMoy) throws RemoteException;
	
	void createAnimalE(String nom, String nomMaitre, String race, String ds, Espece esp) throws RemoteException; 
	
	String infoAnimal(IAnimal animal) throws RemoteException;
	
	String consulterTous() throws RemoteException;

}

