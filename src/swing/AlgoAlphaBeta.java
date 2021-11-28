package swing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;



public class AlgoAlphaBeta {
	// l'algorithme alpha beta se base sur - infini / + infini ici on prendra les
	// valeurs max de min et max value
	// INT_MAX Maximum value for a variable of type int. 2147483647
	public static final int MAX = Integer.MAX_VALUE;
	// INT_MIN Minimum value for a variable of type int. -2147483648
	public static final int MIN = Integer.MIN_VALUE;
	private static final int PROFONDEUR_EXPLORATION_ALPHA_BETA = 4;
	private int niveau = 4;

	public int getNiveau() {
		return 4;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	public AlgoAlphaBeta(int lvl) {
		this.niveau = lvl;
	}

	public int ouJouer(JoueurAbstrait joueur, Grille grille) {

		// ArrayList<Integer> colonnesJouables = grille.colonesJouables();
		double valeurDeJeu = MIN;
		int alphaMin = MIN, betaMax = MAX, colAjouer = 0;
		int poidsColonne[] = new int[Grille.LARGEUR];
			List<Integer> PoissibiliteDeJeu = new ArrayList<Integer>();
		int profondeurArbre = grille.getTourDeQuelJoueur().getNiveau();
		// on va faire le le calcul pouur chaque colonne de la grille
		for (int i = 0; i < Grille.LARGEUR; i++) {
			// on regarde si la col est pleine si c'est plein pas besoin de copier la grille
			// pour cette colonne donc next
			
			if (!grille.colPleine(i) ) {
			//	System.out.print("\n Colonne ----------------" + i );
				// copie de la grille (grille courante a la boucle for)
				Grille copieDeLaGrille = grille.Copie();				
				
				// on recupére la profondeur de l'arbre qu'on va gen (pour l'humain l'aide de
				// profondeur 4 et pour L'IA son niveau)
				
				copieDeLaGrille.insere(i, joueur.getSymbole());
			//	System.out.print("\n base " +joueur.getSymbole());
				
				int valeurDeJeuCourante = min(joueur, copieDeLaGrille, alphaMin, betaMax, PROFONDEUR_EXPLORATION_ALPHA_BETA);

				if (valeurDeJeuCourante == valeurDeJeu) {
					PoissibiliteDeJeu.add(i);
				} else if (valeurDeJeuCourante > valeurDeJeu) {
					valeurDeJeu = valeurDeJeuCourante;
					PoissibiliteDeJeu.clear();
					PoissibiliteDeJeu.add(i);
				}
				poidsColonne[i] = valeurDeJeuCourante;
								
			} else {
			//	System.out.print("\n outofbound  colonne  ----------------"  );
				poidsColonne[i] = alphaMin;						
			}
			
		}
		System.out.print("\n Colonne ----------------"+Arrays.toString(poidsColonne) );
		Collections.shuffle(PoissibiliteDeJeu);
		grille.setPoidsColonnes(poidsColonne);
		return PoissibiliteDeJeu.get(0);
	}

// algo min de alpha beta, ici on applique la valeur min
	private int min(JoueurAbstrait joueur, Grille grille, int alpha, int beta, int occurence) {
	//	System.out.print("\n min  "+  occurence+' '+ grille.getNombreDeTour()+"+ alpha/beta +"+alpha +' '+ beta);

		if (occurence >= 0) {
		//s	System.out.print("\n occurence  "+ occurence);
			int valeurDeJeu = MAX;
			for (int i = 0; i < grille.LARGEUR; i++) {
				if (!grille.colPleine(i)) {
					Grille copieDeLaGrille = grille.Copie();
					//occurence--;
					//System.out.print("\n min " +copieDeLaGrille.getTourDeQuelJoueur().getSymbole());
					copieDeLaGrille.insere(i, copieDeLaGrille.getTourDeQuelJoueur().getSymbole());

					valeurDeJeu = Math.min(valeurDeJeu, this.max(joueur, copieDeLaGrille, alpha, beta, occurence-1));
					// doit on tronquer la branche ?
					if ( alpha >=valeurDeJeu  ) {
						// ici on tronque la branche on ira donc pas plus loin dans min
						//break;
						return valeurDeJeu;
					}
					beta = Math.min(beta, valeurDeJeu);
				}
			}
			return valeurDeJeu;
		} else {

			int result = grille.poids(joueur);
			//System.out.print("\n min result  "+ result);

			return result;
		}
	}

	// algo max de alpha beta, ici on applique la valeur max
	private int max(JoueurAbstrait joueur, Grille grille, int alpha, int beta, int occurence) {
	//	System.out.print("\n max  "+  occurence+' '+ grille.getNombreDeTour()+"+ alpha/beta +"+alpha +' '+ beta);
 
		if (occurence >= 0) {
		//	System.out.print("\n max occurence  "+ occurence);
			int valeurDeJeu = MIN;
			for (int i = 0; i < grille.LARGEUR; i++) {
				if (!grille.colPleine(i)) {
					// init des variables
					Grille copieDeLaGrille = grille.Copie();
				//	System.out.print("\n max " +copieDeLaGrille.getTourDeQuelJoueur().getSymbole());
					// copie insersion dans les grilles
					copieDeLaGrille.insere(i, copieDeLaGrille.getTourDeQuelJoueur().getSymbole());
					valeurDeJeu = Math.max(valeurDeJeu, this.min(joueur, copieDeLaGrille, alpha, beta, occurence-1));
					// doit on tronquer la branche ?o
					if (valeurDeJeu >= beta) {
						// ici on tronque la branche on ira donc pas plus loin dans max
						//break;
						return valeurDeJeu;
					}
					alpha = Math.max(alpha, valeurDeJeu);
				}
			}
			return valeurDeJeu;
		} else {
			int result = grille.poids(joueur);
			//grille.afficheGrille();
			//System.out.print("\n max result  "+ result);
			return result;
		}
	}


	public static void main(String[] args) {
		
		long debut = System.currentTimeMillis();
		boolean win = true;
		Grille grille = new Grille();
		JoueurAbstrait joueurA = new Ordinateur('X', 4);
		joueurA.setNom(" Humain");
		JoueurAbstrait joueurB = new Ordinateur('O', 4);
		joueurB.setNom("Ordinateur ");
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
				System.out.print("Bravo c'est gagné "+JoueurActuel.getSymbole() );
			}else {
			grille.afficheGrille();
			 JoueurActuel = grille.getTourDeQuelJoueur();
			 JoueurSuivant = grille.getTourJoueurSuivant();
			
		}	
		
	}	
		
		
		/*
		
		long debut = System.currentTimeMillis();
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
		System.out.print("\n numeroColonneOuJouer  "+  numeroColonneOuJouer +" tour "+ grille.getNombreDeTour());*/
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
