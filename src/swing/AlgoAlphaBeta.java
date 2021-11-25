package swing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;



public class AlgoAlphaBeta {
	// l'algorithme alpha beta se base sur - infini / + infini ici on prendra les
	// valeurs max de min et max value
	// INT_MAX Maximum value for a variable of type int. 2147483647
	public static final int MAX = Integer.MAX_VALUE;
	// INT_MIN Minimum value for a variable of type int. -2147483648
	public static final int MIN = Integer.MIN_VALUE;

	//ALICE
	//CETTE PARTIE NE SERT PAS A RECUPERER LE NIVEAU !!
	private int niveau = 4;
	public int getNiveau() {
		return niveau;
	}
	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}
	////////////////
	
	public AlgoAlphaBeta(int lvl) {
		this.niveau = lvl;
	}

	public int ouJouer(JoueurAbstrait joueur, Grille grille) {

		double valeurDeJeu = MIN;
		int alphaMin = MIN, betaMax = MAX, colAjouer = 0;
		int poidsColonne[] = new int[Grille.LARGEUR];
		int profondeurArbre = grille.getTourDeQuelJoueur().getNiveau();
		System.out.print("\n profondeurArbre    ----------------"+profondeurArbre );
		
		int poidsActuelleCol = 0;
		int poidsAncienneCol =0;
		
		ArrayList<Integer> array_L = new ArrayList<Integer>();
		
		for (int i = 0; i < Grille.LARGEUR; i++) {
			
			if (!grille.colPleine(i) ) {
				
				//
				//INSERER UN PION DANS UNE COLONNE (0 à 6)
				Grille copieDeLaGrille = grille.Copie();
				// prof de l'arbre nous = 4 / IA = difficulité
				copieDeLaGrille.insere(i, joueur.getSymbole());
				//System.out.print("\n profondeurArbre    ----------------"+profondeurArbre );
				
				//
				//CALCUL LA VALEUR MIN POUR CETTE GRILLE
				int valeurDeJeuCourante = min(joueur, copieDeLaGrille, alphaMin, betaMax, profondeurArbre);

				//
				//DEICISION DE COLONNE A JOUER *si poids=min / si poid actu > poid avant
				poidsActuelleCol = grille.poids(joueur, i);
				
				if (valeurDeJeuCourante == valeurDeJeu) {
					colAjouer = i;
				}
				if(poidsActuelleCol > poidsAncienneCol)
				{
					colAjouer = i;
				}
				
				poidsAncienneCol = poidsActuelleCol;
				
				//mise dans le tableau
				poidsColonne[i] = poidsActuelleCol;
				array_L.add(poidsActuelleCol);
								
			} else {
				System.out.print("\n outofbound  colonne  ----------------"  );
				poidsColonne[i] = alphaMin;						
			}
			
		}
		
		System.out.print("\n Colonne ----------------"+Arrays.toString(poidsColonne) );
		
			 
	   // Retourner la plus grande valeur
	    Object obj = Collections.max(array_L);
	    System.out.println("\n COL AVEC POIDS MAX ==" + obj);
		for(int i = 0; i < array_L.size(); i++)
		{
			if(array_L.get(i) == obj)
			{
				int col_jouable = i+1;
				System.out.println("\n COL A JOUER ==" + col_jouable); 
			}
		}
		
		
		grille.afficheGrille();
		return colAjouer;
	}
	
 

// algo min de alpha beta, ici on applique la valeur min
	private int min(JoueurAbstrait joueur, Grille grille, int alpha, int beta, int occurence) {
	//	System.out.print("\n min  "+  occurence+' '+ grille.getNombreDeTour()+"+ alpha/beta +"+alpha +' '+ beta);

		if (occurence > 0) {
			System.out.print("\n min occurence  "+ occurence);
			int valeurDeJeu = MAX;
			for (int i = 0; i < grille.LARGEUR; i++) {
				if (!grille.colPleine(i)) {
					Grille copieDeLaGrille = grille.Copie();
					//occurence--;
					copieDeLaGrille.insere(i, copieDeLaGrille.getTourDeQuelJoueur().getSymbole());

					valeurDeJeu = Math.min(valeurDeJeu, this.max(joueur, copieDeLaGrille, alpha, beta, occurence-1));
					// doit on tronquer la branche ?
					if (valeurDeJeu >= alpha ) {
						// ici on tronque la branche on ira donc pas plus loin dans min
						break;
						//return valeurDeJeu;
					}
					beta = Math.min(beta, valeurDeJeu);
				}
			}
			return valeurDeJeu;
		}
		return 0;
	}
	// algo max de alpha beta, ici on applique la valeur max
	private int max(JoueurAbstrait joueur, Grille grille, int alpha, int beta, int occurence) {
	//	System.out.print("\n max  "+  occurence+' '+ grille.getNombreDeTour()+"+ alpha/beta +"+alpha +' '+ beta);
 
		if (occurence > 0) {
			System.out.print("\n MAX occurence  "+ occurence);
			int valeurDeJeu = MIN;
			for (int i = 0; i < grille.LARGEUR; i++) {
				if (!grille.colPleine(i)) {
					// init des variables
					Grille copieDeLaGrille = grille.Copie();
					
					// copie insersion dans les grilles
					copieDeLaGrille.insere(i, copieDeLaGrille.getTourDeQuelJoueur().getSymbole());
					valeurDeJeu = Math.max(valeurDeJeu, this.min(joueur, copieDeLaGrille, alpha, beta, occurence-1));
					// doit on tronquer la branche ?o
					if (valeurDeJeu >= beta) {
						// ici on tronque la branche on ira donc pas plus loin dans max
						break;
					//	return valeurDeJeu;
					}
					alpha = Math.max(alpha, valeurDeJeu);
				}
			}
			return valeurDeJeu;
		}
		return 0;
		//return grille.poids;
	}

	public int appellealphaBetaNega(Grille grilleACalculer) {
		return -1;
	}

	public static void main(String[] args) {
		
		/*long debut = System.currentTimeMillis();
		boolean win = true;
		Grille grille = new Grille();
		JoueurAbstrait joueurA = new Humain('x', 4);
		joueurA.setNom("Joueur 1 ");
		JoueurAbstrait joueurB = new Ordinateur('O', 4);
	
			
		joueurB.setNom("Joueur 2 ");
		grille.setJoueur1(joueurA);
		grille.setJoueur2(joueurB);
		grille.afficheGrille();
		System.out.print(" tour "+ grille.getNombreDeTour());
	
		int numeroColonneOuJouer =  joueurA.placerChar(grille) ;
		System.out.print("\n numeroColonneOuJouer  "+numeroColonneOuJouer);

		grille.insere(4, grille.getTourDeQuelJoueur().getSymbole());
		grille.insere(4, grille.getTourDeQuelJoueur().getSymbole());
		grille.insere(4, grille.getTourDeQuelJoueur().getSymbole());
		grille.insere(4, grille.getTourDeQuelJoueur().getSymbole());
		//grille.insere(1,joueurA.getSymbole());	
		
		grille.afficheGrille();
		System.out.print("\n numeroColonneOuJouer  "+  numeroColonneOuJouer +" tour "+ grille.getNombreDeTour());
	/*	grille.insere(numeroColonneOuJouer,joueurA.getSymbole());	
		grille.afficheGrille();
		System.out.println("\n"+ (System.currentTimeMillis()-debut));*/ 
	/*	while (win) {
		
			grille.afficheGrille();
			JoueurAbstrait JoueurActuel = grille.getTourDeQuelJoueur();
			JoueurAbstrait JoueurSuivant = grille.getTourJoueurSuivant();
			
					int numeroColonneOuJouer = JoueurActuel.placerChar(grille);
			grille.insere(numeroColonneOuJouer,JoueurActuel.getSymbole());	
			
		}*/
	}

}
