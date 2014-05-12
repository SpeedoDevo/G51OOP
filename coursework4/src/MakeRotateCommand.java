public class MakeRotateCommand extends Command {
	Rotation rotation;
	
	public MakeRotateCommand(Rotation rotation) {
		this.rotation = rotation;
	}

	public String toString() {
		//toString for printing
		return super.toString() + " " + rotation;
	}
}