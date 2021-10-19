/**
 * 
 */
package P4;

/**
 * Ce composant représente une abstraction d'une couleur. Il peut exister autant de couleur que nécessaire.
 * @author Ronan Quintin
 *
 */
public abstract class Couleur {
	
		public Couleur(String codeCouleur) {
			this.codeCouleur = codeCouleur;
		}
	
		private String codeCouleur;

		/**
		 * @return the codeCouleur
		 */
		public String getCodeCouleur() {
			return codeCouleur;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((codeCouleur == null) ? 0 : codeCouleur.hashCode());
			return result;
		}


		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Couleur other = (Couleur) obj;
			if (codeCouleur == null) {
				if (other.codeCouleur != null)
					return false;
			} else if (!codeCouleur.equals(other.codeCouleur))
				return false;
			return true;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return this.getCodeCouleur();
		}
		

		
}
