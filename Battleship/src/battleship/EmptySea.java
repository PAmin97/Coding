package battleship;

public class EmptySea extends Ship {
	
	// constructor
	
	public EmptySea() {
		super(1);
	}
	
	// methods
	
	/**
	 * This method overrides shootAt(int row, int column) that is inherited
	 * from Ship, and always returns false to indicate that nothing was hit
	 */
	@Override
	boolean shootAt(int row, int column) {
		return false;
	}
	
	/**
	 * This method overrides isSunk() that is inherited from Ship, and always
	 * returns false to indicate that you didn’t sink anything
	 */
	@Override
	boolean isSunk() {
		return false;
	}
	
	/**
	 * Returns the single-character “-“ String to use in the Ocean’s print method.
	 */
	@Override
	public String toString() {
		return "-";
	}
	
	/**
	 * This method just returns the string “empty”
	 */
	@Override
	public String getShipType() {
		return "empty";
	}
}
