package Services;
import java.util.ArrayList;
import java.util.List;
import Model.Bike;
import Model.Customer;
import Model.Vehicle;

public class VehicleInventory {
	private List <Vehicle>Vehicles;
	private List <Customer>customers;


	public VehicleInventory() {
		Vehicles = new ArrayList();
		customers= new ArrayList<Customer>();
	}

	public void addVehicle(Vehicle vehicle) {
		Vehicles.add(vehicle);

	}

	public boolean removeVehicle(String id) {
		for (Vehicle vehicle2 : Vehicles) {
			if(vehicle2.getVehicleId().equalsIgnoreCase(id)){
			Vehicles.remove(vehicle2);
			return true;
			}
		}
		return false;
	}

	public List<Vehicle> getallVehicle() {
		return Vehicles;

	}

	public void showAvailableVehicle(Class<?> type) {
		System.out.println("Available "+type.getSimpleName()+"'S: ");
		
		for(Vehicle vehicle: Vehicles) {
			if(type.isInstance(vehicle)&& vehicle.isAvailable()) {
				System.out.println(type.getSimpleName()+" Id: "+ vehicle.getVehicleId());
				System.out.println("Rental Rate: "+vehicle.getRentalRate());
				System.out.println("-----------------------");
			}
		}
		

	}

	public void addCustomers(Customer customer) {
		customers.add(customer);
		
		
		
	}

	public List<Customer> getAllCustomer() {
		return this.customers;
		
	}

}
