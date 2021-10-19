/**
 * 
 */
package P4;

/**
 * Cette exception peut être soulevée si jamais une opération dois se faire en dehors des bornes définies
 * @author Ronan Quintin
 *
 */
public class HorsIndexException extends Exception {

	
	/**
	 * Généré automatiquement par eclipse
	 */
	private static final long serialVersionUID = -4905533898090698708L;

	public HorsIndexException() {
	}

	/**
	 * @param message
	 */
	public HorsIndexException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public HorsIndexException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public HorsIndexException(String message, Throwable cause) {
		super(message, cause);
	}

}
