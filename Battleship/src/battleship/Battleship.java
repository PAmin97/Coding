package battleship;

public class Battleship extends Ship {
	
	// specific length of ship
	final static int x = 4;
	
	// specific type of ship
	final static String s = "battleship";
	
	// constructor
	public Battleship() {
		super(x);
	}
	
	@Override
	public String getShipType() {
		return s;
	}
}