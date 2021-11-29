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
	public static final int POIDS_MAX = 10;
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

		// on verifie que on a pas d�j� rempli la colonne
		if (colPleine(i)) {

			return -1;
		} else {
			// on part du haut de la grille (visuellement le bas)
			int ligneTester = LONGUEUR - 1;
			// int ligneTester = 0;

			// tant qu'on est sur un char different de blanc on monte
			while (tabJeu[i][ligneTester] != CHAR_NULL) {

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
	for (int j = this.dernierSymbole(i); j < LONGUEUR; j++) {
		poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, i, j,colonneGauche, descendreLigne);
		if (poidsAlignement == POIDS_MAX ) {
			return true;
		} 
		poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, i, j,colonneDroite, descendreLigne);
		if (poidsAlignement == POIDS_MAX  ) {
			return true;
		} 
		poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, i, j, Stable,descendreLigne);
		if (poidsAlignement == POIDS_MAX ) {
			return true;
		} 
		poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, i, j,colonneGauche, Stable);
		if (poidsAlignement == POIDS_MAX ) {
			return true;
		} 
		poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, i, j,colonneDroite, Stable);
		if (poidsAlignement == POIDS_MAX ) {
			return true;
		} 
		poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, i, j,colonneGauche, monterLigne);
		if (poidsAlignement == POIDS_MAX ) {
			return true;
		} 
		poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, i, j,colonneDroite, monterLigne);
		if (poidsAlignement == POIDS_MAX  ) {
			return true;
		}
		poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, i, j,Stable, monterLigne);
		if (poidsAlignement == POIDS_MAX ) {
			return true;
		} 
	}	
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
	public int poids(JoueurAbstrait joueur ) {
		int poids = 0, poidsAlignement = 0;
		int descendreLigne = 1, colonneGauche = -1, colonneDroite = 1, Stable = 0,monterLigne = -1;
		// d'abord on regarde si l'adversaire gagne c'est un min si il gagne( on inverse
		// donc le resultat
	//this.afficheGrille();
		char SymboleACalculer, SymboleOpposant;
		SymboleACalculer = joueur.getSymbole();
		if (this.getTourJoueurSuivant().getSymbole()!=SymboleACalculer ) {
			SymboleOpposant  =this.getTourJoueurSuivant().getSymbole();
		}else
		{
			SymboleOpposant  =this.getTourDeQuelJoueur().getSymbole();
		} 

		if(this.VictoireAdversaire(SymboleOpposant, SymboleACalculer))
				{return AlgoAlphaBeta.MIN;}
		
		// on fait le poids pour le joueur actuel
		// on fait le poids pour le joueur actuel
		for (int i = 0; i < LARGEUR; i++) {
			for (int j =this.dernierSymbole(i); j < LONGUEUR; j++) {
				poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, i, j,colonneGauche, descendreLigne);
				if (poidsAlignement == POIDS_MAX ) {
					return AlgoAlphaBeta.MAX;
				} else {
					poids += poidsAlignement;
					poidsAlignement = 0;
				}
				poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, i, j,colonneDroite, descendreLigne);
				if (poidsAlignement == POIDS_MAX  ) {
					return AlgoAlphaBeta.MAX;
				} else {
					poids += poidsAlignement;
					poidsAlignement = 0;
				}
				poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, i, j, Stable,descendreLigne);
				if (poidsAlignement == POIDS_MAX ) {
					return AlgoAlphaBeta.MAX;
				} else {
					poids += poidsAlignement;
					poidsAlignement = 0;
				}
				poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, i, j,colonneDroite, Stable);
				if (poidsAlignement == POIDS_MAX ) {
					return AlgoAlphaBeta.MAX;
				} else {
					poids += poidsAlignement;
					poidsAlignement = 0;
				}
				poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, i, j,colonneGauche, Stable);
				if (poidsAlignement == POIDS_MAX ) {
					return AlgoAlphaBeta.MAX;
				} else {
					poids += poidsAlignement;
					poidsAlignement = 0;
				}
				poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, i, j,colonneGauche, monterLigne);
				if (poidsAlignement == POIDS_MAX ) {
					return AlgoAlphaBeta.MAX;
				} else {
					poids += poidsAlignement;
					poidsAlignement = 0;
				}
				poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, i, j,colonneDroite, monterLigne);
				if (poidsAlignement == POIDS_MAX  ) {
					return AlgoAlphaBeta.MAX;
				} else {
					poids += poidsAlignement;
					poidsAlignement = 0;
				}
				poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, i, j,Stable, monterLigne);
				if (poidsAlignement == POIDS_MAX  ) {
					return AlgoAlphaBeta.MAX;
				} else {
					poids += poidsAlignement;
					poidsAlignement = 0;
				} 
			}
		}
		//if(poids > 24) {System.out.print("\n max result  "+ poids);this.afficheGrille();}
		return poids;
	}

	// Nous allons parcourir la grille en se deplacant via deplacementLargeur
	// deplacementLongueur en incrementant automatiquement les valeur par elle m�me
	public int parcoursResultatGrille(char symboleJoueur, char symboleJoueurOpposant ,  int colonneDepart, int lignerDepart, int deplacementColonne,
			int deplacementLigne) {
		int  colonne = colonneDepart, ligne = lignerDepart;
		int boucleMax = 0, poidsColonne = POIDS_MAX;
		boolean boucler = true;
		int tourDeBoucleMax = 0;
		// la longeur = ligne
		// la largeur = colonne
		//
	//	System.out.println("PASSAGEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE " +colonneDepart +" " +lignerDepart);
		while (boucler && tourDeBoucleMax<CONDITION_VICTOIRE ) {
			try {				
				if (this.tabJeu[colonne][ligne] == symboleJoueur) {
					poidsColonne = poidsColonne;
				}else if(this.tabJeu[colonne][ligne] == CHAR_NULL) {
					poidsColonne = (int) poidsColonne/2 ;
					
				}else {// pion adverse
					poidsColonne = 0;
					boucler = !boucler;
				}
			}
		 catch(ArrayIndexOutOfBoundsException ex) {
			 poidsColonne = 0;
			 boucler = false; 
			 }  		
 
			// on continue le deplacement !
			colonne += deplacementColonne;
			ligne += deplacementLigne;
			tourDeBoucleMax++;
			
		}
		if (poidsColonne< 0) {
			poidsColonne = 0;
		//	this.afficheGrille();
		//System.out.println("poidsColonne " +poidsColonne);
		}

	
		return poidsColonne;
	}
	public void affichePoids() {
		try {
			for (int x = 0; x < poidsColonnes.length; x++) {
				System.out.print(" " + poidsColonnes[x] + " ");
				
			}
		}   catch(Exception ex)
        {
			 ex.printStackTrace();
        } 
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
		for (int x = 0; x < LARGEUR; x++) {
			System.out.print("  " + (x+1) + "");
		}		
			
	}

	public static void main(String[] args) {
		int victoireJ1=0, victoireJ2=0;
		for( int nbjeu=0; nbjeu<100;nbjeu++) {
		long debut = System.currentTimeMillis();
		boolean win = true;
		Grille grille = new Grille();
		JoueurAbstrait joueurA = new Ordinateur('X', 4);
		joueurA.setNom("1");
		JoueurAbstrait joueurB = new Ordinateur('O', 4);
		joueurB.setNom("2");
		grille.setJoueur1(joueurA);
		grille.setJoueur2(joueurB);

		grille.afficheGrille();
		JoueurAbstrait JoueurActuel = grille.getTourDeQuelJoueur();
		JoueurAbstrait JoueurSuivant = grille.getTourJoueurSuivant();

		
		/*	for (int i = 0; i < LARGEUR; i++) {
			for (int j = grille.dernierSymbole(i); j < LONGUEUR; j++) {
				System.out.print("\ni , "+ i + " j "+ j + " valeur"+ grille.getcase(i, j) );
			}
		}*/		
		
		boolean gagner=false; 
		while(!gagner ) {
			int colonneAJouer;
			
			colonneAJouer = JoueurActuel.placerChar(grille);
			grille.insere(colonneAJouer,JoueurActuel.getSymbole());
			if (grille.VictoireAdversaire(JoueurActuel.getSymbole(), JoueurSuivant.getSymbole())) {
				grille.afficheGrille();
				gagner =true;
				System.out.print("Bravo c'est gagn� "+JoueurActuel.getNom() );
				if(JoueurActuel.getNom()=="1") {
					victoireJ1++;
				}else {victoireJ2++;}
			}
			
		//	grille.afficheGrille();
			JoueurActuel = grille.getTourDeQuelJoueur();
			JoueurSuivant = grille.getTourJoueurSuivant();			
		}	
		
		}
		System.out.print(" victoireJ1 "+victoireJ1 +" victoireJ2 "+victoireJ2 );
		
	}

		
		/*
		grille.afficheGrille();
		System.out.print("\n poids  "+JoueurSuivant.getNom());
		System.out.print("\n " +JoueurSuivant.placerChar(grille));/*	
		grille.afficheGrille();
		grille.insere(4, JoueurActuel.getSymbole());
		grille.insere(4, JoueurActuel.getSymbole());
		System.out.print("\n poids important  "+joueurA.getNom()+" " + grille.poids(joueurA));	
		grille.insere(4, JoueurActuel.getSymbole());
		grille.insere(4, JoueurActuel.getSymbole());
		grille.insere(1, JoueurActuel.getSymbole());
		grille.afficheGrille();
		System.out.print("\n poids  "+joueurA.getNom()+" " +joueurA.placerChar(grille));		
		System.out.print("\n poids  " +joueurB.getNom()+" " + grille.poids(joueurB));	
		System.out.println("\n"+ (System.currentTimeMillis()-debut));
		Grille g2 = (Grille) grille.clone();
		System.out.print("\n grille apr�s creation G2  " );
		grille.afficheGrille();
		g2.insere(1, JoueurActuel.getSymbole());
		System.out.print("\n grille apr�s insertion G2  " );
		grille.afficheGrille();
		System.out.print("\n G2 apr�s insertion G2  " );
		g2.afficheGrille();
		Grille g3 = g2.Copie();
		g2.insere(1, JoueurActuel.getSymbole());
		System.out.print("\n g3 apr�s insertion G2  " );
		g3.afficheGrille();
		g2.afficheGrille();*/
	
}
