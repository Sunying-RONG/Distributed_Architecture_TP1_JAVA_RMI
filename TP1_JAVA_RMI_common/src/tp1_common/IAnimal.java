package tp1_common;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IAnimal extends Remote {
	String helloAnimal() throws RemoteException;
	
	void printAnimal() throws RemoteException;
	
	String getNom() throws RemoteException;
	
	void setNom(String next) throws RemoteException;
	
	String getNomMaitre() throws RemoteException;
	
	void setNomMaitre(String next) throws RemoteException;
	
	String getRace() throws RemoteException;
	
	void setRace(String next) throws RemoteException;
	
	String getDossierSuivi() throws RemoteException;
	
	void setDossierSuivi(String ds) throws RemoteException;
	
//	void setEspece(Espece espece) throws RemoteException;
	Espece consultEspece(int espNum) throws RemoteException;
	
//	String toString() throws RemoteException;
}
