package battleship;

public class Destroyer extends Ship {
	
	// specific length of ship
	final static int x = 2;
	
	// specific type of ship
	final static String s = "destroyer";
	
	// constructor
	public Destroyer() {
		super(x);
	}
	
	@Override
	public String getShipType() {
		return s;
	}
}
