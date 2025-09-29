package Model;

import java.util.Scanner;

public class Vehicle {
	private String vehicleId;
	private double rentalRate;
	private boolean isAvailable;
	private int rentedDays;
	private boolean underMaintenance;

	//constructor
	public Vehicle(String vehicleId, double rentalRate) {
		super();
		this.isAvailable = true;
		this.underMaintenance = false;
		this.vehicleId = vehicleId;
		this.rentalRate = rentalRate;
		this.rentedDays =0;
	}

	// Methods
	public boolean rent(int days, String name) {
		if(this.isAvailable) {
		double bill=this.rentalRate*days;
		System.out.println("Total Bill Amount : "+bill);
		
		boolean payment=paymentGateWay.processPayment(bill);
		if(payment) {
		isAvailable=false;
		rentedDays=days;
		receiptGenerator.generateReceipt(name, this, bill);
		System.out.println("Sending Bill to your Email..........");
		System.out.println("Enter your email id: ");
		Scanner sc=new Scanner(System.in);
		String email=sc.next();
				
		//Email
		EmailService.sendBookingConfirmation(name, days, rentalRate, bill, vehicleId,email) ;
		return true;
		
		}
		return false;
	}
		System.out.println("Vahicle not available....");
		return false;
}
	

	public void returnvehicle() {
		this.isAvailable=true;
		this.rentedDays=0;
		System.out.println(this.vehicleId+" Returned Succesfull....");
		
	}
	
	

	public void sendForMaintenace() {
		this.isAvailable=false;
		this.underMaintenance=true;
		System.out.println(this.vehicleId+"Send for maintenance...");

	}

	public void completeMaintenance() {
		this.isAvailable=true;
		this.underMaintenance=false;
		System.out.println(this.vehicleId+"Maintenance Completed...");

	}

	
	// Getters and setters
	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public int getRentedDays() {
		return rentedDays;
	}

	public void setRentedDays(int rentedDays) {
		this.rentedDays = rentedDays;
	}

	public boolean isUnderMaintenance() {
		return underMaintenance;
	}

	public void setUnderMaintenance(boolean underMaintenance) {
		this.underMaintenance = underMaintenance;
	}

	public void returSpecificvehicle(String id) {
		this.isAvailable=true;
		this.rentedDays=0;
		System.out.println(this.vehicleId+" Returned Succesfull....");		
	}

	

	

}
