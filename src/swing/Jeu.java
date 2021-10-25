package swing;

import javax.swing.JTable;

public class Jeu {

	public char[][] getTabJeu() {
		return tabJeu;
	}

	public void setTabJeu(char[][] tabJeu) {
		this.tabJeu = tabJeu;
	}

	private char tabJeu[][];
	private int C;
	private int L;
	private int condWin;
	private int nbTour;
	private char charVide;

	public int getNbTour() {
		return nbTour;
	}

	public void setNbTour(int nbTour) {
		this.nbTour = nbTour;
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

	// affichage du tabJeu:
	public void affichetabJeu() {

		// System.out.println("Tour " + i + ", Etat du tabJeu :");

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

	public int placementJeton(int CJoue, char SymboleAPlace, JTable aff ) {
		// on verifie la case la plus haute du tableau si cette à déjà un piuon alors
		// C = pleine
		System.out.println("CJoue "+CJoue);
		if (tabJeu[CJoue][0] != charVide) {
			System.out.println("faux");
			return -1;
		} else {

			System.out.println("Placement C " + (CJoue + 1) + " symbole " + SymboleAPlace);
			// placement du jeton:
			int ligneTester = L - 1;
			System.out.println("CJoue" + CJoue + " ligneTester" + ligneTester);
			while (tabJeu[CJoue][ligneTester] != charVide) {
				ligneTester--;
				System.out.println("ligne" + ligneTester);
			}
		//	aff.SetValueRowCol(ligneTester, CJoue , SymboleAPlace);
			System.out.println("ligne" + ligneTester);
			tabJeu[CJoue][ligneTester] = SymboleAPlace;
			nbTour++;
			
			return ligneTester;
		}

	}

	public boolean gagnant(int CJoue, int rang, char symbole) {
		// pour les diagonales on part du point de jeu et on somme en descendant la plus longue chaine de symbole 
		// puis on repart du rang et on fait vers le haut
		// diagonal NordOuest SudEst=> \
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
			win = "diagonal NordOuest SudEst=> \\ diagonal NordOuest SudEst=> \\";

		// diagonale NordEST SudOuest => /
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
	
		// verticale => |
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

		//  horizontale => -
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
		if(somme>max)
		max = somme;
		System.out.println("horizontale => - somme " + somme +" condWin " + condWin);
		if (max >= condWin)
			win = "horizontale => -";

		if (max >= condWin) {

			System.out.println("****VICTOIRE DE "+symbole+"**** en "+ win );
			return true;
		} else
			System.out.println("****PAS VICTOIRE DE "+symbole+"**** en "+ win );
			return false;
	}
}
