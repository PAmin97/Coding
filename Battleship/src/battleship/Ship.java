package battleship;

public abstract class Ship {
	
	// instance variables
	
	/**
	 * The row that contains the bow
	 */
	private int bowRow;
	
	/**
	 * The column that contains the bow
	 */
	private int bowColumn;
	
	/**
	 * Length of ship
	 */
	private int length;
	
	/**
	 * A boolean that represents whether ship is horizontal or not
	 */
	private boolean horizontal;
	
	/**
	 * An array of booleans that indicate whether that part of the ship has been hit or not
	 */
	private boolean[] hit;
	
	/**
	 * Determine if surrounding block in the ocean are occupied or not
	 */
	private static int[][] directions = new int[][] {{-1,-1}, {-1,0}, {-1,1}, {0,1}, {1,1}, {1,0}, {1,-1}, {0,-1}};
	
	
	// constructor
	
	/**
	 * Create a ship with a given length
	 * @param length of the ship
	 */
	public Ship(int length) {
		// sets the length of the created ship to the input length
		this.length = length;
		// initializes the hit array based on the length given
		this.hit = new boolean[length];
	}
	
	// getters/setters
	
	/**
	 * return the ship length
	 */
	public int getLength() {
		return length;
		
	}
	
	/**
	 * return the row corresponding to the position of the bow
	 */
	public int getBowRow() {
		return bowRow;
	}
	
	/**
	 * return the bow column location
	 */
	public int getBowColumn() {
		return bowColumn;
	}
	
	/**
	 * return the hit array
	 */
	public boolean[] getHit() {
		return hit;
	}
	
	/**
	 * return whether the ship is horizontal or not
	 */
	public boolean isHorizontal() {
		return horizontal;
	}
	
	
	/**
	 * sets the value of bowRow
	 * @param row to set
	 */
	public void setBowRow(int row) {
		this.bowRow = row;
	}
	
	/**
	 * set the value of bowColumn
	 * @param column to set
	 */
	public void setBowColumn(int column) {
		this.bowColumn = column;
	}
	
	/**
	 * sets the value of the instance variable horizontal
	 * @param horizontal to set
	 */
	public void setHorizontal(boolean horizontal) {
		this.horizontal = horizontal;
	}
	
	// abstract methods
	
	/**
	 * return the type of ship as a String
	 */
	public abstract String getShipType();
	
	// other methods
	
	/**
	 * Based on the given row, column, and orientation, returns true if it is okay
	 * to put a ship of its length with its bow in this location; false otherwise
	 * @param row of the ocean
	 * @param column of the ocean
	 * @param horizontal is true if the boat is going to be placed horizontally
	 * @param ocean contains the 2D array to place the ships
	 * @return boolean if it's safe to place the ship
	 */
	boolean okToPlaceShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		// create a boolean variable to return
		boolean placeShip = false;
		
		// enter this statement if horizontal is true
		if (horizontal == true) {
			// checks if the ship can be placed within the bounds of the ocean
			if (column > (this.length-2) && column < 10) {
				// iterate through the helper array defined above
				for (int i = column; i > column - this.length; i--) {
					for (int[] direction : directions) {
						// add the first element in the array to the given row
						int x = row + direction[0];
						// add the second element in the array to the given column
						int y = i + direction[1];
						// as long as each column is within the bounds of the ocean we can place the ship
						if (y >= 0 && y < ocean.getShipArray().length) {
							// as long as each row is within the bounds of the ocean we can place the ship
							if (x >= 0 && x < ocean.getShipArray()[y].length) {
								// last check if every point through the iteration is unoccupied
								if (ocean.isOccupied(x, y) == false && ocean.isOccupied(row, column) == false) {
									// if so place the ship
									placeShip = true;
								} else {
									placeShip = false;
									break;
								}
							}
						}
					}
					// once placeShip is false stop checking the remaining pieces of the ship
					if (placeShip == false) {
						break;
					}
				}
			}
		  // enter this statement if horizontal is false
		} else if (horizontal == false) {
			// while the given row is greater than the ships length - 2 and less than 10 enter this statement
			if (row > (this.length-2) && column < 10) {
				// iterate through the helper variable defined above
				for (int i = row; i > row - this.length; i--) {
					for (int[] direction : directions) {
						// add the first element in the array to the given row
						int x = i + direction[0];
						// add the second element in the array to the given column
						int y = column + direction[1];
						// as long as each column is within the bounds of the ocean we can place the ship
						if (y >= 0 && y < ocean.getShipArray().length) {
							// as long as each row is within the bounds of the ocean we can place the ship
							if (x >= 0 && x < ocean.getShipArray()[y].length) {
								// last check if every point through the iteration is unoccupied
								if (ocean.isOccupied(x, y) == false && ocean.isOccupied(row, column) == false) {
									// if so place the ship
									placeShip = true;
								} else {
									placeShip = false;
									break;
								}
							}
						}
					}
					// once placeShip is false stop checking the remaining pieces of the ship
					if (placeShip == false) {
						break;
					}
				}
			}
		}
		return placeShip;
	}
	
	/**
	 * "Puts" the ship in the ocean
	 * @param row to place the ship
	 * @param column to place the ship
	 * @param horizontal, boolean to decide if the ship is going to be vertical or horizontal
	 * @param ocean provides the 2D array to help place the ship in the ocean
	 */
	void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
		// set the bow column, row, and if the ship is horizontal or not
		this.setBowColumn(column);
		this.setBowRow(row);
		this.setHorizontal(horizontal);
		// check to make sure there is no ship already occupying the ocean
		// based on the ships orientation parameter, place the ship horizontally or vertically
		if (this.okToPlaceShipAt(row, column, horizontal, ocean) == true && horizontal == true) {
			for (int i=0; i < this.getLength(); i++) {
				ocean.getShipArray()[row][column-i] = this;
			}
		} else if (this.okToPlaceShipAt(row, column, horizontal, ocean) == true && horizontal == false) {
			for (int i=0; i < this.getLength(); i++) {
				ocean.getShipArray()[row-i][column] = this;
			}
		}
	}
	
	/**
	 * If a part of the ship occupies the given row and column, and the ship hasn’t been sunk, 
	 * mark that part of the ship as “hit” (in the hit array, index 0 indicates the bow) and return true; 
	 * otherwise return false.
	 * @param row to shoot at
	 * @param column to shoot at
	 * @return true if a ship was hit otherwise, false
	 */
	boolean shootAt(int row, int column) {
		// create a boolean to return
		boolean hit = false;
		// enter this code block if the ship is horizontal
		if (this.isHorizontal() == true) {
			// iterate through the length of the ship
			for (int i=0; i < this.getLength(); i++) {
				// get each column the ship is in and the only row it is in
				if (this.getBowColumn()-i == column && this.getBowRow() == row) {
					// if the ship was hit, change the boolean in the hit array to true at that location
					this.hit[i] = true;
					// return true, since you hit a ship
					hit = true;
				}
			}
		  // enter this code block if the ship is vertical
		} else if (this.isHorizontal() == false) {
			// iterate through the length of the ship
			for (int i=0; i < this.getLength(); i++) {
				// get each row the ship is in and the only column
				if (this.getBowRow()-i == row && this.getBowColumn() == column) {
					// if the ship was hit, change that boolean in the hit array to true at that location
					this.hit[i] = true;
					// return true, since you hit a ship
					hit = true;
				}
			}
		  // if nothing was hit return false
		} else {
			hit = false;
		}
		return hit;
	}
	
	/**
	 * Return true if every part of the ship has been hit, false otherwise
	 */
	boolean isSunk() {
		// create a boolean to return
		boolean sunk = true;
		// iterate through the hit array of the given ship
		for (boolean h : this.getHit()) {
			// as long as just one of the elements in the array returns false, the ship hasn't been sunk
			if (h == false) {
				sunk = false;
				break;
			  // if they all return true, then the ship has been sunk
			} else {
				sunk = true;
			}
		}
		return sunk;
	}
	
	/**
	 * Returns a single-character String to use in the Ocean’s print method. 
	 * This method should return ”s” if the ship has been sunk and ”x” if it has not been sunk.
	 */
	@Override
	public String toString() {
		// create the variable to return
		String s = null;
		// if the ship is sunk, return an s, letting the user know they've sunk the ship
		if (this.isSunk() == true) {
			s = "s";
		  // otherwise, let them know they hit the ship
		} else {
			s = "x";
		}
		return s;
	}
}
