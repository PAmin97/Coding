package battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ShipTest {

	Ocean ocean;
	Ship ship;
	
	@BeforeEach
	void setUp() throws Exception {
		ocean = new Ocean();
	}

	@Test
	void testGetLength() {
		ship = new Battleship();
		assertEquals(4, ship.getLength());
		
		// get the correct length of a cruiser
		ship = new Cruiser();
		assertEquals(3, ship.getLength());
		
		// get the correct length of a destroyer
		ship = new Destroyer();
		assertEquals(2, ship.getLength());
		
		// get the correct length of a submarine
		ship = new Submarine();
		assertEquals(1, ship.getLength());	
	}

	@Test
	void testGetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		
		//More tests
		// get the correct bow row of a ship
		Ship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		battleship2.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship2.getBowRow());
		
		// get the correct bow row of a ship
		Ship cruiser = new Cruiser();
		row = 6;
		column = 4;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruiser.getBowRow());
		
		// get the correct bow row of a ship
		Ship destroyer = new Destroyer();
		row = 9;
		column = 4;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, destroyer.getBowRow());
	}

	@Test
	void testGetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, battleship.getBowColumn());	

		// get the correct bow column of the ship
		Ship cruiser = new Cruiser();
		row = 4;
		column = 6;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, cruiser.getBowColumn());
		
		// get the correct bow column of the ship
		Ship destroyer = new Destroyer();
		row = 2;
		column = 7;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, destroyer.getBowColumn());
		
		// get the correct bow column of the ship
		Ship submarine = new Submarine();
		row = 1;
		column = 1;
		horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		assertEquals(column, submarine.getBowColumn());
	}

	@Test
	void testGetHit() {
		ship = new Battleship();
		boolean[] hits = new boolean[4];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		
		// return the hit array of a ship
		ship = new Cruiser();
		hits = new boolean[3];
		assertArrayEquals(hits, ship.getHit());
		assertFalse(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		assertFalse(ship.getHit()[2]);
		
		// return the hit array of a ship thats been shot
		ship = new Destroyer();
		hits = new boolean[2];
		assertArrayEquals(hits, ship.getHit());
		ship.placeShipAt(5, 5, false, ocean);
		ship.shootAt(5, 5);
		assertTrue(ship.getHit()[0]);
		assertFalse(ship.getHit()[1]);
		
		// get return the hit array of a ship that has been sunk
		ship = new Submarine();
		hits = new boolean[1];
		assertArrayEquals(hits, ship.getHit());
		ship.placeShipAt(9, 9, false, ocean);
		ship.shootAt(9, 9);
		assertTrue(ship.getHit()[0]);
	}
	@Test
	void testGetShipType() {
		ship = new Battleship();
		assertEquals("battleship", ship.getShipType());
		
		// get the correct ship type
		ship = new Cruiser();
		assertEquals("cruiser", ship.getShipType());
		
		// get the correct ship type
		ship = new Destroyer();
		assertEquals("destroyer", ship.getShipType());
		
		// get the correct ship type
		ship = new Submarine();
		assertEquals("submarine", ship.getShipType());
		
	}
	
	@Test
	void testIsHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertTrue(battleship.isHorizontal());
		
		// test if the cruiser was not placed horizontally
		Ship cruiser = new Cruiser();
		row = 5;
		column = 6;
		horizontal = false;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertFalse(cruiser.isHorizontal());
		// test if the destroyer was placed horizontally
		Ship destroyer = new Destroyer();
		row = 0;
		column = 3;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		assertTrue(destroyer.isHorizontal());
	}
	
	@Test
	void testSetBowRow() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowRow(row);
		assertEquals(row, battleship.getBowRow());
		
		// set the correct bow row for the ship
		Ship cruiser = new Cruiser();
		row = 8;
		column = 5;
		horizontal = false;
		cruiser.setBowRow(row);
		assertEquals(row, cruiser.getBowRow());
		// set the correct bow row for the ship
		Ship sub = new Submarine();
		row = 3;
		column = 4;
		horizontal = false;
		sub.setBowRow(row);
		assertEquals(row, sub.getBowRow());
	}

	@Test
	void testSetBowColumn() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setBowColumn(column);
		assertEquals(column, battleship.getBowColumn());
		
		// set the correct bow column for the ship
		Ship destroyer = new Destroyer();
		row = 6;
		column = 6;
		horizontal = false;
		destroyer.setBowColumn(column);
		assertEquals(column, destroyer.getBowColumn());
		// set the correct bow column for the ship
		Ship cruiser = new Cruiser();
		row = 0;
		column = 4;
		horizontal = true;
		cruiser.setBowColumn(column);
		assertEquals(column, cruiser.getBowColumn());
	}

	@Test
	void testSetHorizontal() {
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.setHorizontal(horizontal);
		assertTrue(battleship.isHorizontal());
		
		// return the correct orientation of the ship once its set
		Ship cruiser = new Cruiser();
		row = 4;
		column = 7;
		horizontal = false;
		cruiser.setHorizontal(horizontal);
		assertEquals(horizontal, cruiser.isHorizontal());
		// return the correct orientation of the ship once its set
		Ship sub = new Submarine();
		row = 6;
		column = 4;
		horizontal = true;
		sub.setHorizontal(horizontal);
		assertTrue(sub.isHorizontal());
	}

	@Test
	void testOkToPlaceShipAt() {
		
		//test when other ships are not in the ocean
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok = battleship.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok, "OK to place ship here.");
		
		// test if it's okay to place a ship directly below the battleship
		Ship cruiser = new Cruiser();
		row = 1;
		column = 5;
		horizontal = false;
		boolean ok2 = cruiser.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2);
		// test if it is okay to place a ship out of bounds
		Ship destroyer = new Destroyer();
		row = 0;
		column = 0;
		horizontal = false;
		boolean ok3 = destroyer.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok3);
	}
	
	@Test
	void testOkToPlaceShipAtAgainstOtherShipsOneBattleship() {
		
		//test when other ships are in the ocean
		
		//place first ship
		Battleship battleship1 = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		boolean ok1 = battleship1.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok1, "OK to place ship here.");
		battleship1.placeShipAt(row, column, horizontal, ocean);

		//test second ship
		Battleship battleship2 = new Battleship();
		row = 1;
		column = 4;
		horizontal = true;
		boolean ok2 = battleship2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok2, "Not OK to place ship vertically adjacent below.");
		
		// test if this method will fail, if we place a bigger ship on top of a smaller ship
		//place first ship
		Cruiser cruiser = new Cruiser();
		row = 5;
		column = 8;
		horizontal = true;
		boolean ok3 = cruiser.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok3, "OK to place ship here.");
		cruiser.placeShipAt(row, column, horizontal, ocean);

		//test second ship
		Battleship battleship3 = new Battleship();
		row = 5;
		column = 8;
		horizontal = false;
		boolean ok4 = battleship3.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok4);
		
		// test if our placement works
		// first ship
		Destroyer destroyer = new Destroyer();
		row = 7;
		column = 5;
		horizontal = true;
		boolean ok5 = destroyer.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok5);
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		//test second ship
		Submarine sub = new Submarine();
		row = 7;
		column = 6;
		horizontal = false;
		boolean ok6 = sub.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok6);
		
		// first ship
		Destroyer destroyer2 = new Destroyer();
		row = 6;
		column = 0;
		horizontal = false;
		boolean ok7 = destroyer2.okToPlaceShipAt(row, column, horizontal, ocean);
		assertTrue(ok7);
		destroyer2.placeShipAt(row, column, horizontal, ocean);
		
		//test second ship
		Destroyer destroyer3 = new Destroyer();
		row = 6;
		column = 2;
		horizontal = true;
		boolean ok8 = destroyer3.okToPlaceShipAt(row, column, horizontal, ocean);
		assertFalse(ok8);
	}

	@Test
	void testPlaceShipAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 4;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, battleship.getBowRow());
		assertEquals(column, battleship.getBowColumn());
		assertTrue(battleship.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[0][0].getShipType());
		assertEquals(battleship, ocean.getShipArray()[0][1]);

		// place another ship 2 rows below the battleship above
		Ship cruiser = new Cruiser();
		row = 2;
		column = 4;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruiser.getBowRow());
		assertEquals(column, cruiser.getBowColumn());
		assertTrue(cruiser.isHorizontal());
		
		assertEquals("empty", ocean.getShipArray()[2][1].getShipType());
		assertEquals(cruiser, ocean.getShipArray()[2][2]);
		// place a ship out of bounds
		Ship cruiser2 = new Cruiser();
		row = 0;
		column = 8;
		horizontal = false;
		cruiser2.placeShipAt(row, column, horizontal, ocean);
		assertEquals(row, cruiser2.getBowRow());
		assertEquals(column, cruiser2.getBowColumn());
		assertFalse(cruiser2.isHorizontal());
		// row 0 column 8 should be empty since the ship can not be placed there vertically
		assertEquals("empty", ocean.getShipArray()[0][8].getShipType());
	}

	@Test
	void testShootAt() {
		
		Ship battleship = new Battleship();
		int row = 0;
		int column = 9;
		boolean horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(battleship.shootAt(1, 9));
		boolean[] hitArray0 = {false, false, false, false};
		assertArrayEquals(hitArray0, battleship.getHit());

		// test if shootAt returns true and if the hit array gets updated at the right index
		Ship cruiser = new Cruiser();
		row = 3;
		column = 9;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(cruiser.shootAt(3, 8));
		boolean[] hitArray1 = {false, true, false};
		assertArrayEquals(hitArray1, cruiser.getHit());
		// test if the entire array will return true if all its points are shot at
		Ship destroyer = new Destroyer();
		row = 0;
		column = 1;
		horizontal = true;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(destroyer.shootAt(0, 0));
		assertTrue(destroyer.shootAt(0, 1));
		boolean[] hitArray2 = {true, true};
		assertArrayEquals(hitArray2, destroyer.getHit());
	}
	
	@Test
	void testIsSunk() {
		
		Ship submarine = new Submarine();
		int row = 3;
		int column = 3;
		boolean horizontal = true;
		submarine.placeShipAt(row, column, horizontal, ocean);
		
		assertFalse(submarine.isSunk());
		assertFalse(submarine.shootAt(5, 2));
		assertFalse(submarine.isSunk());

		// test if a destroyer ship has been sunk after 3 shots
		Ship destroyer = new Destroyer();
		row = 3;
		column = 3;
		horizontal = false;
		destroyer.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(destroyer.shootAt(2, 3));
		assertFalse(destroyer.isSunk());
		assertFalse(destroyer.shootAt(5, 2));
		assertFalse(destroyer.isSunk());
		assertTrue(destroyer.shootAt(3, 3));
		assertTrue(destroyer.isSunk());
		// test if a battleship is sunk after 4 direct hits
		Ship battleship = new Battleship();
		row = 9;
		column = 8;
		horizontal = true;
		battleship.placeShipAt(row, column, horizontal, ocean);
		
		assertTrue(battleship.shootAt(9, 8));
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(9, 7));
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(9, 6));
		assertFalse(battleship.isSunk());
		assertTrue(battleship.shootAt(9, 5));
		assertTrue(battleship.isSunk());
	}

	@Test
	void testToString() {
		
		Ship battleship = new Battleship();
		assertEquals("x", battleship.toString());
		
		int row = 9;
		int column = 1;
		boolean horizontal = false;
		battleship.placeShipAt(row, column, horizontal, ocean);
		battleship.shootAt(9, 1);
		assertEquals("x", battleship.toString());

		// test the toString method of a cruiser
		Ship cruiser = new Cruiser();
		assertEquals("x", cruiser.toString());
		
		row = 4;
		column = 6;
		horizontal = true;
		cruiser.placeShipAt(row, column, horizontal, ocean);
		cruiser.shootAt(row, column);
		assertEquals("x", cruiser.toString());
		// test the toString method of a sunk submarine
		Ship sub = new Submarine();
		assertEquals("x", sub.toString());
		
		row = 0;
		column = 0;
		horizontal = true;
		sub.placeShipAt(row, column, horizontal, ocean);
		sub.shootAt(row, column);
		assertEquals("s", sub.toString());
	}

}
