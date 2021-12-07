/**
 * Classe abstraite, parent des autres joueurs (qu'ils soient Humain ou ordinateur)
 */
package swing;

public abstract class JoueurAbstrait {
	//niveau de "difficulté"
	private int niveau;

	public int getNiveau() {
		return 4;
	}

	public void setNiveau(int niveau) {
		this.niveau = this.niveau;
	}

	//----------------------------------------------------------------------------------------
	//jeton du joueur X ou O
	private char symbole;
	
	public JoueurAbstrait(char symbole, int lvl) {
		this.symbole = symbole;
		this.niveau = lvl;
	}

	public char getSymbole() {
		return symbole;
	}

	public String getCouleur() {
		if (symbole == 'X'){
			return "rouge";
		}
		else{
			return "jaune";
		}
	}
	//----------------------------------------------------------------------------------------
	//nom du joueur
	private String nom;
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	//----------------------------------------------------------------------------------------
	//méthode abstraite qui sera implementée chez le joueur et L'IA
	public abstract int placerChar(Grille grille);
}
