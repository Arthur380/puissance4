package swing;

import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JTable;

public class Grille extends Object implements Cloneable {
	public static final int 	LARGEUR 			= 7; //déplacement de Gauche à Droite
	public static final int 	LONGUEUR 			= 6; //déplacement de Bas en Haut
	public static final int 	CONDITION_VICTOIRE 	= 4;
	public static final int 	POIDS_MAX 			= 10;
	public static final char 	CHAR_NULL 			= '.';
	private char 				tabJeu[][];
	private int 				nombreDeTour 		= 0;
	private int 				poidsColonnes[] 	= new int[Grille.LARGEUR];


	public int[] getPoidsColonnes() {
		return poidsColonnes;
	}

	public void setPoidsColonnes(int[] poidsColonnes) {
		this.poidsColonnes = poidsColonnes;
	}
	
	//----------------------------------------------------------------------------------------
	private JoueurAbstrait joueur1;
	private JoueurAbstrait joueur2;
	
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

	
	//----------------------------------------------------------------------------------------
	public int getNombreDeTour() {
		return nombreDeTour;
	}

	public void setNombreDeTour(int nombreDeTour) {
		this.nombreDeTour = nombreDeTour;
	}

	//----------------------------------------------------------------------------------------
	public char[][] getTabJeu() {
		return tabJeu;
	}

	public void setTabJeu(char[][] tabJeu) {
		this.tabJeu = tabJeu;
	}
	public char getcase(int i,int j) {
		return tabJeu[i][j];
	}
	
	public boolean getEqualCase(int a, int b, char c) {
        return tabJeu [a][b] == c;
    }
	
	

//--------------------------CONSTRUCTEUR-------------------------------------------------
    /**Constructeur grille de jeu à vide
     * (public pour être vu par tous depuis l'extérieur)
     */
	public Grille() {
		
		this.tabJeu = new char[LARGEUR][LONGUEUR];
		
		//pour chaque colonne, chaque ligne, mise d'un '.' dans la cellule
		for (int i = 0; i < LARGEUR; i++) {
			for (int j = 0; j < LONGUEUR; j++) {
				this.tabJeu[i][j] = CHAR_NULL;
			}
		}
		//initialisation du nombre de tour
		nombreDeTour = 0;	
	}
	
	
//----------------------------------------------------------------------------------------
	/**Insertion d'un jeton dans la colonne i avec le symbole donné
	 * 
	 * @param i colonne ciblée
	 * @param symbole symbole à insérer (X ou O)
	 * 
	 * @return void 
	 */
	public void insere(int i, char symbole) {
		
		//vérification que la colonne n'est pas pleine
		if (colPleine(i)) {
			System.out.println("Colonne " + i + "est pleine");
			
		} else {
			
			//insertxion au dessus du denier jeton joué dans la colonne
			int j = this.dernierSymbole(i);
			this.tabJeu[i][j] = symbole;
			
			//incrémentation du nb de tour 
			this.setNombreDeTour(this.getNombreDeTour() + 1);
		}
	}
	

//----------------------------------------------------------------------------------------
	/**Insertion d'un jeton dans la colonne i avec le symbole donné
	 * 
	 * @param int i : colonne ciblée
	 * @param char symbole : symbole à insérer (X ou O)
	 * 
	 * @return void 
	 */
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

	
//----------------------------------------------------------------------------------------
	/**
	 * Clones the provided array
	 * 
	 * @param src
	 * 
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
	

//----------------------------------------------------------------------------------------
	/**
	 * Booléen vérification si colonne pleine ou non
	 * 
	 * @param i colonne ciblée
	 * 
	 * @return colonne pleine ou non 
	 */
	public boolean colPleine(int i) {
		return !(this.tabJeu[i][0] == CHAR_NULL);
	}
	

//----------------------------------------------------------------------------------------
	/**
	 * Booléen grille pleine si autant de cases que de nb de tour
	 * 
	 * @return grille pleine ou non 
	 */
	public boolean grillePleine() {
		return (LARGEUR * LONGUEUR == this.getNombreDeTour());
	}

	
//----------------------------------------------------------------------------------------
	/**
	 * Récupère le dernier symbole joué sur une colonne donnée
	 * 
	 * @param i colonne
	 * @return emplacement du dernier symbole joué
	 */
	public int dernierSymbole(int i) {

		//vérifie que la colonne n'est pas pleine
		if (colPleine(i)) {
			return -1;
			
		} else {
			//test de Haut en Bas 
			int ligneTester = LONGUEUR - 1;
			
			//monte tant qu'on ne rencontre pas un symbole différent de '.'
			while (tabJeu[i][ligneTester] != CHAR_NULL) {
				ligneTester--;
			}

			return ligneTester;
		}
	}

	
//----------------------------------------------------------------------------------------
	/**
	 * @return liste des colonnes qui peuvent être jouées
	 */
	public ArrayList<Integer> colonnesJouables() {

		ArrayList<Integer> colonnesJouables = new ArrayList<Integer>();

		//donne les colonnes jouables dans un ordre croissant
		for (int i = 0; i < this.LARGEUR; i++) {
			if (this.colPleine(i) != true) {
				colonnesJouables.add(i);
			}
		}
		return colonnesJouables;
	}
	
	
//----------------------------------------------------------------------------------------
	/**
	 * C'est au tour de quel joueur ?
	 * @return joueur actuel
	 */
	public JoueurAbstrait getTourDeQuelJoueur() {
		return (this.getNombreDeTour() % 2 == 1 ?  this.getJoueur2() : this.getJoueur1()  );
	}
	
	
//----------------------------------------------------------------------------------------
	/**
	 * Ce sera à qui de jouer ? (inverse de getTourDeQuelJoueur)
	 * @return joueur suivant
	 */
	public JoueurAbstrait getTourJoueurSuivant() {
		return (this.getNombreDeTour() % 2 == 1 ?this.getJoueur1() : this.getJoueur2());
	}
	
//----------------------------------------------------------------------------------------
	/**
	 * Vérification booléenne de victoire (alignement de 4 pions du même joueur)
	 * Diagonales, ligne ou colonne 
	 * @param SymboleACalculer symbole du joueur actuel
	 * @param SymboleOpposant symbole du joueur adverse
	 * @return victoire sur ce coup de jeu ou non
	 */
	public boolean	Victoire(char SymboleACalculer, char SymboleOpposant){
		
		int  poidsAlignement = 0;
		int descendreLigne = 1, colonneGauche = -1, colonneDroite = 1, Stable = 0,monterLigne = -1;
		
		for (int i = 0; i < LARGEUR; i++) {
			for (int j = this.dernierSymbole(i); j < LONGUEUR; j++) {
				//diagonale basse gauche
				poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, i, j,colonneGauche, descendreLigne);
				if (poidsAlignement == POIDS_MAX ) {
					return true;
				}
				//diagonale basse droite
				poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, i, j,colonneDroite, descendreLigne);
				if (poidsAlignement == POIDS_MAX  ) {
					return true;
				} 
				//en bas
				poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, i, j, Stable,descendreLigne);
				if (poidsAlignement == POIDS_MAX ) {
					return true;
				} 
				//à gauche
				poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, i, j,colonneGauche, Stable);
				if (poidsAlignement == POIDS_MAX ) {
					return true;
				} 
				//à droite
				poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, i, j,colonneDroite, Stable);
				if (poidsAlignement == POIDS_MAX ) {
					return true;
				} 
				//en haut
				poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, i, j,colonneGauche, monterLigne);
				if (poidsAlignement == POIDS_MAX ) {
					return true;
				} 
				//diagonale haute gauche
				poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, i, j,colonneDroite, monterLigne);
				if (poidsAlignement == POIDS_MAX  ) {
					return true;
				}
				//diagonale haute gauche
				poidsAlignement = this.parcoursResultatGrille(SymboleACalculer,SymboleOpposant, i, j,Stable, monterLigne);
				if (poidsAlignement == POIDS_MAX ) {
					return true;
				} 
			}	
		}
		return false;
	}
	
	
//----------------------------------------------------------------------------------------
	/**
	 * Parcours la grille de jeu actuelle de différentes manières afin de récupérer le poids
	 * des colonnes de jeu pour un joueur donné (diagonales, colonnes et lignes)
	 * @param joueur joueur actuel
	 * @return poids d'une colonne
	 */	
	public int poids(JoueurAbstrait joueur ) {
		int poids 			= 0;
		int poidsAlignement = 0;
		int descendreLigne = 1, colonneGauche = -1, colonneDroite = 1, Stable = 0,monterLigne = -1; //direction dans la grille
		char SymboleACalculer;
		char SymboleOpposant;
		SymboleACalculer = joueur.getSymbole();


		//récupère le symbole de l'adversaire
		if (this.getTourJoueurSuivant().getSymbole()!=SymboleACalculer ) {
			SymboleOpposant = this.getTourJoueurSuivant().getSymbole();
		}else
		{
			SymboleOpposant = this.getTourDeQuelJoueur().getSymbole();
		} 

		if (this.Victoire(SymboleOpposant, SymboleACalculer)) {
			return AlgoAlphaBeta.MIN;
		}
		
		//calcul du poids pour le joueur actuel
		for (int i = 0; i < LARGEUR; i++) {
			for (int j =this.dernierSymbole(i); j < LONGUEUR; j++) {
				if (j+Grille.CONDITION_VICTOIRE<=LONGUEUR) {
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
				if (j-Grille.CONDITION_VICTOIRE>=0) {
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
		}
		
		return poids;
	}
	

//----------------------------------------------------------------------------------------
	/**
	 * Parcours la grille de jeu en largeur pour noter la colonne en fonction des opportunités de jeu possibles dans une direction donnée
	 * @param symboleJoueur symbole joueur actuel
	 * @param symboleJoueurOpposant symbole adverse
	 * @param colonneDepart
	 * @param lignerDepart
	 * @param deplacementColonne direction à suivre sur les colonnes
	 * @param deplacementLigne direction à suivre sur les lignes 
	 * @return poids d'une colonne
	 */	
	public int parcoursResultatGrille(char symboleJoueur, char symboleJoueurOpposant ,  int colonneDepart, int lignerDepart, int deplacementColonne,
			int deplacementLigne) {
		
		int colonne 		= colonneDepart;
		int ligne 			= lignerDepart;
		int poidsColonne	= POIDS_MAX;
		int tourDeBoucleMax = 0;
		boolean boucler 	= true;
		
		while (boucler && tourDeBoucleMax<CONDITION_VICTOIRE ) {
			try {				
				if (this.tabJeu[colonne][ligne] == symboleJoueur) {
					//symbole du joueur actuel
					poidsColonne = poidsColonne;
				} else if(this.tabJeu[colonne][ligne] == CHAR_NULL) {
					//possibilité de jeu = /2
					poidsColonne = (int) poidsColonne/2 ;
					
				} else {
					//stop car pion adverse
					poidsColonne = 0;
					boucler = !boucler;
				}
			}
			catch(ArrayIndexOutOfBoundsException ex) {
				poidsColonne = 0;
				boucler = false; 
			}  		
 
			//continue le déplacement
			colonne += deplacementColonne;
			ligne += deplacementLigne;
			tourDeBoucleMax++;
		}
		
		if (poidsColonne< 0) {
			poidsColonne = 0;
		}
		return poidsColonne;
	}
	
//----------------------------------------------------------------------------------------
	/**
	 * Affiche le poids de chaque colonne
	 * @return void
	 */
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
	
	
//----------------------------------------------------------------------------------------
	/**
	 * Affiche la grille de jeu
	 * @return grille graphique pour debbug
	 */
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

//----------------------------------------------------------------------------------------
	public static void main(String[] args) {
		
		int victoireJ1=0;
		int victoireJ2=0;
		long debut = System.currentTimeMillis();
		
		for( int nbjeu=0; nbjeu<10;nbjeu++) {
			
		
			boolean win = true;
			Grille grille = new Grille();
			JoueurAbstrait joueurA = new Ordinateur('X', 6);
			joueurA.setNom("1");
			JoueurAbstrait joueurB = new Ordinateur('O', 2);
			joueurB.setNom("2");
			grille.setJoueur1(joueurA);
			grille.setJoueur2(joueurB);
	
			grille.afficheGrille();
			JoueurAbstrait JoueurActuel = grille.getTourDeQuelJoueur();
			JoueurAbstrait JoueurSuivant = grille.getTourJoueurSuivant();
	
			boolean gagner=false; 
			while(!gagner ) {
				
				int colonneAJouer = 0;
				
				colonneAJouer = JoueurActuel.placerChar(grille);				
				grille.insere(colonneAJouer,JoueurActuel.getSymbole());				
					System.out.print("err "+colonneAJouer );
					grille.afficheGrille();				
				
				if (grille.Victoire(JoueurActuel.getSymbole(), JoueurSuivant.getSymbole()) || grille.grillePleine()) {
					grille.afficheGrille();
					gagner =true;
					System.out.print("Bravo c'est gagné "+JoueurActuel.getNom() );
					if(JoueurActuel.getNom()=="1") {
						victoireJ1++;
					}else {victoireJ2++;}
				}
				JoueurActuel = grille.getTourDeQuelJoueur();
				JoueurSuivant = grille.getTourJoueurSuivant();			
			}	
			System.out.print(" victoireJ1 "+victoireJ1 +" victoireJ2 "+victoireJ2 +"  Temps " + (System.currentTimeMillis()-debut) );
		}
		
		System.out.print(" victoireJ1 "+victoireJ1 +" victoireJ2 "+victoireJ2 +"  Temps " + (System.currentTimeMillis()-debut) );	
	}
	
}
