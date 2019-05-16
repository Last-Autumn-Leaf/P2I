import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

import javax.swing.JFrame;

public class Fenetre extends JFrame implements MouseListener{
	private Dimension tailleEcran;
	private LinkedList<int[]> coordImpact;
	private Cible cible;
	private int tailleImpact;
	private Objet[][] zones;
	private int zoneImpact;
	
	public Fenetre() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		tailleEcran=java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setTitle("Cible - Projet P2I");
		
		this.setSize((int)(0.5*tailleEcran.getWidth()),(int)(0.5*tailleEcran.getWidth()));
		this.setLocation((int)(tailleEcran.getWidth()*0.2), (int)(tailleEcran.getHeight()*0.02));
		
		cible = new Cible(this.getWidth()/2,this.getHeight()/2); // coordonnees du centre
		cible.setRayon((int)(this.getHeight()*0.45));
		
		zones = new Objet[6][2];
		int hauteurZone = 2*cible.getRayon()/(zones.length+1);
		for(int i=0;i<zones.length;i++) {
			int yZone = cible.getY()-cible.getRayon()+(i+1)*hauteurZone;
			int xDiffCentre = (int) Math.sqrt(Math.pow(cible.getRayon(), 2)-Math.pow(yZone-cible.getY(), 2))+cible.getX();
			
			zones[i][0] = new Objet(cible.getX()-xDiffCentre,yZone);
			zones[i][1] = new Objet(cible.getX()+xDiffCentre,yZone);
		}
		
		coordImpact=new LinkedList<int[]>();
		
		tailleImpact = (int)(this.getWidth()*0.01);
		this.addMouseListener(this);
		this.setVisible(true);
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		
		//affichage des anneaux
		for(int i =cible.getNbAnneau()-1; i>=0;i--) {
			switch(i) {
			case 0:
				g.setColor(Color.YELLOW);
				break;
			case 1:
				g.setColor(Color.RED);
				break;
			case 2:
				g.setColor(Color.BLUE);
				break;
			case 3:
				g.setColor(Color.BLACK);
				break;
				
			case 4: 
				g.setColor(Color.WHITE);
				break;
			}
			g.fillOval(cible.getX()-(i+1)*cible.getRayon()/cible.getNbAnneau(), cible.getY()-(i+1)*cible.getRayon()/cible.getNbAnneau(), 2*(i+1)*cible.getRayon()/cible.getNbAnneau(), 2*(i+1)*cible.getRayon()/cible.getNbAnneau());
		}
		
		//affichage des impacts
		for(int i=0;i<this.coordImpact.size();i++) {
			int xi = this.coordImpact.get(i)[0];
			int yi = this.coordImpact.get(i)[1];
			
			g.setColor(Color.GREEN);
			g.drawLine(xi-tailleImpact, yi-tailleImpact, xi+tailleImpact, yi+tailleImpact);
			g.drawLine(xi+tailleImpact, yi-tailleImpact, xi-tailleImpact, yi+tailleImpact);
			
			// pour mieux voir notamment sur le fond blanc
			g.setColor(Color.BLACK);
			g.drawLine(xi-tailleImpact+1, yi-tailleImpact, xi+tailleImpact+1, yi+tailleImpact);
			g.drawLine(xi+tailleImpact+1, yi-tailleImpact, xi-tailleImpact+1, yi+tailleImpact);
		}
		
		g.setColor(Color.GRAY);
		for(int i=0;i<zones.length;i++) {
			g.drawLine(zones[i][0].getX(), zones[i][0].getY(), zones[i][1].getX(), zones[i][1].getY());
		}
		//g.drawLine(cible.getX()-cible.getRayon(), cible.getY(), cible.getX()+cible.getRayon(), cible.getY());
		//g.drawLine(cible.getX(), cible.getY()-cible.getRayon(), cible.getX(), cible.getY()+cible.getRayon());
	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		int x = e.getX();
		int y = e.getY();
		this.coordImpact.add(new int[2]);
		this.coordImpact.getLast()[0]=x;
		this.coordImpact.getLast()[1]=y;
		//recherche de la zone
		int numLigne=-1;
		
		for(int i=0;i<zones.length;i++) {
			if(zones[i][0].getY()>y) {
				numLigne=i;
				break;
			}
		}
		if(numLigne==-1) {
			zoneImpact=zones.length+1;
		}
		else {
			zoneImpact=numLigne+1;
		}
		
		System.out.println("zone n° : "+zoneImpact);
		repaint();
	}

	public void mouseReleased(MouseEvent e) {
		
	}
	public int getZoneImpact() {
		return this.zoneImpact;
	}

}
