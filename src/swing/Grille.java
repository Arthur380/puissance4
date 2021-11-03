package swing;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JTable;

public class Grille {
	// de gauche a droite
	public static final int LARGEUR = 7;
	// de bas en haut
	public static final int LONGUEUR = 6;
	public static final int CONDITION_VICTOIRE = 4;
	private char tabJeu[][];
	private int nombreDeTour = 0;
	private int poidsColonnes[];

	public int[] getPoidsColonnes() {
		return poidsColonnes;
	}

	public void setPoidsColonnes(int[] poidsColonnes) {
		this.poidsColonnes = poidsColonnes;
	}

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
		// tour paire = joueur 1 / impaire joueur 2
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
		for (int i = 0; i < LARGEUR; i++) {
			for (int j = 0; j < LONGUEUR; j++) {

				this.tabJeu[i][j] = '.';
			}
		}
		nombreDeTour = 0;
	}

	public void insere(int i, char symbole) {
		if (colPleine(i)) {
			System.out.println("Colonne " + i + "est pleine");
		} else {
			// on prends la dernioere valeur jou� sur cette colonne
			int j = this.dernierSymbole(i);

			this.tabJeu[i][j] = symbole;
			this.setNombreDeTour(this.getNombreDeTour() + 1);
		}
	}

	public Grille Copie() {
		Grille copieDeLaGrille = new Grille();
		copieDeLaGrille.setTabJeu(Arrays.copyOf(this.tabJeu, this.tabJeu.length));
		copieDeLaGrille.setNombreDeTour(this.getNombreDeTour());
		copieDeLaGrille.setJoueur1(joueur1);
		copieDeLaGrille.setJoueur2(joueur2);
		return copieDeLaGrille;
	}

	public boolean colPleine(int i) {
		// retourne vrai si on a autre chose que . en haut (le tableau est init a
		// .)
		// System.out.print("\n Colpleine " + (this.tabJeu[i][LONGUEUR-1] != ' ')+" Col
		// "+ i+" " +this.tabJeu[i][LONGUEUR-1]);

		return !(this.tabJeu[i][0] == '.');
	}

	public boolean grillePleine(int i) {
		// la grille est pleine si on a autant de case de que nombre de tour
		return (LARGEUR * LONGUEUR == this.getNombreDeTour());
	}

	public int dernierSymbole(int i) {

		// on verifie que on a pas d�j� rempli la colonne
		if (colPleine(i)) {

			return -1;
		} else {
			// on part du haut de la grille (visuellement le bas)
			int ligneTester = LONGUEUR - 1;
			// int ligneTester = 0;

			// tant qu'on est sur un char different de blanc on monte
			while (tabJeu[i][ligneTester] != '.') {

				ligneTester--;

			}

			return ligneTester;
		}
	}

	public ArrayList<Integer> colonesJouables() {

		ArrayList<Integer> colonnesJouables = new ArrayList<Integer>();

		// Donne les colonnes jouables dans un ordre croissant
		for (int i = 0; i < this.LARGEUR; i++) {
			if (this.colPleine(i) != true) {
				colonnesJouables.add(i);
			}
		}
		return colonnesJouables;
	}

	public JoueurAbstrait getTourDeQuelJoueur() {
		return (this.getNombreDeTour() % 2 == 1 ?  this.getJoueur2() : this.getJoueur1()  );
	}

	// on verifie que a qui c'est le tour fonction inverse de getTourDeQuelJoueur
	public JoueurAbstrait getTourJoueurSuivant() {
		return (this.getNombreDeTour() % 2 == 1 ?this.getJoueur1() : this.getJoueur2());
	}

	// pour calculer le poids nous allons parcourir le tableau de la grille actuel
	// de plusieurs mani�re en evitant de dupliquer les comptes
	// de haut en bas pour chaque case on fait 4 test
	// horizontal vers la droite =>
	// diagonale bas droite et diagonale bas gauche
	// vertical vers le bas
	// on a un dernierSymbole qui retourne la derni�religne jou� sur cette colonne (
	// la plus haute)
	public int poids(JoueurAbstrait joueur) {
		int poids = 0, poidsAlignement = 0;
		int descendreLigne = 1, colonneGauche = -1, colonneDroite = 1, Stable = 0;
		// d'abord on regarde si l'adversaire gagne c'est un min si il gagne( on inverse
		// donc le resultat
	
		for (int i = 0; i < LARGEUR; i++) {
			for (int j = this.dernierSymbole(i); j < LONGUEUR; j--) {
				// poids =
				// this.parcoursResultatGrille(this.getTourJoueurSuivant().getSymbole(),i,j);
				if (this.parcoursResultatGrille(this.getTourJoueurSuivant().getSymbole(), i, j, colonneGauche,
						descendreLigne) == AlgoAlphaBeta.MAX) {
					return AlgoAlphaBeta.MIN;
				}
				if (this.parcoursResultatGrille(this.getTourJoueurSuivant().getSymbole(), i, j, colonneDroite,
						descendreLigne) == AlgoAlphaBeta.MAX) {
					return AlgoAlphaBeta.MIN;
				}
				if (this.parcoursResultatGrille(this.getTourJoueurSuivant().getSymbole(), i, j, Stable,
						descendreLigne) == AlgoAlphaBeta.MAX) {
					return AlgoAlphaBeta.MIN;
				}
				if (this.parcoursResultatGrille(this.getTourJoueurSuivant().getSymbole(), i, j, colonneDroite,
						Stable) == AlgoAlphaBeta.MAX) {
					return AlgoAlphaBeta.MIN;
				}
			}
		} 

		// on fait le poids pour le joueur actuel
		for (int i = 0; i < LARGEUR; i++) {
			for (int j = this.dernierSymbole(i); j < LONGUEUR; j++) {
				poidsAlignement = this.parcoursResultatGrille(this.getTourDeQuelJoueur().getSymbole(), i, j,colonneGauche, descendreLigne);
				if (poidsAlignement == AlgoAlphaBeta.MAX) {
					return AlgoAlphaBeta.MAX;
				} else {
					poids += poidsAlignement;
					poidsAlignement = 0;
				}
				poidsAlignement = this.parcoursResultatGrille(this.getTourDeQuelJoueur().getSymbole(), i, j,colonneDroite, descendreLigne);
				if (poidsAlignement == AlgoAlphaBeta.MAX) {
					return AlgoAlphaBeta.MAX;
				} else {
					poids += poidsAlignement;
					poidsAlignement = 0;
				}
				poidsAlignement = this.parcoursResultatGrille(this.getTourDeQuelJoueur().getSymbole(), i, j, Stable,descendreLigne);
				if (poidsAlignement == AlgoAlphaBeta.MAX) {
					return AlgoAlphaBeta.MAX;
				} else {
					poids += poidsAlignement;
					poidsAlignement = 0;
				}
				poidsAlignement = this.parcoursResultatGrille(this.getTourDeQuelJoueur().getSymbole(), i, j,colonneDroite, Stable);
				if (poidsAlignement == AlgoAlphaBeta.MAX) {
					return AlgoAlphaBeta.MAX;
				} else {
					poids += poidsAlignement;
					poidsAlignement = 0;
				}
			}
		}
		return poids;

	}

	// Nous allons parcourir la grille en se deplacant via deplacementLargeur
	// deplacementLongueur en incrementant automatiquement les valeur par elle m�me
	public int parcoursResultatGrille(char symbole,  int colonneDepart, int lignerDepart, int deplacementColonne,
			int deplacementLigne) {
		int valeurColonne, colonne = colonneDepart, ligne = lignerDepart;
		int boucleMax = 0, poidsColonne = 0;
		boolean boucler = true;
		// la longeur = ligne
		// la largeur = colonne
		//
	//	System.out.println("PASSAGEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE " +colonneDepart +" " +lignerDepart);
		while ((ligne < this.LONGUEUR && ligne >= 0) && (colonne < this.LARGEUR && colonne >= 0)
				&& boucleMax <= this.CONDITION_VICTOIRE && boucler) {
							
			System.out.println("ligne/colonne "+ligne+"/"+colonne+ " Valtab "+this.tabJeu[colonne][ligne]);
			if (this.tabJeu[colonne][ligne] == symbole) {
				System.out.println("symbole"+ symbole+" lignerDepart "+ lignerDepart + " colonneDepart "+colonneDepart+ " deplacementColonne "+ deplacementColonne + " deplacementLigne "+deplacementLigne);
				poidsColonne += 1;
			} else {
				boucler = false;
			}
			// on continue le deplacement !
			colonne += deplacementColonne;
			ligne += deplacementLigne;
			boucleMax++;
		}
		// c'est qu'on a les conditions de victoire
		if (poidsColonne == this.CONDITION_VICTOIRE) {
			return AlgoAlphaBeta.MIN;
		}
		return poidsColonne;
	}

	public void afficheGrille() {
		System.out.println();
		for (int y = 0; y < LONGUEUR; y++) {
			System.out.print('|');
			for (int x = 0; x < LARGEUR; x++) {
				System.out.print(" " + tabJeu[x][y] + " ");
			}
			System.out.print('|');
			System.out.println();
		}
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

	public static void main(String[] args) {
		boolean win = true;
		Grille grille = new Grille();
		JoueurAbstrait joueurA = new Humain('x', 4);
		joueurA.setNom("Joueur 1 ");
		JoueurAbstrait joueurB = new Ordinateur('O', 4);
		joueurB.setNom("Joueur 2 ");
		grille.setJoueur1(joueurA);
		grille.setJoueur2(joueurB);

		grille.afficheGrille();
		JoueurAbstrait JoueurActuel = grille.getTourDeQuelJoueur();
		JoueurAbstrait JoueurSuivant = grille.getTourJoueurSuivant();

		grille.insere(4, JoueurActuel.getSymbole());
		grille.insere(4, JoueurActuel.getSymbole());
		grille.insere(4, JoueurActuel.getSymbole());
		grille.insere(1, JoueurActuel.getSymbole());
		grille.afficheGrille();
		System.out.print("\n poids  " + grille.poids(JoueurActuel));
	}

}
