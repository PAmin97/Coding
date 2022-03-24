package battleship;

public class Submarine extends Ship {
	
	// specific length of ship
	final static int x = 1;
	
	// specific type of ship
	final static String s = "submarine";
	
	// constructor
	public Submarine() {
		super(x);
	}
	
	@Override
	public String getShipType() {
		return s;
	}
}
