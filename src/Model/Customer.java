package Model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	private String cname;
	private long mobileno;
	private boolean isLicenseAvailable;
	private List<Vehicle> Vehicle;
	
	
	
	public Customer(String cname, long mobileno, boolean isLicenseAvailable) {
		super();
		this.cname = cname;
		this.mobileno = mobileno;
		this.isLicenseAvailable = isLicenseAvailable;
		Vehicle = new ArrayList<Vehicle>();
	}

	

	public  void rentvehicle(Vehicle vehicle, int days) {
		
		if(vehicle.rent(days,this.cname)) {
			Vehicle.add(vehicle);
		}
	}

	
	public void returnAllvehicle() {
		for (Vehicle vehicle : Vehicle) {
			vehicle.returnvehicle();
			
		}
		Vehicle.clear();
		
	}
	
	public void viewRentedVehicle() {
		if(Vehicle.isEmpty()) {
			System.out.println("Vehicle Not Found........");
		}
		else {
		System.out.println("   ------ List of Rented Vehicles ------ ");
		Vehicle.forEach(val-> System.out.println("Vehicle Id: 		      	"+val.getVehicleId()+""+"\nRented Days: 			"+val.getRentedDays()));
		}
		
	}



	public String getCname() {
		return cname;
	}



	public void setCname(String cname) {
		this.cname = cname;
	}



	public long getMobileno() {
		return mobileno;
	}



	public void setMobileno(long mobileno) {
		this.mobileno = mobileno;
	}



	public boolean isLicenseAvailable() {
		return isLicenseAvailable;
	}



	public void setLicenseAvailable(boolean isLicenseAvailable) {
		this.isLicenseAvailable = isLicenseAvailable;
	}



	public List<Vehicle> getVehicle() {
		return Vehicle;
	}



	public void setVehicle(List<Vehicle> vehicle) {
		Vehicle = vehicle;
	}


}
	


