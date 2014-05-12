public class BarometerAsset extends Asset {

	public BarometerAsset(String name) {
		super(name);
	}

	public Response execute(Command command) {
		try {
			//generate response
			int startTime = DeviceDriver.getMissionTime();
			int pressure = DeviceDriver.getPressure();
			int endTime = DeviceDriver.getMissionTime();
			return new PressureResponse(startTime,endTime,command,pressure);
		} catch (HardwareFailException e) {
			//catch hwfe
			return error(command, e);
		}
	}
}