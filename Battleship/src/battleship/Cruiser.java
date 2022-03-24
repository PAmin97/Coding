package battleship;

public class Cruiser extends Ship {
	
	// specific length of ship
	final static int x = 3;
	
	// specific type of ship
	final static String s = "cruiser";
	
	// constructor
	public Cruiser() {
		super(x);
	}
	
	@Override
	public String getShipType() {
		return s;
	}
}
