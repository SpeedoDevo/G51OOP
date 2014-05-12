public class Location {	
	private int locationX;
	private int locationY;
	private Direction direction;

	public Location(int locationX, int locationY, Direction direction) {
		this.locationX = locationX;
		this.locationY = locationY;
		this.direction = direction;
	}

	public String toString() {
		//toString for printing
		return "[" + locationX + ", " + locationY + "] " + direction;
	}

}
