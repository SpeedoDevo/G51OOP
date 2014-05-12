public class MakeMoveCommand extends Command {
	public int distance;

	public MakeMoveCommand(int distance) {
		this.distance = distance;
	}

	public String toString() {
		//toString for printing
		return super.toString() + " " + distance;
	}
}
