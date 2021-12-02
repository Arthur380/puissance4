/**
 * 
 */
package swing;

import java.util.Scanner;

// joueur humain (les meilleurs ! sauf si L'IA gagne...
public class Humain extends JoueurAbstrait {
	private AlgoAlphaBeta aideHumain;
	public Humain(char symbole, int lvl) {		
		super(symbole, lvl);
		this.aideHumain = new AlgoAlphaBeta(lvl);
	}
	


	@Override
	public int placerChar(Grille grille) {
		System.out.print("\n Petite aide " + (aideHumain.ouJouer(this, grille)+1)+"\n");
		//grille.affichePoids();
		System.out.print("\n Où placer le prochain pion? "+this.getSymbole()+" Donnez un numéro de colonne entre 1 et " + Grille.LARGEUR + " : ");

		//Scanner sc = new Scanner(System.in);
	//	int i = sc.nextInt()-1;		
		int i=0;
		return i;
	}

//kk

}
