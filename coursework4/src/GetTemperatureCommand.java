public class GetTemperatureCommand extends Command {
	public boolean scale; // true = Celcius, false = Fahrenheit

	public GetTemperatureCommand(boolean scale) {
		this.scale = scale;
	}

	public String toString() {
		//toString for printing
		return super.toString() + (scale ? " C" : " F");
	}
}