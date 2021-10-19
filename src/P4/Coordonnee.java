/**
 * 
 */
package P4;

/**
 * Represente les coordonnée d'une cellule d'une matrice
 * @author Ronan Quintin
 *
 */
public class Coordonnee{
	
	private int i;
	
	private int j;
	
	

	/**
	 * @param i
	 * @param j
	 */
	public Coordonnee(int i, int j) {
		this.i = i;
		this.j = j;
	}

	/**
	 * @return the i
	 */
	public int getI() {
		return i;
	}

	/**
	 * @param i the i to set
	 */
	public void setI(int i) {
		this.i = i;
	}

	/**
	 * @return the j
	 */
	public int getJ() {
		return j;
	}

	/**
	 * @param j the j to set
	 */
	public void setJ(int j) {
		this.j = j;
	}
	
	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.i + "," + this.j;
	}

	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + i;
		result = prime * result + j;
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
		Coordonnee other = (Coordonnee) obj;
		if (i != other.i)
			return false;
		if (j != other.j)
			return false;
		return true;
	}

	
	
	
}
