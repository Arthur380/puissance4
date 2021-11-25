package swing;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JTable;

public class Grille extends Object implements Cloneable {
	// de gauche a droite
	public static final int LARGEUR = 7;
	// de bas en haut
	public static final int LONGUEUR = 6;
	public static final int CONDITION_VICTOIRE = 4;
	public static final char CHAR_NULL = '.';
	private char tabJeu[][];
	private int nombreDeTour = 0;
	private int poidsColonnes[] = new int[Grille.LARGEUR];

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
	public char getcase(int i,int j) {
		return tabJeu[i][j];
	}

	// Initialise la grille � vide

	public Grille() {
		this.tabJeu = new char[LARGEUR][LONGUEUR];
		for (int i = 0; i < LARGEUR; i++) {
			for (int j = 0; j < LONGUEUR; j++) {

				this.tabJeu[i][j] = CHAR_NULL;
			}
		}
		nombreDeTour = 0;	}

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
		copieDeLaGrille.setTabJeu(cloneArray(this.tabJeu));
		copieDeLaGrille.setNombreDeTour(this.getNombreDeTour());
		copieDeLaGrille.setJoueur1(joueur1);
		copieDeLaGrille.setJoueur2(joueur2);
		return copieDeLaGrille;
		
	}
	public Object clone() {
		try {
			Grille CopieGrille = (Grille) super.clone();
			CopieGrille.tabJeu = (char [][]) this.tabJeu.clone(); //clonage de this.tabJeu

			 return CopieGrille;
			 }
			 catch (CloneNotSupportedException e){
			 throw new InternalError();
			 }	
	}
	/**
	 * Clones the provided array
	 * 
	 * @param src
	 * @return a new clone of the provided array
	 */
	public static char[][] cloneArray(char[][] src) {
	    int length = src.length;
	    char[][] target = new char[length][src[0].length];
	    for (int i = 0; i < length; i++) {
	        System.arraycopy(src[i], 0, target[i], 0, src[i].length);
	    }
	    return target;
	}

	public boolean colPleine(int i) {
		// retourne vrai si on a autre chose que . en haut (le tableau est init a
		// .)
		// System.out.print("\n Colpleine " + (this.tabJeu[i][LONGUEUR-1] != ' ')+" Col
		// "+ i+" " +this.tabJeu[i][LONGUEUR-1]);

		return !(this.tabJeu[i][0] == CHAR_NULL);
	}

	public boolean grillePleine(int i) {
		// la grille est pleine si on a autant de case de que nombre de tour
		return (LARGEUR * LONGUEUR == this.getNombreDeTour());
	}

	public int dernierSymbole(int i) {
		char cell_vide = '.';
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
	
	public boolean	VictoireAdversaire(char SymboleACalculer, char SymboleOpposant){
		int  poidsAlignement = 0;
		int descendreLigne = 1, colonneGauche = -1, colonneDroite = 1, Stable = 0,monterLigne = -1;
		for (int i = 0; i < LARGEUR; i++) {
//			for (int j = this.dernierSymbole(i); j < LONGUEUR; j++) {
//				poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, i, j,colonneGauche, descendreLigne);
//				if (poidsAlignement == CONDITION_VICTOIRE ) {
//					return true;
//				} 
//				poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, i, j,colonneDroite, descendreLigne);
//				if (poidsAlignement == CONDITION_VICTOIRE  ) {
//					return true;
//				} 
//				poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, i, j, Stable,descendreLigne);
//				if (poidsAlignement == CONDITION_VICTOIRE ) {
//					return true;
//				} 
//				poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, i, j,colonneGauche, Stable);
//				if (poidsAlignement == CONDITION_VICTOIRE ) {
//					return true;
//				} 
//			}	
		}
		return false;
	}	
	// pour calculer le poids nous allons parcourir le tableau de la grille actuel
	// de plusieurs mani�re en evitant de dupliquer les comptes
	// de haut en bas pour chaque case on fait 4 test
	// horizontal vers la droite =>
	// diagonale bas droite et diagonale bas gauche
	// vertical vers le bas
	// on a un dernierSymbole qui retourne la derni�religne jou� sur cette colonne (
	// la plus haute)
	public int poids(JoueurAbstrait joueur, int colonnepes�e ) {
		int poids = 0, poidsAlignement = 0;
		int descendreLigne = -1, colonneGauche = -1, colonneDroite = 1, Stable = 0,monterLigne = -1;
		
		//RECUPERE LE SYMBOLE
		char SymboleACalculer, SymboleOpposant;
		SymboleACalculer = joueur.getSymbole();
		if (this.getTourJoueurSuivant().getSymbole()!=SymboleACalculer ) {
			SymboleOpposant  =this.getTourJoueurSuivant().getSymbole();
		}else
		{
			SymboleOpposant  =this.getTourDeQuelJoueur().getSymbole();
		} 

		this.VictoireAdversaire(SymboleOpposant, SymboleACalculer);
	
		// on fait le poids pour le joueur actuel
		// i = COLONNES j = LIGNES
		int oui;
		//for (int i = 0; i < LARGEUR; i++) {
			//oui = this.dernierSymbole(i);

			//System.out.print("\n dernier ligne = " + oui);
			//int goal = LONGUEUR-oui;
			for (int j = this.dernierSymbole(colonnepes�e); j >= 0; j--) {
				if(colonnepes�e == 3)
				{
					//CALCULE POUR DIAGN GAUCHE BAS
					poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, colonnepes�e, j,colonneGauche, descendreLigne);
					if (poidsAlignement == CONDITION_VICTOIRE ) {
						//return AlgoAlphaBeta.MAX;
					} 
					else 
					{
						poids += poidsAlignement;
						poidsAlignement = 0;
					}
					
					//CALCULE POUR DIAGN GAUCHE HAUT
					poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, colonnepes�e, j,colonneGauche, monterLigne);
					if (poidsAlignement == CONDITION_VICTOIRE ) {
						//return AlgoAlphaBeta.MAX;
					} 
					else 
					{
						poids += poidsAlignement;
						poidsAlignement = 0;
					}
					
					//CALCULE POUR DIAGN DROITE BAS
					poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, colonnepes�e, j,colonneDroite, descendreLigne);
					if (poidsAlignement == CONDITION_VICTOIRE  ) {
						//return AlgoAlphaBeta.MAX;
					} 
					else 
					{
						poids += poidsAlignement;
						poidsAlignement = 0;
					}
					
					//CALCULE POUR DIAGN DROITE HAUT
					poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, colonnepes�e, j,colonneDroite, monterLigne);
					if (poidsAlignement == CONDITION_VICTOIRE  ) {
						//return AlgoAlphaBeta.MAX;
					} 
					else 
					{
						poids += poidsAlignement;
						poidsAlignement = 0;
					}
					
					//CALCULE POUR EN BAS
					poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, colonnepes�e, j, Stable,descendreLigne);
					if (poidsAlignement == CONDITION_VICTOIRE ) {
						//return AlgoAlphaBeta.MAX;
					} 
					else 
					{
						poids += poidsAlignement;
						poidsAlignement = 0;
					}
					
					
//					//CALCULE POUR EN HAUT
//					poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, colonnepes�e, j, Stable,monterLigne);
//					if (poidsAlignement == CONDITION_VICTOIRE ) {
//						//return AlgoAlphaBeta.MAX;
//					} 
//					else 
//					{
//						poids += poidsAlignement;
//						poidsAlignement = 0;
//					}
					
					//CALCULE A GAUCHE
					poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, colonnepes�e, j,colonneGauche, Stable);
					if (poidsAlignement == CONDITION_VICTOIRE ) {
						//return AlgoAlphaBeta.MAX;
					} 
					else {
						poids += poidsAlignement;
						poidsAlignement = 0;
					}
					
					//CALCULE A DROITE
					poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, colonnepes�e, j,colonneDroite, Stable);
					if (poidsAlignement == CONDITION_VICTOIRE ) {
						//return AlgoAlphaBeta.MAX;
					} 
					else {
						poids += poidsAlignement;
						poidsAlignement = 0;
					}
					
				//}
					System.out.print("\n poids : " + poids + " ligne = " + j);
				}
				
		}
		if(poids > 24) {
			System.out.print("\n max result  "+ poids);
			//this.afficheGrille();
		}
		
		return poids;
	}

	// Nous allons parcourir la grille en se deplacant via deplacementLargeur
	// deplacementLongueur en incrementant automatiquement les valeur par elle m�me
	public int parcoursResultatGrille(char symboleJoueur, char symboleJoueurOpposant ,  int colonneDepart, int lignerDepart, int deplacementColonne,
			int deplacementLigne) {
		int  colonne = colonneDepart, ligne = lignerDepart;
		int boucleMax = 0, poidsColonne = 4;
		boolean boucler = true;
		int tourDeBoucleMax = 0;
		
//		if(colonne == 3)
//		{
			colonne += deplacementColonne;
			ligne += deplacementLigne;
			int veri = this.dernierSymbole(colonneDepart);
			//ALICE ON COMPTE DU DERNIER PION JOUE A LA LIGNE LA PLUS HAUTE
			//while(colonne < LARGEUR && colonne >= 0 && ligne >= this.dernierSymbole(colonneDepart) && ligne < LONGUEUR) {
			while(colonne < LARGEUR && colonne >= 0 && ligne >= 0 && ligne < LONGUEUR) {
					
				if (this.tabJeu[colonne][ligne] == symboleJoueur) {
					poidsColonne = poidsColonne;
				}else if(this.tabJeu[colonne][ligne] == '.') {
					poidsColonne = poidsColonne-1 ;
					
				}else {// pion adverse
					poidsColonne = 0;
				}
				colonne += deplacementColonne;
				ligne += deplacementLigne;
			}
			return poidsColonne;
		//}
		//return 0;
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
		try {
			for (int x = 0; x < poidsColonnes.length; x++) {
				System.out.print(" " + poidsColonnes[x] + " ");
				
			}
		}   catch(Exception ex)
        {
			 ex.printStackTrace();
        } 
			
	}

	public boolean chercheAlignement4(int rang, char symbole) {

		// pour les diagonales on part du point de jeu et on somme en descendant la plus
		// longue chaine de symbole
		// puis on repart du rang et on fait vers le haut
		// diagonal NordOuest SudEst=> \
		int x, y, somme;
		String win = " ";

		somme = -1;
		int max = 0;
		int C = LARGEUR, L = LONGUEUR;
		x = rang;
		y = L;
		System.out.println("x " + x + " y" + y);
		try {
		while (y >= 0 && x >= 0 && tabJeu[x][y] == symbole && somme < CONDITION_VICTOIRE) {
			System.out.println("x " + x + " y" + y);
			y--;
			x--;
			somme++;
		}
		x = rang;
		y = L;
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
		x = rang;
		y = L;
		somme = -1;

		while (y >= 0 && x < C && tabJeu[x][y] == symbole && somme < CONDITION_VICTOIRE) {
			System.out.println("x " + x + " y" + y);
			y--;
			x++;
			somme++;
		}
		x = rang;
		y = L;
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
		x = rang;
		y = L;
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
		x = rang;
		y = L;
		somme = -1;
		while (x >= 0 && tabJeu[x][y] == symbole && somme < CONDITION_VICTOIRE) {
			x--;
			somme++;
		}
		x = rang;
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
		} catch(ArrayIndexOutOfBoundsException ex) {
	return false;
		}

	}

	public static void main(String[] args) {
		long debut = System.currentTimeMillis();
		boolean win = true;
		Grille grille = new Grille();
		JoueurAbstrait joueurA = new Humain('X', 1);
		joueurA.setNom("Humain");
		JoueurAbstrait joueurB = new Ordinateur('O', 1);
		joueurB.setNom("Ordinateur ");
		grille.setJoueur1(joueurA);
		grille.setJoueur2(joueurB);

		System.out.print("\n Niveau joueur 1 = ");
		System.out.print(grille.getJoueur1().getNiveau());
		System.out.print("\n Niveau joueur 2 = ");
		System.out.print(grille.getJoueur2().getNiveau());
		System.out.print("\n");
		System.out.print("============");
		grille.afficheGrille();
		JoueurAbstrait JoueurActuel = grille.getTourDeQuelJoueur();
		JoueurAbstrait JoueurSuivant = grille.getTourJoueurSuivant();
		
		
		boolean gagner=false; 
		while(!gagner ) {
			int colonneAJouer;
			
			colonneAJouer = JoueurActuel.placerChar(grille);
			colonneAJouer = colonneAJouer;
			grille.insere(colonneAJouer,JoueurActuel.getSymbole());
			
			if (grille.chercheAlignement4(colonneAJouer,JoueurActuel.getSymbole())) {
				grille.afficheGrille();
				gagner =true;
				System.out.print("Bravo c'est gagn");
			}
			grille.afficheGrille();
			 JoueurActuel = grille.getTourDeQuelJoueur();
			 JoueurSuivant = grille.getTourJoueurSuivant();
			
		}
	}

	
}
