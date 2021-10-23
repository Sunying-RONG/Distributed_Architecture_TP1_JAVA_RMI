package tp1_common;

import java.io.Serializable;

public class Espece implements Serializable {
	private String nomEspece;
	private int dureeVieMoy;
	
//	public Espece() {}
	
	public Espece(String nomEspece, int dureeVieMoy) {
		this.nomEspece = nomEspece;
		this.dureeVieMoy = dureeVieMoy;
	}
	
	public String getNomEspece() {
		return this.nomEspece;
	}
	
	public int getDureeVieMoy() {
		return this.dureeVieMoy;
	}
	
	public String toString() {
		return "Espèce est : " + this.nomEspece + "; Durée de la vie moyenne est : " + this.dureeVieMoy;
	}
}
