public class ThermometerAsset extends Asset {
	public ThermometerAsset(String name) {
		super(name);
	}

	public Response execute(Command command) {
		try {
			//generate response
			int startTime = DeviceDriver.getMissionTime();
			double temp = DeviceDriver.getTemperature(((GetTemperatureCommand)command).scale);
			int endTime = DeviceDriver.getMissionTime();
			return new TemperatureResponse(startTime,endTime,command,temp);
		} catch (HardwareFailException e) {
			//catch hwfe
			return error(command, e);
		}
	}
}