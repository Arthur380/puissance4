/**
 * 
 */
package P4;



/**
 * Cette heuristique aleatoire note les grille au hasard
 * @author Ronan Quintin
 *
 */
public class HeuristiqueAleatoire extends Heuristique {

	/* (non-Javadoc)
	 * @see org.ronan.puissance4.alphabeta.Heuristique#noteGrille(org.ronan.puissance4.modele.jeu.Grille, org.ronan.puissance4.modele.joueur.Joueur)
	 */
	@Override
	public double noteGrille(Grille grille, Joueur joueur) {
		return  Math.random();
	}

}
