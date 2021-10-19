/**
 * 
 */
package P4;

/**
 * Cette interface définise les opérations de base que doivent fournir les journaliseur
 *  @author Ronan Quintin
 *
 */
public interface Journaliseur {

	
	
	/**
	 * Journalise le message d'erreur donné
	 */
	public void erreur(String message);
	
	/**
	 * Journalise le message d'information donné
	 * @param message
	 */
	public void info(String message);
}
