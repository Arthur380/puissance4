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
		System.out.print("\nPetite aide \n" + aideHumain.ouJouer(this, grille)+"\n");
		System.out.print("Où placer le prochain jeton? Donnez un numéro de colonne entre 1 et " + Grille.LARGEUR + " : ");

		Scanner sc = new Scanner(System.in);
		int i = sc.nextInt()-1;
		
		return i;
	}



}
