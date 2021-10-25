package swing;

import java.util.ArrayList;

public class AlgoAlphaBeta {
	// l'algorithme alpha beta se base sur - infini / + infini ici on prendra les valeurs max de min et max value
	//INT_MAX	Maximum value for a variable of type int.	2147483647
	public static final int MAX = Integer.MAX_VALUE;
	//INT_MIN	Minimum value for a variable of type int.	-2147483648
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

		ArrayList<Integer> colonnesJouables = grille.colonesJouables();
		double valeurDeJeu = MIN;
		int alphaMin  = MIN;
		int betaMax  = MAX;
		// on va faire le le calcul pouur chaque colonne de la grille
		for (int i = 1; i <= Grille.LARGEUR; i++) {
			// on regarde si la col est pleine si c'est plein pas besoin de copier la grille
			// pour cette colonne donc next
			if (grille.colPleine(i) != true) {
				// copie de la grille (grille courante a la boucle for)
				Grille copieDeLaGrille = grille.Copie();

				copieDeLaGrille.insere(i, joueur.getSymbole());
			// on recupére la profondeur de l'arbre qu'on va gen (pour l'humain l'aide de profondeur 4 et pour L'IA son niveau)
				int profondeurArbre = grille.getTourDeQuelJoueur().getNiveau();
				double valeurDeJeuCourante = min(joueur, copieDeLaGrille,alphaMin, betaMax, profondeurArbre);
				if (valeurDeJeuCourante == valeurDeJeu) {
					colonnesJouables.add(i);
				} else if (valeurDeJeuCourante > valeurDeJeu) {
					colonnesJouables.clear();
					valeurDeJeu = valeurDeJeuCourante;
					colonnesJouables.add(i);
				}
			}

		}

		int numeroDeColonneAJouer = (int) (Math.random() * colonnesJouables.size());

		return colonnesJouables.get(numeroDeColonneAJouer);
	}

// algo min de alpha beta, ici on applique la valeur min
	private double min(JoueurAbstrait joueur, Grille grille, double alpha, double beta, int occurence) {
		if (occurence != 0) {
			double valeurDeJeu = MAX;
			for (int i = 1; i <= Grille.LARGEUR; i++) {

				if (!grille.colPleine(i)) {
					Grille copieDeLaGrille = grille.copie();
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
			return grille.poids;
		}
	}

	//algo max de alpha beta, ici on applique la valeur max 
	private double max(JoueurAbstrait joueur, Grille grille, double alpha, double beta, int occurence) {
		if (occurence != 0) {
			double valeurDeJeu = MIN;
			for (int i = 1; i <= Grille.LARGEUR; i++) {

				if (!grille.colPleine(i)) {
					//init des variables 
					Grille copieDeLaGrille = grille.Copie();
					occurence--;
					// copie insersion dans les grilles   
					copieDeLaGrille.insere(i, copieDeLaGrille.getTourDeQuelJoueur().getSymbole());
					valeurDeJeu = Math.max(valeurDeJeu, this.min(joueur, copieDeLaGrille, alpha, beta, occurence));
					// doit on tronquer la branche ?
					if (valeurDeJeu >= beta)
						//ici on tronque la branche on ira donc pas plus loin dans max
						return valeurDeJeu; 
					}
					alpha = Math.max(alpha, valeurDeJeu);
				}
			
			return valeurDeJeu;
		} else {
			return grille.poids;
		}
	}

	public int appellealphaBetaNega(Grille grilleACalculer) {

		return -1;
	}

	/*
	 * procedure AlphaBetaNega(s : situation, α : entier, β : entier) if
	 * estFeuille(s) then retourner(h(s)) else for all s 0 ∈ s.successeurs do β ←
	 * max(α, −ALPHABETANEGA(s 0 , −β, −α)) if α ≥ β then retourner(α) end if α ←
	 * max(α, β) end for retourner(α) end if end proceduree
	 */
	public int alphaBetaNega(Grille grilleACalculer, int alpha, int beta, int profondeur) {

		// if(P.nbMoves() == Grille.LARGEUR_GRILLE*Grille.LONGUEUR_GRILLE)
		// return 0;

		for (int i = 0; i < grilleACalculer.LARGEUR; i++) {
			if (grilleACalculer.colPleine(i)
					&& grilleACalculer.chercheAlignement4(i, grilleACalculer.joueurCourant().getSymbole()))
				return (grilleACalculer.LARGEUR * Grille.LONGUEUR + 1 - grilleACalculer.getNombreDeTour()) / 2;
		}
		int max = (grilleACalculer.LARGEUR * Grille.LONGUEUR - 1 - grilleACalculer.getNombreDeTour()) / 2; // upper
																											// bound
																											// of
																											// our
																											// score
																											// as
																											// we
																											// cannot
																											// win
																											// immediately
		if (beta > max) {
			beta = max; // there is no need to keep beta above our max possible score.
			if (alpha >= beta)
				return beta; // prune the exploration if the [alpha;beta] window is empty.
		}

		for (int x = 0; x < grilleACalculer.LARGEUR; x++) // compute the score of all possible next move and keep
															// the best one
			if (grilleACalculer.colPleine(x)) {
				Grille copieDeLaGrille = grilleACalculer.Copie();
				copieDeLaGrille.insere(x, copieDeLaGrille.joueurCourant().getSymbole()); // It's opponent turn in P2
																							// Grille after current
																							// player plays x column.
				int score = -alphaBetaNega(copieDeLaGrille, -beta, -alpha); // explore opponent's score within
																			// [-beta;-alpha] windows:
				// no need to have good precision for score better than beta (opponent's score
				// worse than -beta)
				// no need to check for score worse than alpha (opponent's score worse better
				// than -alpha)

				if (score >= beta)
					return score; // prune the exploration if we find a possible move better than what we were
									// looking for.
				if (score > alpha)
					alpha = score; // reduce the [alpha;beta] window for next exploration, as we only
									// need to search for a Grille that is better than the best so far.
			}

		return alpha;
	}

}
