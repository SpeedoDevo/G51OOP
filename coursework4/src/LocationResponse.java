public class LocationResponse extends Response {
	private Location location;

	public LocationResponse(int startTime, int endTime, Command command, Location location) {
		//inherit most of the constructor + specific data
		super(startTime, endTime, command);
		this.location = location;
	}

	public String toString() {
		//toString for printing
		return "startTime = " + startTime + "; endTime = " + endTime
			+ "; command = " + command + "; location is " + location;
	}
}