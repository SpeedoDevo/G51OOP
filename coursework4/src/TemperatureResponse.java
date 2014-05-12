public class TemperatureResponse extends Response {
	private double temp;
	public TemperatureResponse(int startTime, int endTime, Command command, double temp){
		//inherit most of the constructor + specific data
		super(startTime, endTime, command);
		this.temp = temp;
	}
	
	public String toString() {
		//toString for printing
		return "startTime = " + startTime + "; endTime = " + endTime 
			+ "; command = " + command + "; temperature is " + temp;
	}
}