package uk.ac.sheffield.aca13pn;

@SuppressWarnings("serial")
public class IllegalRowException extends RuntimeException {

	/**
	 * Constructs a new IllegalRowException
	 * @param col the illegal row number that was supplied
	 */
	public IllegalRowException(int row) {
		super("Row "+row+" is an illegal row number");		
	}	

}
