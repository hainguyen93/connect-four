package uk.ac.sheffield.aca13pn;

@SuppressWarnings("serial")
public class IllegalColumnException extends RuntimeException {

	/**
	 * Constructs a new IllegalColumnException
	 * @param col the illegal column number that was supplied
	 */
	public IllegalColumnException(int col) {
		super("Column "+col+" is an illegal column number");		
	}	
}
