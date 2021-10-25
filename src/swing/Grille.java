package swing;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JTable;

public class Grille {

	public static final int LARGEUR = 7;
	public static final int LONGUEUR = 6;
	public static final int CONDITION_VICTOIRE = 4;
	private char tabJeu[][];
	private int nombreDeTour = 0;

	public JoueurAbstrait getJoueur1() {
		return joueur1;
	}

	public void setJoueur1(JoueurAbstrait joueur1) {
		this.joueur1 = joueur1;
	}

	public JoueurAbstrait getJoueur2() {
		return joueur2;
	}

	public void setJoueur2(JoueurAbstrait joueur2) {
		this.joueur2 = joueur2;
	}

	private JoueurAbstrait joueur1;
	private JoueurAbstrait joueur2;

	public JoueurAbstrait j() {
		//tour paire = joueur 1 / impaire joueur 2
		if (this.getNombreDeTour() % 2 == 0)
			return getJoueur1();
		else
			return getJoueur2();
	}

	public int getNombreDeTour() {
		return nombreDeTour;
	}

	public void setNombreDeTour(int nombreDeTour) {
		this.nombreDeTour = nombreDeTour;
	}

	public char[][] getTabJeu() {
		return tabJeu;
	}

	public void setTabJeu(char[][] tabJeu) {
		this.tabJeu = tabJeu;
	}

	// Initialise la grille � vide

	public Grille() {
		this.tabJeu = new char[LARGEUR][LONGUEUR];
		for (int i = 1; i <= LARGEUR; i++) {
			for (int j = 1; j <= LONGUEUR; j++) {
				this.tabJeu[i][j] = ' ';
			}
		}
		nombreDeTour = 0;
	}

	public void insere(int i, char symbole) {
		if (colPleine(i)) {
			System.out.println("Colonne " + i + "est pleine");
		}
		// on prends la dernioere valeur jou� sur cette colonne
		int j = this.dernierSymbole(i);

		this.tabJeu[i][j] = symbole;
		this.setNombreDeTour(this.getNombreDeTour() + 1);
	}

	public Grille Copie() {
		Grille copieDeLaGrille = new Grille();
		copieDeLaGrille.setTabJeu(Arrays.copyOf(this.tabJeu, this.tabJeu.length));
		copieDeLaGrille.setNombreDeTour(this.getNombreDeTour());
		return copieDeLaGrille;
	}

	public boolean colPleine(int i) {
		// retourne vrai si on a autre chose que du vide en haut (le tableau est init a
		// blanc)
		return (this.tabJeu[i][LARGEUR] != ' ');
	}
	public boolean grillePleine(int i) {
	// la grille est pleine si on a autant de case de que nombre de tour
		return (LARGEUR*LONGUEUR ==this.getNombreDeTour());
	}
	public int dernierSymbole(int i) {
		// on verifie que on a pas d�j� rempli la colonne
		if (colPleine(i)) {
			return -1;
		} else {
			// on part du haut de la grille (visuellement le bas)
			int ligneTester = LARGEUR - 1;
			// tant qu'on est sur un char different de blanc on monte
			while (tabJeu[i][ligneTester] != ' ') {
				ligneTester--;
			}
			return ligneTester;
		}
	}

	public ArrayList<Integer> colonesJouables() {

		ArrayList<Integer> colonnesJouables = new ArrayList<Integer>();

		// Donne les colonnes jouables dans un ordre croissant
		for (int i = 1; i <= this.LARGEUR; i++) {
			if (this.colPleine(i) != true) {
				colonnesJouables.add(i);
			}
		}
		return colonnesJouables;
	}
	public JoueurAbstrait getTourDeQuelJoueur() {
		return (this.getNombreDeTour()%2==1 ? this.getJoueur2() : this.getJoueur1());
	}

	public boolean chercheAlignement4(int rang, char symbole) {

		// pour les diagonales on part du point de jeu et on somme en descendant la plus
		// longue chaine de symbole
		// puis on repart du rang et on fait vers le haut
		// diagonal NordOuest SudEst=> \
		int x, y, somme;
		String win = " ";
		x = symbole;
		y = rang;
		somme = -1;
		int max = 0;
		int C = LARGEUR, L = LONGUEUR;
		System.out.println("x " + x + " y" + y);
		while (y >= 0 && x >= 0 && tabJeu[x][y] == symbole && somme < CONDITION_VICTOIRE) {
			System.out.println("x " + x + " y" + y);
			y--;
			x--;
			somme++;
		}
		x = symbole;
		y = rang;
		while (y < L && x < C && tabJeu[x][y] == symbole && somme < CONDITION_VICTOIRE) {
			System.out.println("x " + x + " y" + y);
			y++;
			x++;
			somme++;
		}
		max = somme;
		System.out.println("somme " + somme + " CONDITION_VICTOIRE " + CONDITION_VICTOIRE);
		if (somme >= CONDITION_VICTOIRE)
			win = "diagonal NordOuest SudEst=> \\ diagonal NordOuest SudEst=> \\";

		// diagonale NordEST SudOuest => /
		x = symbole;
		y = rang;
		somme = -1;

		while (y >= 0 && x < C && tabJeu[x][y] == symbole && somme < CONDITION_VICTOIRE) {
			System.out.println("x " + x + " y" + y);
			y--;
			x++;
			somme++;
		}
		x = symbole;
		y = rang;
		while (y < L && x >= 0 && tabJeu[x][y] == symbole && somme < CONDITION_VICTOIRE) {
			System.out.println("x " + x + " y" + y);
			y++;
			x--;
			somme++;
		}
		if (somme > max)
			max = somme;
		System.out.println(
				"Diagonale NordEST SudOuest => / somme " + somme + " CONDITION_VICTOIRE " + CONDITION_VICTOIRE);
		if (somme >= CONDITION_VICTOIRE)
			win = "Diagonale NordEST SudOuest => /";

		// verticale => |
		x = symbole;
		y = rang;
		somme = -1;
		while (y >= 0 && tabJeu[x][y] == symbole && somme < CONDITION_VICTOIRE) {
			System.out.println("x " + x + " y" + y);
			y--;
			somme++;
		}
		y = rang;
		while (y < L && tabJeu[x][y] == symbole && somme < CONDITION_VICTOIRE) {
			System.out.println("x " + x + " y" + y);
			y++;
			somme++;
		}
		if (somme > max)
			max = somme;
		System.out.println(" verticale => | somme " + somme + " CONDITION_VICTOIRE " + CONDITION_VICTOIRE);
		if (somme >= CONDITION_VICTOIRE)
			win = "verticale => |";

		// horizontale => -
		x = symbole;
		y = rang;
		somme = -1;
		while (x >= 0 && tabJeu[x][y] == symbole && somme < CONDITION_VICTOIRE) {
			x--;
			somme++;
		}
		x = symbole;
		while (x < C && tabJeu[x][y] == symbole && somme < CONDITION_VICTOIRE) {
			x++;
			somme++;
		}
		if (somme > max)
			max = somme;
		System.out.println("horizontale => - somme " + somme + " CONDITION_VICTOIRE " + CONDITION_VICTOIRE);
		if (max >= CONDITION_VICTOIRE)
			win = "horizontale => -";

		if (max >= CONDITION_VICTOIRE) {

			System.out.println("****VICTOIRE DE " + symbole + "**** en " + win);
			return true;
		} else
			System.out.println("****PAS VICTOIRE DE " + symbole + "**** en " + win);
		return false;

	}

}