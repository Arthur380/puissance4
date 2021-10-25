/**
 * 
 */
package swing;

public abstract class JoueurAbstrait {

	// type de jeton du joueur
	private char symbole;

	// Le nom du joeur

	private String nom;

	private int niveau;

	public int getNiveau() {
		return niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	public JoueurAbstrait(char symbole, int lvl) {
		this.symbole = symbole;
		this.niveau = lvl;
	}

	public char getSymbole() {
		return symbole;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public abstract int placerChar(Grille grille, JoueurAbstrait joueur, JoueurAbstrait opposant);
}
