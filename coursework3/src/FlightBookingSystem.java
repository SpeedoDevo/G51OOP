/*
 * simple flight booking system
 * @author: Barnabas Forgo (bxf03u, 4211949)
 */

public class FlightBookingSystem {
	
	//create fields
	private Flight[] flights;
	private int flightCount;

	public FlightBookingSystem(int i) {
		//fill fields
		flights = new Flight[i];
		flightCount = 0;
	}

	public static void main(String[] args) {
		//Do not modify this method!
		FlightBookingSystem fbs = new FlightBookingSystem(10);
		fbs.runMainMenu();
	}
	
	public void runMainMenu() {
		boolean quit = false;
		//loop menu until quit
		while (!quit) {
			boolean valid = false;
			while (!valid) {
				//print menu
				System.out.println("Flight Booking Menu");
				System.out.println("===================");
				System.out.println("1) Create Flight");
				if (flightCount != 0) {
					System.out.println("2) Make Booking on Flight");
					System.out.println("3) Print A Flight's Manifest");
				}
				System.out.println("Q) Quit");
				//prompt user
				System.out.print("\nEnter your choice: ");
				char selection = G51OOPInput.getChar();
				//check if selection is valid
				if (selection == '1') {
					//go to flights creator if we still have place for a new one
					if (flightCount < flights.length) {
						runFlightCreator();
					} else {
						System.out.println("\nTotal Flights available exceeded\n");
					}
					valid = true;
				} else if (selection == '2' && flightCount != 0) {
					//encapsulated a helper method promptForFlight that returns a Flight
					bookSeat(promptForFlight());
					valid = true;
				} else if (selection == '3' && flightCount != 0) {
					promptForFlight().printManifest();
					valid = true;
				} else if (selection == 'q' || selection == 'Q') {
					quit = true;
					valid = true;
				} else {
					System.out.println("ERROR: Invalid menu choice!\n");
				}
			}
		}
		System.out.println("\nQuitting...");
		System.exit(0);
	}

	//method that prompts for flight data, creates a Flight and adds it to the array
	private void runFlightCreator() {
		//print menu
		System.out.println("\nFlight Creator");
		System.out.println("==============");
		//prompt the user
		System.out.print("Enter destination: ");
		String destination = G51OOPInput.readString();
		System.out.print("Enter departure time: ");
		String departure = G51OOPInput.readString();		
		System.out.println("\nAeroplane Details");
		System.out.println("=================");
		System.out.print("Enter number of rows in Club Class: ");
		int clubRows = G51OOPInput.readInt();
		System.out.print("Enter number of rows in Economy Class: ");
		int economyRows = G51OOPInput.readInt();
		//data validity check, assumed that Integer.MAX_VALUE is a mistyped input
		if (clubRows < 0 || economyRows < 0 || clubRows == Integer.MAX_VALUE || economyRows == Integer.MAX_VALUE) {
			System.out.println("ERROR: Invalid Row Count\n");
		} else {
			//add Flight to array while incrementing flightCounter
			flights[flightCount++] = new Flight(clubRows, economyRows, destination, departure);
			System.out.println();
		}
	}
	
	//helper method that lists available flights, to keep things nice and tidy
	private void getAvailableFlights() {
		System.out.println("\nAvailable flights:");
		for(int i = 0; i < flightCount; i++) {
			System.out.println("Flight number: " + i
					+ ", Destination: " + flights[i].getDestination()
					+ ", Departure time: " + flights[i].getTime());
		}
		System.out.println();
	}
	
	/*helper method that uses getAvailableFlights to list flights,
	 then prompts the user to choose one and returns the chosen Flight*/
	private Flight promptForFlight() {
		boolean valid = false;
		int flightNum = 0;
		while(!valid) {
			getAvailableFlights();
			System.out.print("Enter flight number: ");
			flightNum = G51OOPInput.readInt();
			if (0 <= flightNum && flightNum < flightCount) {
				valid = true;
			} else {
				System.out.println("\nERROR: Invalid menu choice!");
			}
		}
		return flights[flightNum];
	}
	
	//seat  booking menu
	private void bookSeat(Flight onFlight) {
		boolean isClub;
		boolean clubIsFull = (onFlight.getNextAvailableSeat(true) == null);
		boolean econIsFull = (onFlight.getNextAvailableSeat(false) == null);
		String name;
		String nationality;
		Location loc = null;
		//check if flight is full
		if (clubIsFull && econIsFull) {
			System.out.println("Flight is fully booked, Sorry\n");
			return;
		} else {
			System.out.println("\nSeat Type");
			System.out.println("=========\n");
			//only print seat type if it's not full
			if (!clubIsFull) {
				System.out.println("C) Book Club Class Seat");
			}
			if (!econIsFull) {
				System.out.println("E) Book Economy Class Seat");
			}
		}
		//prompt user
		System.out.print("Pick your seat type: ");
		char seatType = G51OOPInput.getChar();
		//protection so that a full economy class won't be selected
		if (seatType == 'c' || seatType == 'C' || econIsFull) {
			isClub = true;
		} else {
			isClub = false;
		}
		//seat selection menu
		System.out.println("\nSeat Selection");
		System.out.println("==============\n");
		System.out.println("1) Pick Specific Seat");
		System.out.println("2) Any Window Seat");
		System.out.println("3) Any Aisle Seat");
		System.out.println("4) Any Seat");
		//prompt user
		System.out.print("Enter Preference: ");
		int choice = G51OOPInput.readInt();
		//select appropriate seat selection method
		if (choice == 1) {
			loc = selectSeat(onFlight, isClub);
		} else if (choice == 2) {
			loc = onFlight.getNextAvailableWindowSeat(isClub);
			if (loc == null) {
				System.out.println("Sorry, no window seats left");
			}
		} else if (choice == 3) {
			loc = onFlight.getNextAvailableAisleSeat(isClub);
			if (loc == null) {
				System.out.println("Sorry, no aisle seats left");
			}
		} else {
			loc = onFlight.getNextAvailableSeat(isClub);
		}
		//if an empty seat is found prompt for a person then add booking
		if (loc != null) {
			System.out.print("\nEnter person's name: ");
			name = G51OOPInput.readString();
			System.out.print("Enter person's nationality: ");
			nationality = G51OOPInput.readString();
			onFlight.addSeatBooking(loc, new Person(name, nationality));
		}
		System.out.print("\nBook another seat? (Y/N): ");
		char another = G51OOPInput.getChar();
		if (another == 'y' || another == 'Y') {
			//i didn't want to wrap the whole method in a while loop
			bookSeat(onFlight);
		} else {
			System.out.println();
		}
	}
	
	//manual seat selection method
	private Location selectSeat(Flight onFlight, boolean isClub) {
		boolean valid = false;
		Location loc = null;
		//prompt user until valid seat is chosen
		while(!valid) {
			onFlight.printSeatingPlan(isClub);
			System.out.print("Enter Row: ");
			int row = G51OOPInput.readInt();
			if (0 <= row && row < onFlight.numRows(isClub)) {
				System.out.print("Enter Column: ");
				int col = G51OOPInput.readInt();
				if (0 <= col && col < onFlight.numCols(isClub)) {
					loc = new Location(row, col, isClub);
					if (onFlight.isSeatEmpty(loc)) {
						valid = true;
					} else {
						System.out.println("ERROR: Seat already taken");
					}
				} else {
					System.out.println("ERROR: Invalid column number");
				}
			} else {
				System.out.println("ERROR: Invalid row number");
			}
		}
		return loc;
	}

}
