public class PressureResponse extends Response {
	private int pressure;

	public PressureResponse(int startTime, int endTime, Command command, int pressure) {
		//inherit most of the constructor + specific data
		super(startTime, endTime, command);
		this.pressure = pressure;
	}

	public String toString() {
		//toString for printing
		return "startTime = " + startTime + "; endTime = " + endTime 
			+ "; command = " + command + "; pressure is " + pressure;
	}
}
