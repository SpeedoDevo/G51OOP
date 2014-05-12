/*
 * seat class with
 * 	one constructor
 * 	one setter for person
 * 	getters for all fields
 */

public class Seat {
	private boolean isWindow;
	private boolean isAisle;
	private Person person;
	
	public Seat(boolean isWindow, boolean isAisle) {
		this.isWindow = isWindow;
		this.isAisle = isAisle;
	}
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public boolean isWindow() {
		return isWindow;
	}

	public boolean isAisle() {
		return isAisle;
	}

}