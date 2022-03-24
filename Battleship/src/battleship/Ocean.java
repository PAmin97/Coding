package battleship;

import java.util.Random;

public class Ocean {
	
	// instance variables
	
	/**
	 * Used to quickly determine which ship is in any given location
	 */
	private Ship[][] ships = new Ship[10][10];
	
	/**
	 * total number of shots fired by the user
	 */
	private int shotsFired;
	
	/**
	 * The number of times a shot hits a ship.
	 */
	private int hitCount;
	
	/**
	 * The number of ships sunk (10 ships in all)
	 */
	private int shipsSunk;
	
	/**
	 * Determining if a certain location has been shot at
	 */
	private boolean[][] shotAt = new boolean[10][10];
	
	// constructor
	
	/**
	 * Creates an ”empty” ocean
	 * Also initializes any game variables, such as how many shots have been fired and how many ships have been hit
	 */
	public Ocean() {
		// set the shotsFired variable to 0
		shotsFired = 0;
		// set the hitCount variable to 0
		hitCount = 0;
		// iterate through every row and column and set each point to an empty sea
		for (int i=0; i < ships.length; i++) {
			for (int j=0; j < ships[i].length; j++) {
				ships[i][j] = new EmptySea();
				ships[i][j].setBowRow(i);
				ships[i][j].setBowColumn(j);
			}
		}
	}
	
	// methods
	
	/**
	 * Place all ten ships randomly on the (initially empty) ocean.
	 */
	void placeAllShipsRandomly() {
		// create a ship array with all 10 ships going from largest ship to smallest ship
		Ship[] Ships = {new Battleship(), new Cruiser(), new Cruiser(), new Destroyer(), 
		          new Destroyer(), new Destroyer(), new Submarine(), new Submarine(), 
		          new Submarine(), new Submarine()};
		// create the random variable
		Random random = new Random();
		// create a for loop to iterate through the ships array above
		for (int i = 0; i < Ships.length; i++) {
		  // assign each ship in the ships array to the variable ship	
		  Ship ship = Ships[i];
		  // create a while loop
		  while (true) {
			  // pick a random number from 0-9 and set it as the row
		      int row = random.nextInt(10);
		      // pick a random number from 0-9 and set it as the column
		      int column = random.nextInt(10);
		      // has a 50/50 chance to place the ship horizontally
		      boolean horizontal = random.nextInt(2) == 0;
		      // based on the row and column, determines if the ship is okay to place, if not selects new row and column values
		      if (ship.okToPlaceShipAt(row, column, horizontal, this)) {
		    	  // if its okay to place the ship, this will place that ship in the ocean
		          ship.placeShipAt(row, column, horizontal, this);
		          // once the ship is placed, break out of the while loop, which will cause the for loop
		          // to iterate to the next ship in the ships array
		          break;
		      	}
		  	}
		}
	}
	
	/**
	 * Returns true if the given location contains a ship, false if it does not
	 * @param row to check
	 * @param column to check
	 */
	boolean isOccupied(int row, int column) {
		// create a boolean variable to return
		boolean occupied;
		
		if (row < 0 || row > 9 || column < 0 || column > 9) {
			occupied = false;
		}
		// check if the given row and column in the 2D ship array is empty
		if (this.getShipArray()[row][column].getShipType().equals("empty")) {
			occupied = false;
		// if the row and column returns anything other than empty, assume it is occupied
		} else {
			occupied = true;
		}
		return occupied;
	}
	
	/**
	 * Returns true if the given location contains a ”real” ship, still afloat, false if it does not. 
	 * In addition, this method updates the number of shots that have been fired, and the number of hits.
	 * @param row to shoot at
	 * @param column to shoot at
	 */
	boolean shootAt(int row, int column) {
		// Once the user takes a shot, increment the shotsFired variable by 1
		shotsFired++;
		// change the value of the shotAt array to true, since that specific point has been shot at
		shotAt[row][column] = true;
		// if the shootAt method in the ship class returns true then the hitCount increases by 1 and this method returns true
		if (ships[row][column].shootAt(row, column) == true && ships[row][column].isSunk() == true) {
			hitCount++;
			shipsSunk++;
			return true;
		} else if (ships[row][column].shootAt(row, column) == true && ships[row][column].isSunk() == false) {
			hitCount++;
			return true;
		} else {
			return false;
		}
		
	}
	
	// getters
	
	/**
	 * Returns the number of shots fired (in the game)
	 */
	int getShotsFired() {
		return this.shotsFired;
	}
	
	/**
	 * Returns the number of hits recorded (in the game). All hits are counted, 
	 * not just the first time a given square is hit.
	 */
	int getHitCount() {
		return this.hitCount;
	}
	
	/**
	 * Returns the number of ships sunk (in the game)
	 */
	int getShipsSunk() {
		return this.shipsSunk;
	}
	
	/**
	 * Returns the 10x10 array of Ships.
	 */
	Ship[][] getShipArray() {
		return this.ships;
	}
	
	/**
	 * Returns true if all ships have been sunk, otherwise false
	 */
	boolean isGameOver() {
		// set the number of ships sunk at the beginning to 0
		/*shipsSunk = 0;
		// iterate through the entire ocean and check if any ships have been sunk
		// if so, for each 's' in the ocean add 1 to shipsSunk
		for (int i=0; i < ships.length; i++) {
			for (int j=0; j < ships[i].length; j++) {
				if (ships[i][j].isSunk() == true) {
					shipsSunk ++;
				}
			}
		}*/
		// if the number of ship's sunk equals 10 the game is over
		if (shipsSunk == 10) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Prints the Ocean. To aid the user, row numbers should be displayed along the left edge of the array, 
	 * and column numbers should be displayed along the top. Numbers should be 0 to 9
	 */
	void print() {
		// creates an empty space between the 0's labeling the rows and columns
		System.out.printf("%-4s", "");
		for (int i = 0; i < 10; i++) {
			// label the columns 0-9
		    System.out.printf("%-4d", i);
		}
		// skip to the next line, to start the ocean
		System.out.println();
		for (int i = 0; i < 10; i++) {
			// label the rows 0-9
		    System.out.printf("%-4d", i);
		    for (int j = 0; j < ships[0].length; j++) {
		    	if (shotAt[i][j] == false) {
		    		// every point in the ocean should be a '.' until its been shot at
		    		System.out.printf("%-4s", ".");
		    	} else {
		    		// once a point has been shot at, depending on if its a hit, miss, or sunk the 
		    		//toString method will return something
		    		System.out.printf("%-4s", ships[i][j].toString());
		    	}
		    }
		    // for each iteration go to the next line
		    System.out.println();
		}
	}
	
	/**
	 * Used for debugging
	 * Print the Ocean with the ships visible
	 */
	void printWithShips() {
		// create an empty space between the 0's labeling the rows and columns
		System.out.printf("%-4s", "");
		for (int i = 0; i < 10; i++) {
			// label the column 0-9
		    System.out.printf("%-4d", i);
		}
		// begin the ocean on the next line (below the column numbers)
		System.out.println();
		for (int i = 0; i < 10; i++) {
			// label the rows
		    System.out.printf("%-4d", i);
		    for (int j = 0; j < ships[0].length; j++) {
		    	// iterate through each point in the ocean and depending on its ship type return a certain letter or nothing
		    	if (ships[i][j].getShipType().equals("battleship")) {
		    		System.out.printf("%-4s", "b");
		    	} else if (ships[i][j].getShipType().equals("cruiser")) {
		    		System.out.printf("%-4s", "c");
		    	} else if (ships[i][j].getShipType().equals("destroyer")) {
		    		System.out.printf("%-4s", "d");
		    	} else if (ships[i][j].getShipType().equals("submarine")) {
		    		System.out.printf("%-4s", "s");
		    	} else {
		    		System.out.printf("%-4s", " ");
		    	}
		    }
		    // for each iteration go to the next line
		    System.out.println();
		}
	}
}
