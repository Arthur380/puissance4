/**
 * 
 */
package P4;



/**
 * Ce joeur artificiel utilise l'algorithme minmax et l'heuristique stupide pour déterminer qu'elle colonne
 * jouer 
 * @author Ronan Quintin
 *
 */
public class JoueurMinMax extends Joueur {

	private MinMax minMax;
	
	public JoueurMinMax(Couleur couleur, Heuristique heuristique) {
		super(couleur);
		this.minMax = new MinMax(heuristique);
	}

	/* (non-Javadoc)
	 * @see org.ronan.puissance4.modele.joueur.Joueur#placerJeton(org.ronan.puissance4.modele.jeu.Grille)
	 */
	@Override
	public int placerJeton(Grille grille, Joueur joueur, Joueur opposant) {
		return minMax.calculeColonneAJouer(grille, joueur);
	}

}
