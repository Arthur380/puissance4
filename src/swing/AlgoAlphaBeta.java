/**
 * Partie algorithme de notre projet Alpha-Beta
 * Algorithme basé sur Min-Max avec élagague des branches qui
 * ne conduisent pas à l'objectif de départ (pour un gain de temps)
 */

package swing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class AlgoAlphaBeta {
	
	//valeurs minimale et maximale de Java pour représenter les infinis
	public static final int MAX = Integer.MAX_VALUE; // 2147483647
	public static final int MIN = Integer.MIN_VALUE; //-2147483648
	
	
	private int niveau ;

	public int getNiveau() {
		return 4;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

	public AlgoAlphaBeta(int lvl) {
		this.niveau = lvl;
	}

	
//----------------------------------------------------------------------------------------
	/**Création d'une copie de la grille courante et insertion d'un jeton pour chaque colonne
	 * 
	 * @param joueur joueur actuel
	 * @param grille grille de jeu actuelle
	 * 
	 * @return numéro de colonne au plus grand poids
	 */
	public int ouJouer(JoueurAbstrait joueur, Grille grille) {

		double 			valeurDeJeu 		= MIN;
		int 			alphaMin 			= MIN;
		int 			betaMax 			= MAX;
		int 			poidsColonne[] 	  	= new int[Grille.LARGEUR];
		List<Integer> 	PoissibiliteDeJeu 	= new ArrayList<Integer>();
		int 			colGagnante 	  	= verifCoupGagnant( joueur,  grille);
		
		//si on a une colonne gagnante, on la retourne comme résultat direcement
		if (colGagnante>=0) 
		{
			return colGagnante;
		}
		
		//parcour de la grille en largeur = par colonne
		for (int i = 0; i < Grille.LARGEUR; i++) {
			
			//si la colonne n'est pas pleine
			if (!grille.colPleine(i) ) {
				
				//copie de la grille courante
				Grille copieDeLaGrille = grille.Copie();				
				//insertion d'un jeton dans la colonne 
				copieDeLaGrille.insere(i, joueur.getSymbole());
				
				int valeurDeJeuCourante;
				
				//si victoire ? calcul du MIN : sinon calcul MAX
				if (!copieDeLaGrille.Victoire(joueur.getSymbole(),grille.getTourDeQuelJoueur().getSymbole()))
				{ 
					valeurDeJeuCourante = min(joueur, copieDeLaGrille, alphaMin, betaMax, this.niveau);}
				else {
					valeurDeJeuCourante =AlgoAlphaBeta.MAX;
				}
					
				//si la valeur calculée = MIN alors la colonne peut être jouée
				if (valeurDeJeuCourante == valeurDeJeu) {
					PoissibiliteDeJeu.add(i);
				} else if (valeurDeJeuCourante > valeurDeJeu) {
					valeurDeJeu = valeurDeJeuCourante;
					PoissibiliteDeJeu.clear();
					PoissibiliteDeJeu.add(i);
				}
				
				//colonne suivante
				poidsColonne[i] = valeurDeJeuCourante;
								
			} else {
				//si la colonne est pleine, directement MIN
				poidsColonne[i] = alphaMin;						
			}
		}
		//retours de la liste des poids des colonnes
		System.out.print("\n Colonne ----------------"+Arrays.toString(poidsColonne) );
		
		//
		Collections.shuffle(PoissibiliteDeJeu);
		grille.setPoidsColonnes(poidsColonne);
		return PoissibiliteDeJeu.get(0);
	}
	
	
//----------------------------------------------------------------------------------------
	/**Numéro de colonne si victoire au prochain coup
	 * 
	 * @param joueur joueur actuel
	 * @param grille grille de jeu actuelle
	 * 
	 * @return int 
	 */
	private int verifCoupGagnant(JoueurAbstrait joueur, Grille grille) {
		
		//parcour de la grille en largeur = par colonne
		for (int i = 0; i < Grille.LARGEUR; i++) {
			
			//si la colonne n'est pas pleine
			if (!grille.colPleine(i)) {
				
				Grille copieDeLaGrille = grille.Copie();
				copieDeLaGrille.insere(i, joueur.getSymbole());
				
				if (copieDeLaGrille.Victoire(joueur.getSymbole(),grille.getTourDeQuelJoueur().getSymbole())) {
					return i;
				}
			}		
		}
		return -1;
	}

	
//----------------------------------------------------------------------------------------
	/**Calcul du MIN dans notre algo AlphaBeta
	 * 
	 * @param joueur joueur actuel
	 * @param grille grille de jeu actuelle
	 * @param alpha MIN
	 * @param beta MAX
	 * @param occurence profondeur de recherche
	 * 
	 * @return valeur de MIN
	 */
	private int min(JoueurAbstrait joueur, Grille grille, int alpha, int beta, int occurence) {
		
		//répétition pour une profondeure donnée
		if (occurence >= 0) {
			
			int valeurDeJeu = MAX;
			
			//parcour de la grille en largeur = par colonne
			for (int i = 0; i < grille.LARGEUR; i++) {
				
				if (!grille.colPleine(i)) {
					//création d'une copie de la grille actuelle et insérsion d'un jeton pour chaque colonne
					Grille copieDeLaGrille = grille.Copie();
					copieDeLaGrille.insere(i, copieDeLaGrille.getTourDeQuelJoueur().getSymbole());
					
					//appel récursif de la partie MAX dans MIN
					valeurDeJeu = Math.min(valeurDeJeu, this.max(joueur, copieDeLaGrille, alpha, beta, occurence-1));
					
					//vérification si besoin d'élaguer la branche
					if ( alpha >=valeurDeJeu) {
						//si branche tronquée, arrêt des calculs
						return valeurDeJeu;
					}
					//calcul du MIN
					beta = Math.min(beta, valeurDeJeu);
				}
			}
			
			return valeurDeJeu;
			
		} else {
			int result = grille.poids(joueur);
			return result;
		}
	}
	

//----------------------------------------------------------------------------------------
	/**Calcul du MAX dans notre algo AlphaBeta
	 * 
	 * @param joueur joueur actuel
	 * @param grille grille de jeu actuelle
	 * @param alpha MIN
	 * @param beta MAX
	 * @param occurence profondeur de recherche
	 * 
	 * @return valeur de MAX
	 */
	private int max(JoueurAbstrait joueur, Grille grille, int alpha, int beta, int occurence) {
		
		if (occurence >= 0) { 
			int valeurDeJeu = MIN;
			
			for (int i = 0; i < grille.LARGEUR; i++) {
				
				if (!grille.colPleine(i)) {
					//création d'une copie de la grille actuelle et insérsion d'un jeton pour chaque colonne
					Grille copieDeLaGrille = grille.Copie();
					copieDeLaGrille.insere(i, copieDeLaGrille.getTourDeQuelJoueur().getSymbole());
					
					//appel récursif de la partie MIN dans MAX
					valeurDeJeu = Math.max(valeurDeJeu, this.min(joueur, copieDeLaGrille, alpha, beta, occurence-1));
					
					//vérification si besoin d'élaguer la branche
					if (valeurDeJeu >= beta) {
						//si branche tronquée, arrêt des calculs
						return valeurDeJeu;
					}
					//calcul du MAX
					alpha = Math.max(alpha, valeurDeJeu);
				}
			}
			
			return valeurDeJeu;
			
		} else {
			int result = grille.poids(joueur);
			return result;
		}
	}


//----------------------------------------------------------------------------------------
	/**Méthode pour les statistiques du projet, création d'une partie entre un Humain et un Ordinateur
	 * 
	 * @return void
	 */
	public static void main(String[] args) {
		
		Grille 	grille 	= new Grille();
		
		JoueurAbstrait joueurA = new Ordinateur('X', 8);
		joueurA.setNom(" Humain");
		JoueurAbstrait joueurB = new Ordinateur('O', 4);
		joueurB.setNom("Ordinateur ");
		grille.setJoueur1(joueurA);
		grille.setJoueur2(joueurB);

		grille.afficheGrille();
		JoueurAbstrait JoueurActuel = grille.getTourDeQuelJoueur();
		JoueurAbstrait JoueurSuivant = grille.getTourJoueurSuivant();
		
		boolean gagner=false; 
		while( !gagner ) {
			int colonneAJouer;
			
			colonneAJouer = JoueurActuel.placerChar(grille);
			grille.insere(colonneAJouer,JoueurActuel.getSymbole());
			
			if (grille.Victoire(JoueurActuel.getSymbole(), JoueurSuivant.getSymbole())) {
				grille.afficheGrille();
				gagner=true;
				System.out.print("Bravo c'est gagné "+JoueurActuel.getSymbole() );
				
			} else {
				
				grille.afficheGrille();
				JoueurActuel = grille.getTourDeQuelJoueur();
				JoueurSuivant = grille.getTourJoueurSuivant();
			}	
		}	
	}

	
}
