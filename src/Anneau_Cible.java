import java.awt.Color;

public class Anneau_Cible extends Objet{
	private int rayonExt;
	private int rayonInt;
	private Color couleur;
	
	public Anneau_Cible(int xa, int ya,Color c, int ri, int re) {
		super(xa,ya);
		couleur=c;
		rayonInt=ri;
		rayonExt=re;
	}
	
	public int getRInt() {
		return this.rayonInt;
	}
	public int getRExt() {
		return this.rayonExt;
	}
	public Color getCouleur() {
		return this.couleur;
	}

}
