package swing;

import java.util.ArrayList;



public class AlgoAlphaBeta {
	// l'algorithme alpha beta se base sur - infini / + infini ici on prendra les
	// valeurs max de min et max value
	// INT_MAX Maximum value for a variable of type int. 2147483647
	public static final int MAX = Integer.MAX_VALUE;
	// INT_MIN Minimum value for a variable of type int. -2147483648
	public static final int MIN = Integer.MIN_VALUE;

	private int niveau = 4;

	public int getNiveau() {
		return niveau;
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

		// on va faire le le calcul pouur chaque colonne de la grille
		for (int i = 0; i < Grille.LARGEUR; i++) {
			// on regarde si la col est pleine si c'est plein pas besoin de copier la grille
			// pour cette colonne donc next
			
			if (!grille.colPleine(i) ) {
			//	System.out.print("\n Colonne ----------------" + i );
				// copie de la grille (grille courante a la boucle for)
				Grille copieDeLaGrille = grille.Copie();
				
				copieDeLaGrille.insere(i, joueur.getSymbole());
				// on recupére la profondeur de l'arbre qu'on va gen (pour l'humain l'aide de
				// profondeur 4 et pour L'IA son niveau)
				int profondeurArbre = copieDeLaGrille.getTourDeQuelJoueur().getNiveau();

				int valeurDeJeuCourante = min(joueur, copieDeLaGrille, alphaMin, betaMax, profondeurArbre);
		
				if (valeurDeJeuCourante == valeurDeJeu) {
					colAjouer = i;
				} else if (valeurDeJeuCourante > valeurDeJeu) {
					valeurDeJeu = valeurDeJeuCourante;
					colAjouer = i;
				}
				poidsColonne[i] = valeurDeJeuCourante;
				
			} else {
				poidsColonne[i] = alphaMin;
						
			}
			
		}
		System.out.print("\n Colonne ----------------" +poidsColonne.toString() );
		grille.setPoidsColonnes(poidsColonne);
		return colAjouer;
	}

// algo min de alpha beta, ici on applique la valeur min
	private int min(JoueurAbstrait joueur, Grille grille, double alpha, double beta, int occurence) {
	//	System.out.print("\n min  "+  occurence+' '+ grille.getNombreDeTour()+"+ alpha/beta +"+alpha +' '+ beta);
		if (occurence > 0) {
			int valeurDeJeu = MAX;
			for (int i = 0; i < grille.LARGEUR; i++) {
				if (!grille.colPleine(i)) {
					Grille copieDeLaGrille = grille.Copie();
					occurence--;
					copieDeLaGrille.insere(i, copieDeLaGrille.getTourDeQuelJoueur().getSymbole());

					valeurDeJeu = Math.min(valeurDeJeu, this.max(joueur, copieDeLaGrille, alpha, beta, occurence));
					// doit on tronquer la branche ?
					if (alpha >= valeurDeJeu) {
						// ici on tronque la branche on ira donc pas plus loin dans min
						return valeurDeJeu;
					}
					beta = Math.min(beta, valeurDeJeu);
				}
			}
			return valeurDeJeu;
		} else {
			int result = grille.poids(joueur);
			System.out.print("\n min result  "+ result);
			return result;
		}
	}

	// algo max de alpha beta, ici on applique la valeur max
	private int max(JoueurAbstrait joueur, Grille grille, double alpha, double beta, int occurence) {
	//	System.out.print("\n max  "+  occurence+' '+ grille.getNombreDeTour()+"+ alpha/beta +"+alpha +' '+ beta);
		if (occurence > 0) {
			int valeurDeJeu = MIN;
			for (int i = 0; i < grille.LARGEUR; i++) {
				if (!grille.colPleine(i)) {
					// init des variables
					Grille copieDeLaGrille = grille.Copie();
					occurence--;
					// copie insersion dans les grilles
					copieDeLaGrille.insere(i, copieDeLaGrille.getTourDeQuelJoueur().getSymbole());
					valeurDeJeu = Math.max(valeurDeJeu, this.min(joueur, copieDeLaGrille, alpha, beta, occurence));
					// doit on tronquer la branche ?
					if (valeurDeJeu >= beta) {
						// ici on tronque la branche on ira donc pas plus loin dans max
						return valeurDeJeu;
					}
					alpha = Math.max(alpha, valeurDeJeu);
				}
			}
			return valeurDeJeu;
		} else {
			int result = grille.poids(joueur);
			//System.out.print("\n max result  "+ result);
			return result;
		}
	}

	public int appellealphaBetaNega(Grille grilleACalculer) {
		return -1;
	}

	public static void main(String[] args) {
		long debut = System.currentTimeMillis();
		boolean win = true;
		Grille grille = new Grille();
		JoueurAbstrait joueurA = new Humain('x', 4);
		joueurA.setNom("Joueur 1 ");
		JoueurAbstrait joueurB = new Ordinateur('O', 4);
		joueurB.setNom("Joueur 2 ");
		grille.setJoueur1(joueurA);
		grille.setJoueur2(joueurB);
		//grille.insere(1,joueurA.getSymbole());	
		int numeroColonneOuJouer = joueurB.placerChar(grille);
		grille.afficheGrille();
		System.out.print("\n numeroColonneOuJouer  "+  numeroColonneOuJouer);
		grille.insere(numeroColonneOuJouer,joueurA.getSymbole());	
		grille.afficheGrille();
		System.out.println("\n"+ (System.currentTimeMillis()-debut));
	/*	while (win) {
		
			grille.afficheGrille();
			JoueurAbstrait JoueurActuel = grille.getTourDeQuelJoueur();
			JoueurAbstrait JoueurSuivant = grille.getTourJoueurSuivant();
			
					int numeroColonneOuJouer = JoueurActuel.placerChar(grille);
			grille.insere(numeroColonneOuJouer,JoueurActuel.getSymbole());	
			
		}*/
	}

}
