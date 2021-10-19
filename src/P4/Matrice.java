/**
 * 
 */
package P4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



/**
 * Une matrice est un ensemble de lignes et de colonnes contenant des élements du même types.  Cette matrice à une largeur et longueur, c'est pourquoi toute les lignes et les colonnes de la matrice ont la même longueur les une par rapport aux autres.
 * Une matrice est donc une grille donc chaque case est du même type. De plus chaque case est numérotée selon l'axe des abscices de 1 à largeur, et selon l'axe des ordonnées de 1 à longueur. 
 * Cette classe fournis quelques opérations utiles afin de récupérer plus facilement un ensemble d'élements
 * @author Ronan Quintin
 * @param <A>
 *
 */
public class Matrice<A> {


	private static Journaliseur journaliseur = UsineJournaliseur.CreerJournaliseur();

	private HashMap< Coordonnee, A> cellules;



	private int largeur;

	private int longueur;

	/**
	 * Créer une matrice de la largeur et de la longueur spécifiée.
	 * @param largeur La largeur de la matrice
	 * @param longueur La longueur de la matrice
	 */
	public Matrice(int largeur, int longueur) {

		// Stockages des paramètres largeur / longueur
		this.largeur = largeur;
		this.longueur = longueur;

		// Début de la création des cellules
		this.cellules = new HashMap< Coordonnee, A>(this.largeur > this.longueur ? this.largeur : this.longueur);

		try {
			// On créer tous les index et on les places dans les bonnes hashmaps
			for(int i = 1; i <= this.largeur; i++){
				for(int j = 1; j <= this.longueur; j++){
					// Création des valeurs dans les maps
					this.affecteValeur(i, j, null);

				}
			}
		} catch (HorsIndexException e) {
			String message = "Une erreur est apparu lors de l'initialisation de la matrice. Voici le message de cette erreur : " + e.getMessage();
			journaliseur.erreur(message);
		}
	}


	/**
	 * Affecte la valeur à l'intersection de la colonne i et de la ligne h de la matrice. On à donc M(i,j) = valeur.
	 * Il faut respecter les conditions suivantes : 1 <= i <= largeur et 1 <= j <= longueur .
	 * @param i Index de la colonne
	 * @param j Index de la ligne
	 * @param valeur Valeur à affecter 
	 * @throws HorsIndexException Si i ou j ne respecte pas les bornes de la matrice
	 */

	public void affecteValeur(int i, int j, A valeur) throws HorsIndexException{

		if( i > this.largeur || i < 1 || j > this.longueur || j < 1){
			String message = "Les index de colonne et de ligne fournie ( i = "+ i +", j = " + j +" ) lors de la tentative d'affectation de la valeur " + valeur + " sont définies en dehors des limites de largeur et de longueur de la matrice.";
			journaliseur.erreur(message);
			throw new HorsIndexException(message);
		}

		// Affectation dans la ligne colonne
		Coordonnee coordonnee = new Coordonnee(i, j);
		this.cellules.put(coordonnee, valeur);
	}

	/**
	 * Récupere la valeur associée à l'index de colonne i et de ligne j fournis en paramètre.
	 * Il faut respecter les conditions suivantes : 1 <= i <= largeur et 1 <= j <= longueur .
	 * @param i Index de la colonne
	 * @param j Index de la ligne
	 * @return La valeur associée à l'index (i,j)
	 * @throws HorsIndexException Si i ou j ne respecte pas les bornes de la matrice
	 */
	public A recupereValeur(int i, int j) throws HorsIndexException{
		if( i > this.largeur || i < 1 || j > this.longueur || j < 1){
			String message = "Les index de colonne et de ligne fournie ( i = "+ i +", j = " + j +" ) lors de la tentative de récupérer la valeur associée sont définies en dehors des limites de largeur et de longueur de la matrice.";
			throw new HorsIndexException(message);
		}
		return this.cellules.get(new Coordonnee(i, j));
	}



	/**
	 * Recupère la colonne numéroté par l'index de colonne i donné
	 * @param i l'index de la colonne à récupérer
	 * @return la colonne demandée
	 * @throws HorsIndexException 
	 */
	public List<A> recupereColonne(int i) throws HorsIndexException{
		List<A> resultat = new ArrayList<A>();
		try{

			for(int j=1; j <= this.longueur; j++){
				resultat.add(j, this.recupereValeur(i, j));
			}
		}catch(HorsIndexException e){
			String message = "Une erreur est apparu lors de la récupération de la colonne " + i + " de la matrice. Voici le message de cette erreur : " + e.getMessage();
			throw new HorsIndexException(message, e);
		}

		return resultat;
	}


	/**
	 * Recupère la line numéroté par l'index de ligne j donné
	 * @param j l'index de la ligne à récupérer
	 * @return la ligne demandée
	 * @throws HorsIndexException 
	 */
	public List<A> recupereLigne(int j) throws HorsIndexException{
		List<A> resultat = new ArrayList<A>();
		try{

			for(int i=1; i <= this.longueur; i++){
				resultat.add(i, this.recupereValeur(i, j));
			}
		}catch(HorsIndexException e){
			String message = "Une erreur est apparu lors de la récupération de la colonne " + j + " de la matrice. Voici le message de cette erreur : " + e.getMessage();
			throw new HorsIndexException(message, e);
		}

		return resultat;
	}

	/**
	 * @return the largeur
	 */
	public int getLargeur() {
		return largeur;
	}


	/**
	 * @return the longueur
	 */
	public int getLongueur() {
		return longueur;
	}


	private static final String NULL = "null";
	/**
	 * Calcule la taille maximale d'une case textuelle d'une matrice
	 */
	private int tailleMaxElement(){
		int result = 0;
		try {
			for(int i = 1; i <= this.largeur; i++){
				for(int j = 1; j <= this.longueur; j++){
					int tailleElement = 0;
					A element = this.recupereValeur(i, j);
					if(element == null){
						tailleElement = NULL.toString().length();
					}else{
						tailleElement = element.toString().length();
					}
					result  = Math.max(result, tailleElement);
				}
			}
		} catch (HorsIndexException e) {
			String message = "Une erreur est apparu lors de la récupération de la taille maximale des éléments de la matrice sous format textuel. Voici le message de cette erreur : " + e.getMessage();
			journaliseur.erreur(message);
		}
		return result;
	}

	/**
	 * Formate la cellule à taille convenable
	 * @param contenuCellule
	 * @param tailleMaxElement
	 * @return
	 */
	private String formatCellule(String contenuCellule, int tailleMaxElement){
		StringBuffer stringBuffer = new StringBuffer();

		// On calcule le nombre d'espace à ajouter à gauche et à droite
		int nombreDespaceAAjouter = tailleMaxElement - contenuCellule.length();
		int nombreDespaceAAjouterAGauche = nombreDespaceAAjouter / 2;
		int nombreDespaceAAjouterADroite = nombreDespaceAAjouterAGauche;

		// Si le nombre d'espace à ajouter est impaire alors on ajoute un espace de plus à droite
		if(nombreDespaceAAjouterAGauche * 2  != nombreDespaceAAjouter){
			nombreDespaceAAjouterADroite++;
		}

		// On commence le formatage

		stringBuffer.append("|");
		// Ajout des espaces à gauche
		for(int i=0; i<nombreDespaceAAjouterAGauche; i++ ){
			stringBuffer.append(" ");
		}

		stringBuffer.append(contenuCellule);

		// Ajout des espaces à droite
		for(int i=0; i<nombreDespaceAAjouterADroite; i++ ){
			stringBuffer.append(" ");
		}
		stringBuffer.append("|");

		return stringBuffer.toString();
	}


	/**
	 * Formate le contenu de la matrice pour l'afficher sous la forme de texte
	 * @return La matrice sous forme textuelle
	 */
	private String formatageMatrice(){
		StringBuffer buffer = new StringBuffer();

		int tailleMaxElement = this.tailleMaxElement();
		try {

			for(int j = this.longueur ; j >=1 ; j--){
				buffer.append("\n[ ");
				for(int i = 1; i<= this.largeur; i ++ ){
					buffer.append(" ");
					A element = this.recupereValeur(i, j);
					String textualElement = NULL;
					if(element != null){
						textualElement = this.recupereValeur(i, j).toString();
					}
					buffer.append(this.formatCellule(textualElement, tailleMaxElement));


					buffer.append(" ");

				}
				buffer.append(" ]");
			}
		} catch (HorsIndexException e) {
			String message = "Une erreur est apparu lors de la création du visuel de la matrice. Voici le message de cette erreur : " + e.getMessage();
			journaliseur.erreur(message);
		}

		return buffer.toString();
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.formatageMatrice();
	}

	public static void main(String[] args) {
		int largeur = 4;
		int longueur = 7;
		Matrice<String> matrice = new Matrice<String>(largeur, longueur);
		for(int i = 1; i <= largeur; i++ ){
			for(int j = 1; j <= longueur; j++){
				try {
					matrice.affecteValeur(i, j, "i = " + i + ", j = " + j);
				} catch (HorsIndexException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(matrice);
	}


}
