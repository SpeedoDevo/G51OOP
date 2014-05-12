public class RotationResponse extends Response {
	private Direction direction;
	
	public RotationResponse(int startTime, int endTime, Command command, Direction direction) {
		//inherit most of the constructor + specific data
		super(startTime, endTime, command);
		this.direction = direction;
	}
	
	public String toString() {
		//toString for printing
		return "startTime = " + startTime + "; endTime = " + endTime 
			+ "; command = " + command + "; direction is " + direction;
	}
}