/**
 * 
 */
package P4;

/**
 * Fabrique un journaliseur selon une configuration prédéfinie
 * @author Ronan Quintin
 *
 */
public class UsineJournaliseur {
	
	public static Journaliseur CreerJournaliseur(){
	
		Journaliseur journaliseur = new SimpleJournaliseur();
		
		return journaliseur;
	}

}
