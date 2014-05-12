public class GPSAsset extends Asset {

	public GPSAsset(String name) {
		super(name);
	}

	public Response execute(Command command) {
		try {
			//generate response
			int startTime = DeviceDriver.getMissionTime();
			Location location = DeviceDriver.getLocation();
			int endTime = DeviceDriver.getMissionTime();
			return new LocationResponse(startTime,endTime,command,location);
		} catch (HardwareFailException e) {
			//catch hwfe
			return error(command, e);
		}
	}
}