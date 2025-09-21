package Model;

public class Bike extends Vehicle {
	private boolean helmetAvailable;

	public Bike(String vehicleId, double rentalRate,  boolean helmetAvailable) {
		super(vehicleId, rentalRate);
		this.helmetAvailable = helmetAvailable;
	}
	
	public void displayInfo() {
		
	}

}
