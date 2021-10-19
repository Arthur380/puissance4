/**
 * 
 */
package P4;


/**
 * Ce joeur artificiel utilise l'algorithme alpha-beta et avec l'heuristique donnée pour déterminer qu'elle colonne
 * jouer 
 * @author Ronan Quintin
 *
 */
public class JoueurAlphaBeta extends Joueur {

	private AlphaBeta alphaBeta;
	
	public JoueurAlphaBeta(Couleur couleur, Heuristique heuristique) {
		super(couleur);
		this.alphaBeta = new AlphaBeta(heuristique);
	}

	/* (non-Javadoc)
	 * @see org.ronan.puissance4.modele.joueur.Joueur#placerJeton(org.ronan.puissance4.modele.jeu.Grille)
	 */
	@Override
	public int placerJeton(Grille grille, Joueur joueur, Joueur opposant) {
		return alphaBeta.calculeColonneAJouer(grille, joueur);
	}

}
