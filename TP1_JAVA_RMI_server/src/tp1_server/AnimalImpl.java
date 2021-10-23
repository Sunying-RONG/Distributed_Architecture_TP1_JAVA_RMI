package tp1_server;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

import tp1_common.Espece;
import tp1_common.IAnimal;

public class AnimalImpl extends UnicastRemoteObject implements IAnimal {
	private String nom;
	private String nomMaitre;
	private String race;
	private DossierSuivi dossierSuivi;
	private String nomEspece;
	private int dureeVieMoy;
	private Espece esp;
	
	public AnimalImpl(String nom, String nomMaitre, String race, String ds, String nomEspece, int dureeVieMoy) throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		this.dossierSuivi = new DossierSuivi();
		this.setNom(nom);
		this.setNomMaitre(nomMaitre);
		this.setRace(race);
		this.setDossierSuivi(ds);
		this.setNomEspece(nomEspece);
		this.setDureeVieMoy(dureeVieMoy);
	}
	
	public AnimalImpl(String nom, String nomMaitre, String race, String ds, Espece esp) throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		this.dossierSuivi = new DossierSuivi();
		this.setNom(nom);
		this.setNomMaitre(nomMaitre);
		this.setRace(race);
		this.setDossierSuivi(ds);
		this.esp = esp;
		this.nomEspece = this.esp.getNomEspece();
		this.dureeVieMoy = this.esp.getDureeVieMoy();
	}
	
	public String helloAnimal() throws RemoteException {
		return "hello animal !";
	}
	
//	public void printAnimal() throws RemoteException {
//		System.out.println(
//				"Nom d'animal : " + this.nom + 
//				", Nom du maître : " + this.nomMaitre +
//				", Race : " + this.race +
//				", Dossier de suivi : " + this.dossierSuivi.toString()
//				);
//	}
	
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
	
	public String getNomEspece() throws RemoteException {
		return this.nomEspece;
	}
	
	public void setNomEspece(String nomEspece) throws RemoteException {
		this.nomEspece = nomEspece;
	}
	
	public int getDureeVieMoy() throws RemoteException {
		return this.dureeVieMoy;
	}
	
	public void setDureeVieMoy(int dureeVieMoy) throws RemoteException {
		this.dureeVieMoy = dureeVieMoy;
	}

}
