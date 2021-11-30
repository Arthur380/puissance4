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
		return 4;
	}

	public void setNiveau(int niveau) {
		this.niveau = this.niveau;
	}

	public JoueurAbstrait(char symbole, int lvl) {
		this.symbole = symbole;
		this.niveau = lvl;
	}

	public char getSymbole() {
		return symbole;
	}
	
	public String getCouleur() {
		if (symbole == 'X')
			return "rouge";
		else
			return "jaune";
	}


	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
// methode abstraite qui sera implementé chez le joueur et L'IA
	public abstract int placerChar(Grille grille);
}
