/**
 * 
 */
package P4;


/**
 * Cette heuristique note une grille pour savoir qu'elle son potentiel de gain pour le joueur donné
 * @author Ronan Quintin
 *
 */
public abstract class Heuristique {

	
	public static final int MAX_NOTE = Integer.MAX_VALUE;
	
	public static final int MIN_NOTE = Integer.MIN_VALUE;
	
	
	/**
	 * Note la grille donné pour le joueur donné
	 * @param grille La grille à noter
	 * @param joueur Le joueur dont on cherche à donner savoir si il va gagner
	 * @return une note comprose entre min int et max int
	 */
	public abstract double noteGrille(Grille grille, Joueur joueur);
}
