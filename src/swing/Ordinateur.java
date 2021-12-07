/**
 * Partie de gestion d'un joueur Ordinateur et de son IA
 */

package swing;

public class Ordinateur extends JoueurAbstrait {

	private AlgoAlphaBeta IA;
	
	/**
	 * Constructeur Ordinateur
	 * @param symbole X ou O attribué au joueur
	 * @param lvl niveau d'aide allant de 1 (peu d'aide) à 4 (beaucoup d'aide)
	 */
	public Ordinateur(char symbole, int niveau) {
		super(symbole,niveau);
		this.IA = new AlgoAlphaBeta(niveau);
	}

	/**
	 * Permet de faire jouer l'IA sur la grille
	 * @param grille grille de jeu actuelle où sera placé le jeton
	 */
	@Override
	public int placerChar(Grille grille)  {
		int j = IA.ouJouer(this, grille);
		System.out.print("\n IA joue "+this.getSymbole()+" en " + (j+1) +"\n");
		return j;
	}

}
