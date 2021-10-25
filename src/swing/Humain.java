/**
 * 
 */
package swing;

import java.util.Scanner;

// joueur humain (les meilleurs ! sauf si l'ia gagne...
public class Humain extends JoueurAbstrait {
	private AlgoAlphaBeta aideHumain;
	public Humain(char symbole, int lvl) {
		
		super(symbole, lvl);
		this.aideHumain = new AlgoAlphaBeta(lvl);
	}
	

	/* (non-Javadoc)
	 * @see org.ronan.puissance4.modele.joueur.Joueur#placerJeton(org.ronan.puissance4.modele.jeu.Grille)
	 */
	@Override
	public int placerChar(Grille grille,JoueurAbstrait joueur, JoueurAbstrait opposant) {
		System.out.print("Où placer le prochain jeton? Donnez un numéro de colonne entre 1 et " + Grille.LARGEUR + " : ");
		System.out.print("Petite aide " + aideHumain.ouJouer(joueur, grille));
		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt();


		return i;
	}



}
