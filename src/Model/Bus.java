package Model;

public class Bus  extends Vehicle{
	private int noOfSeats;

	public Bus(String vehicleId, double rentalRate, int noOfSeats) {
		super(vehicleId, rentalRate);
		this.noOfSeats = noOfSeats;
	}
	
	public void displayInfo() {
		
	}

}
