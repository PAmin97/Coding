package battleship;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OceanTest {

	Ocean ocean;
	
	static int NUM_BATTLESHIPS = 1;
	static int NUM_CRUISERS = 2;
	static int NUM_DESTROYERS = 3;
	static int NUM_SUBMARINES = 4;
	static int OCEAN_SIZE = 10;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}
	
	@Test
	void testEmptyOcean() {
		
		//tests that all locations in the ocean are "empty"
		
		Ship[][] ships = ocean.getShipArray();
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				
				assertEquals("empty", ship.getShipType());
			}
		}
		
		assertEquals(0, ships[0][0].getBowRow());
		assertEquals(0, ships[0][0].getBowColumn());
		
		assertEquals(5, ships[5][5].getBowRow());
		assertEquals(5, ships[5][5].getBowColumn());
		
		assertEquals(9, ships[9][0].getBowRow());
		assertEquals(0, ships[9][0].getBowColumn());
	}
	
	@Test
	void testPlaceAllShipsRandomly() {
		
		//tests that the correct number of each ship type is placed in the ocean
		
		ocean.placeAllShipsRandomly();

		Ship[][] ships = ocean.getShipArray();
		ArrayList<Ship> shipsFound = new ArrayList<Ship>();
		
		int numBattlehips = 0;
		int numCruisers = 0;
		int numDestroyers = 0;
		int numSubmarines = 0;
		int numEmptySeas = 0;
		
		for (int i = 0; i < ships.length; i++) {
			for (int j = 0; j < ships[i].length; j++) {
				Ship ship = ships[i][j];
				if (!shipsFound.contains(ship)) {
					shipsFound.add(ship);
				}
			}
		}
		
		for (Ship ship : shipsFound) {
			if ("battleship".equals(ship.getShipType())) {		
				numBattlehips++;
			} else if ("cruiser".equals(ship.getShipType())) {
				numCruisers++;
			} else if ("destroyer".equals(ship.getShipType())) {
				numDestroyers++;
			} else if ("submarine".equals(ship.getShipType())) {
				numSubmarines++;
			} else if ("empty".equals(ship.getShipType())) {
				numEmptySeas++;
			}
		}
		
		assertEquals(NUM_BATTLESHIPS, numBattlehips);
		assertEquals(NUM_CRUISERS, numCruisers);
		assertEquals(NUM_DESTROYERS, numDestroyers);
		assertEquals(NUM_SUBMARINES, numSubmarines);
		
		//calculate total number of available spaces and occupied spaces
		int totalSpaces = OCEAN_SIZE * OCEAN_SIZE; 
		int occupiedSpaces = (NUM_BATTLESHIPS * 4)
				+ (NUM_CRUISERS * 3)
				+ (NUM_DESTROYERS * 2)
				+ (NUM_SUBMARINES * 1);
		
		//test number of empty seas, each with length of 1
		assertEquals(totalSpaces - occupiedSpaces, numEmptySeas);
	}

	@Test
	void testIsOccupied() {

		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.isOccupied(1, 5));
		assertTrue(ocean.isOccupied(0, 0));
		assertFalse(ocean.isOccupied(3, 4));
		assertTrue(ocean.isOccupied(0, 5));

		// show that a cruiser can not be placed in the ocean since a battleship is occupying one of its positions
		Ship battleship = new Battleship();
		row = 6;
		column = 8;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		Ship cruiser = new Cruiser();
		row = 8;
		column = 7;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(cruiser.okToPlaceShipAt(row, column, horizontal, ocean));
		assertTrue(ocean.isOccupied(6, 7));
		
		// show the first spot in the ocean is occupied by a submarine
		Ship sub = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		sub.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.isOccupied(0, 0));
	}

	@Test
	void testShootAt() {
	
		assertFalse(ocean.shootAt(0, 1));
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());

		// shoot at an empty spot in the ocean (test 2)
		assertFalse(ocean.shootAt(0, 0));
		// shoot and sink a submarine (test 3)
		Ship sub = new Submarine();
		row = 9;
		column = 9;
		horizontal = true;
		sub.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(9, 9));
		assertTrue(sub.isSunk());
	}

	@Test
	void testGetShotsFired() {
		
		//should be all false - no ships added yet
		assertFalse(ocean.shootAt(0, 1));
		assertFalse(ocean.shootAt(1, 0));
		assertFalse(ocean.shootAt(3, 3));
		assertFalse(ocean.shootAt(9, 9));
		assertEquals(4, ocean.getShotsFired());
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		Ship submarine = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(6, ocean.getShotsFired());
		
		// shoot and sink the submarine and check if shots fired increased by 1
		assertTrue(ocean.shootAt(0, 0));
		assertTrue(submarine.isSunk());
		assertEquals(7, ocean.getShotsFired());
		// sink a battleship and see if all the shots were added to shotsFired
		Ship battleship = new Battleship();
		row = 9;
		column = 9;
		horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(9, 9));
		assertTrue(ocean.shootAt(8, 9));
		assertTrue(ocean.shootAt(7, 9));
		assertTrue(ocean.shootAt(6, 9));
		assertTrue(battleship.isSunk());
		assertEquals(11, ocean.getShotsFired());
	}

	@Test
	void testGetHitCount() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());

		// shoot the destroyer again and see if the hit count increases
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(2, ocean.getHitCount());
		// add a cruiser and miss one shot but land 2 and see if hit count increased by 2 and not 3
		Ship cruiser = new Cruiser();
		row = 8;
		column = 6;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(8, 6));
		assertFalse(ocean.shootAt(7, 7));
		assertTrue(ocean.shootAt(8, 5));
		assertFalse(cruiser.isSunk());
		assertEquals(4, ocean.getHitCount());
	}
	
	@Test
	void testGetShipsSunk() {
		
		Destroyer destroyer = new Destroyer();
		int row = 1;
		int column = 5;
		boolean horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(1, 5));
		assertFalse(destroyer.isSunk());
		assertEquals(1, ocean.getHitCount());
		assertEquals(0, ocean.getShipsSunk());
		
		// shoot the destroyer above and see if shipsSunk increases by 1
		assertTrue(ocean.shootAt(0, 5));
		assertTrue(destroyer.isSunk());
		assertEquals(2, ocean.getHitCount());
		assertEquals(1, ocean.getShipsSunk());
		
		// sink 2 more subs and see if they add to the total number of sunken ships
		Ship sub1 = new Submarine();
		row = 9;
		column = 9;
		horizontal = false;
		sub1.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(9, 9));
		assertEquals(2, ocean.getShipsSunk());
		
		Ship sub2 = new Submarine();
		row = 0;
		column = 0;
		horizontal = false;
		sub2.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(ocean.shootAt(0, 0));
		assertEquals(3, ocean.getShipsSunk());
	}

	@Test
	void testGetShipArray() {
		
		Ship[][] shipArray = ocean.getShipArray();
		assertEquals(OCEAN_SIZE, shipArray.length);
		assertEquals(OCEAN_SIZE, shipArray[0].length);
		
		assertEquals("empty", shipArray[0][0].getShipType());
		
		// place a battleship in the ocean and make sure the ship type matches with the ocean
		Ship battleship = new Battleship();
		int row = 5;
		int column = 0;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertEquals("battleship", shipArray[2][0].getShipType());
		// place a submarine in the ocean and get the ship type right above it
		Ship sub = new Submarine();
		row = 9;
		column = 9;
		horizontal = true;
		sub.placeShipAt(row, column, horizontal, ocean);
		
		assertEquals("empty", shipArray[8][9].getShipType());
	}

}
