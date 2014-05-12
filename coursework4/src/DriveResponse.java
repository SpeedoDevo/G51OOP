public class DriveResponse extends Response {
	private int distance;

	public DriveResponse(int startTime, int endTime, Command command, int distance) {
		//inherit most of the constructor + specific data
		super(startTime, endTime, command);
		this.distance = distance;
	}

	public String toString() {
		//toString for printing
		return "startTime = " + startTime + "; endTime = " + endTime 
			+ "; command = " + command + "; distance is " + distance;
	}
}