package tp1_common;

import java.io.Serializable;

public class Espece implements Serializable {
	private int especeNum;
	private String nomEspece;
	private int dureeVieMoy;
	
	public Espece(int especeNum, String nomEspece, int dureeVieMoy) {
		this.especeNum = especeNum;
		this.nomEspece = nomEspece;
		this.dureeVieMoy = dureeVieMoy;
	}
	
	public int getEspeceNum() {
		return this.especeNum;
	}
	
	public String getNomEspece() {
		return this.nomEspece;
	}
	
	public String toString() {
		return "Espèce est : " + this.nomEspece + "; Durée de la vie moyenne est : " + this.dureeVieMoy;
	}
}
