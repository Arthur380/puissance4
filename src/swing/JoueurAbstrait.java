/**
 * 
 */
package swing;

public abstract class JoueurAbstrait {

	// type de jeton du joueur
	private char symbole;

	// Le nom du joeur

	private String nom;

	private int niveau=5;

	public int getNiveau() {
		String nomJoueur = this.getNom();
		if(nomJoueur =="Humain")
		{
			return 4;
		}
		else
		{
			return 1;
		}
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
// methode abstraite qui sera implementé chez le joueur et L'IA
	public abstract int placerChar(Grille grille);
}
