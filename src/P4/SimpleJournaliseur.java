/**
 * 
 */
package P4;

import java.util.Date;

/**
 * Ce journaliseur utilise les entrées sorties classiques de la JVM pour journaliser les messages
 * @author Ronan Quintin
 *
 */
public class SimpleJournaliseur implements Journaliseur {
	
	
	private static final String PREFIXE_ERREUR = "[ERREUR]";
	
	private static final String PREFIXE_INFO ="[INFO]";
	

	/* (non-Javadoc)
	 * @see org.ronan.puissance4.transverse.Journaliseur#erreur(java.lang.String)
	 */
	@Override
	public void erreur(String message) {
		System.err.println(this.formatageMessage(PREFIXE_ERREUR, message));
	}

	/* (non-Javadoc)
	 * @see org.ronan.puissance4.transverse.Journaliseur#info(java.lang.String)
	 */
	@Override
	public void info(String message) {
		System.out.println(this.formatageMessage(PREFIXE_INFO, message));
	}

	/**
	 * Formate le message sous la forme de DATE  - PREFIXE MESSAGE
	 * @param prefixe Le prefixe du message (erreur ou info)
	 * @param message Le message à intégrer
	 * @return Le message formaté et prêt à être afficher
	 */
	private String formatageMessage(String prefixe, String message){
		
		// Création du buffer de message
		StringBuffer bufferMessage =  new StringBuffer();
		
		// Récupération de la date
		Date date = new Date(System.currentTimeMillis());
		
		bufferMessage.append(date);
		bufferMessage.append(" - ");
		bufferMessage.append(prefixe);
		bufferMessage.append(" ");
		bufferMessage.append(message);
		
		return bufferMessage.toString();
	}
	
	
}
