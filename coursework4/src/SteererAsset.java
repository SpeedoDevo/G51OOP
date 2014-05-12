public class SteererAsset extends Asset {
	public SteererAsset(String name) {
		super(name);
	}

	public Response execute(Command command) {
		try {
			//generate response
			int startTime = DeviceDriver.getMissionTime();
			Direction direction = DeviceDriver.makeTurn(((MakeRotateCommand)command).rotation);
			int endTime = DeviceDriver.getMissionTime();
			return new RotationResponse(startTime,endTime,command,direction);
		} catch (HardwareFailException e) {
			//catch hwfe
			return error(command, e);
		}
	}
}