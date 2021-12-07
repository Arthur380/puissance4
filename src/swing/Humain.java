/**
 * Partie de gestion d'un joueur Humain
 */
package swing;

public class Humain extends JoueurAbstrait {
	//
	private AlgoAlphaBeta aideHumain;
	
	/**
	 * Constructeur de Humain
	 * @param symbole X ou O attribu� au joueur
	 * @param lvl niveau d'aide allant de 1 (peu d'aide) � 4 (beaucoup d'aide)
	 */
	public Humain(char symbole, int lvl) {		
		super(symbole, lvl);
		this.aideHumain = new AlgoAlphaBeta(lvl);
	}
	
	/**
	 * Place un jeton dans la grille avec une aide calcul�e gr�ce au poids 
	 * @param grille grille de jeu actuelle o� sera plac� le jeton
	 */
	@Override
	public int placerChar(Grille grille) {
		System.out.print("\n Petite aide " + (aideHumain.ouJouer(this, grille)+1)+"\n");
		System.out.print("\n O� placer le prochain jeton ? "+this.getSymbole()+" Donnez un num�ro de colonne entre 1 et " + Grille.LARGEUR + " : ");	
		return 0;
	}
}
