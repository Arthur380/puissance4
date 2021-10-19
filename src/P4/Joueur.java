/**
 * 
 */
package P4;



/**
 * Cette classe abstraite définie l'ensemble des opérations que faire un joueur. Afin d'éviter d'éventuelles tricheries un joueur ne 
 *  peut pas insérer directement un jeton dans la grille mais demande à la partie de le faire à sa place. A chaque joueur est donné une couleur.
 * @author Ronan Quintin
 *
 */
public abstract class Joueur {
	
	
	/**
	 * La couleur du joueur
	 */
	private Couleur couleur;
	
	/**
	 * Le nom du joeur
	 */
	private String nom;
	
	/**
	 * Initialise le joueur avec la couleur donnée
	 * @param couleur La couleur donnée
	 */
	public Joueur(Couleur couleur) {
		this.couleur = couleur;
	}
	
	/**
	 * Retourne la couleur du joueur
	 * @return
	 */
	public Couleur getCouleur() {
		return couleur;
	}
	
	
	/**
	 * @return the nomJoeur
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * @param nom the nomJoeur to set
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * Le joueur analyse la grille immutable et choisis dans qu'elle colonne placer son prochain jeton.
	 * @param grilleLa grille de jeu courrante
	 * @return Le numéro de la colonne dans lequel insérer le prochain jeton
	 */
	public abstract int placerJeton(Grille grille, Joueur joueur, Joueur opposant) ;
}
