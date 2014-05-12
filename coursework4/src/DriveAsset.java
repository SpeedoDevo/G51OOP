public class DriveAsset extends Asset {

	public DriveAsset(String name) {
		super(name);
	}

	public Response execute(Command command) {
		try {
			//generate response
			int startTime = DeviceDriver.getMissionTime();
			int distance = DeviceDriver.makeMove(((MakeMoveCommand)command).distance);
			int endTime = DeviceDriver.getMissionTime();
			return new DriveResponse(startTime,endTime,command,distance);
		} catch (HardwareFailException e) {
			//catch hwfe
			return error(command, e);
		}
	}
}