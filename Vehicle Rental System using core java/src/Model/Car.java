package Model;

public class Car extends Vehicle {
	private int noOfSeats;

	//constructor
	public Car(String vehicleId, double rentalRate, int noOfSeats) {
		super(vehicleId, rentalRate);
		this.noOfSeats = noOfSeats;
	}

	//method
	public void displayInfo() {

	}

}
