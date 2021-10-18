package tp1_server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

import tp1_common.Espece;
import tp1_common.IAnimal;

public class AnimalImpl extends UnicastRemoteObject implements IAnimal, Serializable {
	private String nom;
	private String nomMaitre;
	private String race;
	private DossierSuivi dossierSuivi;
	private ArrayList<Espece> especes = new ArrayList<>();

	public AnimalImpl() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		this.dossierSuivi = new DossierSuivi();
		especes.add(new Espece(1, "chien", 10));
		especes.add(new Espece(2, "chat", 12));
		especes.add(new Espece(3, "lapin", 8));
		especes.add(new Espece(4, "cheval", 20));
	}
	
	public String helloAnimal() throws RemoteException {
		return "hello animal !";
	}
	
	public void printAnimal() throws RemoteException {
		System.out.println(
				"Nom d'animal : " + this.nom + 
				", Nom du maître : " + this.nomMaitre +
				", Race : " + this.race +
				", Dossier de suivi : " + this.dossierSuivi.toString()
				);
	}
	
	public String getNom() throws RemoteException {
		return this.nom;
	}
	
	public void setNom(String nom) throws RemoteException {
		this.nom = nom;
	}
	
	public String getNomMaitre() throws RemoteException {
		return this.nomMaitre;
	}
	
	public void setNomMaitre(String nomMaitre) throws RemoteException {
		this.nomMaitre = nomMaitre;
	}
	
	public String getRace() {
		return this.race;
	}
	
	public void setRace(String race) throws RemoteException {
		this.race = race;
	}
	
	public String getDossierSuivi() throws RemoteException {
		return this.dossierSuivi.getDS();
	}
	
	public void setDossierSuivi(String ds) throws RemoteException {
		this.dossierSuivi.setDS(ds);
	}
	
	public Espece consultEspece(int espNum) throws RemoteException {
		Espece consultEspece = null;
		for (Espece esp : especes) {
			if (esp.getEspeceNum() == espNum) {
				consultEspece = esp;
			}
		}
		return consultEspece;
	}
	
//	public String toString() {
//		return "test";
//	}

}
