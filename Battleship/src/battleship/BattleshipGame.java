package battleship;

import java.util.Scanner;

public class BattleshipGame {

	public static void main(String[] args) {
		// create the ocean
		Ocean ocean = new Ocean();
		// place all the ships in the ocean
		ocean.placeAllShipsRandomly();
		
		ocean.printWithShips();
		// create the scanner to get user input
		Scanner scan = new Scanner(System.in);
		// print the ocean, so the user can see the rows and column
		ocean.print();
		// till all the ships have been sunk continue playing
		while (ocean.isGameOver() == false) {
			// ask the user to enter the row they would like to shoot at
			System.out.println("Enter the row you want to shoot at:");
			// store the value in a variable called row
			int row = scan.nextInt();
			// ask the user to enter the column they would like to shoot at
			System.out.println("Enter the column you want to shoot at:");
			// store the value in a variable called column
			int column = scan.nextInt();
			// shoot at the location the user entered, and let the user know if they missed or hit a ship
			if (ocean.shootAt(row, column) == true) {
				System.out.println("Hit!");
				
				Ship[][] ships = ocean.getShipArray();
				// if they shot every part of a ship, let them know the ship they sank
				if (ships[row][column].isSunk() == true) {
					System.out.println("You just sank a " + ships[row][column].getShipType() + "!");
				}
				
			} else {
				System.out.println("Miss");
			}
			// after every shot reprint the ocean, so they know where they've shot, missed, and hit
			ocean.print();
		}
		// after all the ships have been sunk, show the user their final score
		System.out.print("Your final score is " + ocean.getShotsFired() + " shots fired!!");
		// close the scanner
		scan.close();
	}
}
