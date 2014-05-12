public abstract class Asset {
	protected String name;

	public Asset(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public abstract Response execute(Command command);
	
	//inheritable error response generator
	public Response error(Command command, HardwareFailException error) {
		return new ErrorResponse(command, error);
	}
}