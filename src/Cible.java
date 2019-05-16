import java.util.ArrayList;

public class Cible extends Objet{
	private ArrayList<Anneau_Cible> anneaux;
	private final int NB_ANNEAUX = 5;
	private int rayonCible;
	
	public Cible(int xc, int yc) {
		super(xc,yc);
	}
	
	public void ajoutAnneau(Anneau_Cible a) {
		this.anneaux.add(a);
	}
	
	public Anneau_Cible getAnneau(int i) {
		return this.anneaux.get(i);
	}
	
	public int getRayon() {
		return this.rayonCible;
	}
	public void setRayon(int r) {
		this.rayonCible=r;
	}
	public int getNbAnneau() {
		return this.NB_ANNEAUX;
	}
}
