public class Flight implements IFlight {
	
	private final int CLUB_COLS = 4;
	private final int ECON_COLS = 6;
	
	private int clubRows;
	private int econRows;
	private String destination;
	private String departure;
	private Seat[][] clubSeats;
	private Seat[][] econSeats;
	
	public Flight(int clubRows, int economyRows, String destination, String departure) {
		this.clubRows = clubRows;
		this.econRows = economyRows;
		this.destination = destination;
		this.departure = departure;
		//create 2 2D arrays for storing seat bookings
		clubSeats = new Seat[clubRows][numCols(true)];
		econSeats = new Seat[economyRows][numCols(false)];
		//initialize them with proper isAisle and isWindow booleans
		initializeSeats(clubSeats, econSeats);
	}
	
	
	
	//==================================================================GETTERS
	//destination getter
	public String getDestination() {
		return destination;
	}
	//departure time getter
	public String getTime() {
		return departure;
	}
	public int numRows(boolean rowClass) {
		if (rowClass) {
			return clubRows;
		} else {
			return econRows;
		}
	}
	//getter for number of rows in specific class
	public int numCols(boolean rowClass) {
		if (rowClass) {
			return CLUB_COLS;
		} else {
			return ECON_COLS;
		}
	}
	
	
	
	//=============================================================BOOKING STUFF
	//method for adding a booking
	public void addSeatBooking(Location loc, Person person) {
		if (loc.isClub()) {
			clubSeats[loc.getRow()][loc.getCol()].setPerson(person);
		} else {
			econSeats[loc.getRow()][loc.getCol()].setPerson(person);
		}
	}

	//method that checks if a seat is empty
	public boolean isSeatEmpty(Location loc) {
		if (loc.isClub()) {
			return clubSeats[loc.getRow()][loc.getCol()].getPerson() == null;
		} else {
			return econSeats[loc.getRow()][loc.getCol()].getPerson() == null;
		}
	}

	/*
	 * methods for getting next available seats
	 * used a helper method because these three are virtually the same
	 * keeping the code DRY
	 */
	public Location getNextAvailableAisleSeat(boolean rowClass) {
		return getNextAvailableCondition(rowClass, "aisle");
	}
	public Location getNextAvailableWindowSeat(boolean rowClass) {
		return getNextAvailableCondition(rowClass, "window");
	}
	public Location getNextAvailableSeat(boolean rowClass) {
		return getNextAvailableCondition(rowClass, "none");
	}
	// DRY helper method, takes a String from the methods above to decide which condition to use
	private Location getNextAvailableCondition(boolean rowClass, String condition) {
		Location loc = null;
		Seat[][] seats;
		//selecting the appropriate seat class, again this is for DRY reasons
		if (rowClass) {
			seats = clubSeats;
		} else {
			seats = econSeats;
		}
		//check if there are rows at all
		if (numRows(rowClass) == 0) {
			return null;
		}
		//iterate through the rows and cols of seats
		for(int row = 0; row < numRows(rowClass); row++) {
			for(int col = 0; col < numCols(rowClass); col++) {
				if (condition == "none" && seats[row][col].getPerson() == null) {
					//condition for getNextAvailableSeat():
					//just get the next seat without a person
					loc = new Location(row, col, rowClass);
					return loc;
				} else if (condition == "aisle" && seats[row][col].getPerson() == null && seats[row][col].isAisle()) {
					//condition for getNextAvailableAisleSeat():
					//get the next aisle seat without a person
					loc = new Location(row, col, rowClass);
					return loc;
				} else if (condition == "window" && seats[row][col].getPerson() == null && seats[row][col].isWindow()) {
					//condition for getNextAvailableWindowSeat():
					//get the next window seat without a person
					loc = new Location(row, col, rowClass);
					return loc;
				}
			}
		}
		return loc;
	}
	
	//seating plan printer method
	public void printSeatingPlan(boolean rowClass) {
		//I could have done it with a loop that would be independent of the number of cols,
		//but for this exercise I guess this is sufficient enough
		if (rowClass) {
			System.out.println("  0 1 2 3");
		} else {
			System.out.println("  0 1 2 3 4 5");
		}
		for(int row = 0; row < numRows(rowClass); row++) {
			System.out.print(row + ":");
			for(int col = 0; col < numCols(rowClass); col++) {
				//print X if taken O otherwise
				if (isSeatEmpty(new Location(row, col, rowClass))) {
					System.out.print("O ");
				} else {
					System.out.print("X ");
				}
			}
			System.out.println();
		}
	}

	
	//======================================================PRINT MANIFEST STUFF
	//print the flights manifest using the seat class independent classPassengerList
	public void printManifest() {
		System.out.println("Flight Manifest");
		System.out.println("Destination: " + destination);
		System.out.println("Departure time: " + departure);
		System.out.println("Club Class passenger list:");
		classPassengerList(true);
		System.out.println("Economy Class passenger list:");
		classPassengerList(false);
		System.out.println();
	}
	
	//helper method for printing passengers details in specific seat class
	private void classPassengerList(boolean rowClass) {
		Seat[][] seats;
		//boolean is set to false when a passenger is found
		boolean isEmpty = true;
		//selecting the appropriate seat class, DRY
		if (rowClass) {
			seats = clubSeats;
		} else {
			seats = econSeats;
		}
		//don't iterate if there are no rows
		if (numRows(rowClass) != 0) {
			//iterate through rows and cols
			for(int row = 0; row < numRows(rowClass); row++) {
				for(int col = 0; col < numCols(rowClass); col++) {
					//if a person is found print it's data
					if (seats[row][col].getPerson() != null) {
						isEmpty = false;
						System.out.println("Row: " + row + ", Column: " + col 
								+ ", Name: " + seats[row][col].getPerson().getName()
								+ ", Nationality: " + seats[row][col].getPerson().getNationality());
					}
				}
			}
		}
		//if class is empty print appropriate message
		if (isEmpty) {
			if (rowClass) {
				System.out.println("No passengers in Club Class");
			} else {
				System.out.println("No passengers in Economy Class");
			}
		}
	}
	
	
	
	//======================================================================INIT
	//helper method to initialize Seats to have proper boolean values
	private void initializeSeats(Seat[][] clubSeats, Seat[][] econSeats) {
		for(int row = 0; row < numRows(true); row++) {
			for(int col = 0; col < numCols(true); col++) {
				if (col == 0 || col == 3) {
					clubSeats[row][col] = new Seat(true,true);
				} else {
					clubSeats[row][col] = new Seat(false,true);
				}
			}
		}
		for(int row = 0; row < numRows(false); row++) {
			for(int col = 0; col < numCols(false); col++) {
				if(col == 0 || col == 5) {
					econSeats[row][col] = new Seat(true,false);
				} else if (col == 1 || col == 4) {
					econSeats[row][col] = new Seat(false,false);
				} else {
					econSeats[row][col] = new Seat(false,true);
				}
			}
		}
	}
}
