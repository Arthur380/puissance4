package swing;

import javax.swing.JTable;

public class Jeu {

	private char charVide;
	private char tabJeu[][];
	private int C;
	private int L;
	private int condWin;
	private int nbTour;

	//----------------------------------------------------------------------------------------
	public char[][] getTabJeu() {
		return tabJeu;
	}

	public void setTabJeu(char[][] tabJeu) {
		this.tabJeu = tabJeu;
	}

	//----------------------------------------------------------------------------------------
	public int getNbTour() {
		return nbTour;
	}

	public void setNbTour(int nbTour) {
		this.nbTour = nbTour;
	}
	
	public boolean getEqualCase(int a, int b, char c) {
        return tabJeu [a][b] == c;
    }

	public Jeu(int Colonne, int Ligne, int nbaAligne) {
		tabJeu = new char[Colonne][Ligne];
		C = Colonne;
		L = Ligne;
		condWin = nbaAligne;
		nbTour = 0;
		charVide = 'V';
		iniJeu();
		
		System.out.println("Objet jeu initialiser");
		affichetabJeu();
	}

	public void iniJeu() {
		for (int x = 0; x < C; x++)
			for (int y = 0; y < L; y++)
				tabJeu[x][y] = charVide;
		
	}

	
	/**
	 * Affiche le jeu actuel dans la console
	 */
	public void affichetabJeu() {

		for (int loop = 0; loop < C + 2 + 2 * C; loop++)
			System.out.print('-');
		System.out.println();

		for (int y = 0; y < L; y++) {
			System.out.print('|');
			for (int x = 0; x < C; x++) {
				System.out.print(" " + tabJeu[x][y] + " ");
			}
			System.out.print('|');
			System.out.println();
		}
	}

	/**Placement d'un jeton ayant un symbole dans une table donnée dans une certaine colonne
	 * 
	 * @param CJoue
	 * @param SymboleAPlace symbole du joueur (X ou O) à placer
	 * @param aff table de jeu
	 * @return nb de lignes testées
	 */
	public int placementJeton(int CJoue, char SymboleAPlace, JTable aff ) {
		// on verifie la case la plus haute du tableau si cette à déjà un piuon alors
		// C = pleine
		System.out.println("CJoue "+CJoue);
		
		if (tabJeu[CJoue][0] != charVide) {
			System.out.println("faux");
			return -1;
			
		} else {

			System.out.println("Placement C " + (CJoue + 1) + " symbole " + SymboleAPlace);
			
			// placement du jeton
			int ligneTester = L - 1;
			System.out.println("CJoue" + CJoue + " ligneTester" + ligneTester);
			
			while (tabJeu[CJoue][ligneTester] != charVide) {
				ligneTester--;
				System.out.println("ligne" + ligneTester);
			}
			
			System.out.println("ligne" + ligneTester);
			tabJeu[CJoue][ligneTester] = SymboleAPlace;
			nbTour++;
			
			return ligneTester;
		}

	}

	/**
	 * Vérifie si un joueur est gagnant ou non
	 * @param CJoue colonne où le jeton est joué
	 * @param rang ligne sur laquelle le jeton est placé
	 * @param symbole X ou O
	 * @return victoire ou non pour un symbole dans la direction gagnante
	 */
	public boolean gagnant(int CJoue, int rang, char symbole) {
		// pour les diagonales on part du point de jeu et on somme en descendant la plus longue chaine de symbole 
		// puis on repart du rang et on fait vers le haut
		
		//diagonale NordOuest SudEst=> \
		int x, y, somme;
		String win= " "; 
		x = CJoue ;
		y = rang;
		somme = -1 ;	
		int max = 0;
		System.out.println("x "+x+" y" +y);
		while (y >= 0 && x >= 0 && tabJeu[x][y] == symbole && somme<condWin) {
			System.out.println("x "+x+" y" +y);
			y--;
			x--;
			somme++;
		}		
		x =CJoue;
		y = rang;
		while (y < L && x < C && tabJeu[x][y] == symbole && somme<condWin) {
			System.out.println("x "+x+" y" +y);
			y++;
			x++;
			somme++;
		}
		max = somme;
		System.out.println("somme " + somme +" condWin " + condWin);
		if (somme >= condWin)
			win = "diagonale NordOuest SudEst=> \\ diagonale NordOuest SudEst=> \\";

		//diagonale NordEST SudOuest => /
		x = CJoue;
		y = rang;
		somme = -1 ;
		
		while (y >= 0 && x < C && tabJeu[x][y] == symbole && somme<condWin) {
			System.out.println("x "+x+" y" +y);
			y--;
			x++;
			somme++;
		}		
		x = CJoue;
		y = rang;	
		while (y < L && x >= 0 && tabJeu[x][y] == symbole && somme<condWin) {
			System.out.println("x "+x+" y" +y);
			y++;
			x--;
			somme++;
		}
		if(somme>max)
			max = somme;
		System.out.println("Diagonale NordEST SudOuest => / somme " + somme +" condWin " + condWin);
		if (somme >= condWin)
			win = "Diagonale NordEST SudOuest => /";
	
		//de colonne verticale => |
		x = CJoue;
		y = rang;
		somme = -1 ;	
		while (y >= 0 && tabJeu[x][y] == symbole && somme<condWin) {
			System.out.println("x "+x+" y" +y);
			y--;
			somme++;
		}
		y = rang;
		while (y < L && tabJeu[x][y] == symbole && somme<condWin) {
			System.out.println("x "+x+" y" +y);
			y++;
			somme++;
		}
		if(somme>max)
			max = somme;
		System.out.println(" verticale => | somme " + somme +" condWin " + condWin);
		if (somme >= condWin)
			win = "verticale => |";

		//en ligne horizontale => -
		x = CJoue;
		y = rang;
		somme = -1 ;	
		while (x >= 0 && tabJeu[x][y] == symbole && somme<condWin) {
			x--;
			somme++;
		}
		
		x = CJoue;
		while (x < C && tabJeu[x][y] == symbole && somme<condWin) {
			x++;
			somme++;
		}
		
		if(somme>max) {
			max = somme;
			System.out.println("horizontale => - somme " + somme +" condWin " + condWin);
		}
	
		if (max >= condWin) {
			win = "horizontale => -";
		}

		if (max >= condWin) {
			System.out.println("****VICTOIRE DE "+ symbole +"**** en "+ win );
			return true;
		} else
			System.out.println("****PAS VICTOIRE DE "+ symbole +"**** en "+ win );
			return false;
	}
}
