/**
 * 
 */
package P4;

/**
 * Cette exception est soulevée lorsque l'on tente d'insérer un jeton dans une colonne pleine
 * @author Ronan Quintin
 *
 */
public class ColonnePleineException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ColonnePleineException() {
	}

	/**
	 * @param message
	 */
	public ColonnePleineException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ColonnePleineException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ColonnePleineException(String message, Throwable cause) {
		super(message, cause);
	}

}
