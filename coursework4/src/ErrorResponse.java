public class ErrorResponse extends Response {
	private HardwareFailException error;
	public ErrorResponse(Command command, HardwareFailException error){
		//inherit most of the constructor + specific data
		super(0, 0, command);
		//storing the original error
		this.error = error;
	}
	
	public String toString() {
		//toString for printing, printing just the message from original hwfe
		//all logic for handling exception in one class
		return "startTime = " + startTime + "; endTime = " + endTime 
			+ "; command = " + command + "; error is: " + error.getMessage();
	}
}