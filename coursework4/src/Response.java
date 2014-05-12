public abstract class Response {
	int startTime;
	int endTime;
	Command command;
	
	public Response (int startTime, int endTime, Command command) {
		//inherited constructor
		this.startTime = startTime;
		this.endTime = endTime;
		this.command = command;
	}

	public abstract String toString();
}
