package Services;

import java.util.List;
import java.util.Scanner;
import Model.*;

public class MainServices {
	private static Scanner sc = new Scanner(System.in);
	private static VehicleInventory inventory = new VehicleInventory();
	private static Customer customer;
	private static Vehicle v;

	public static void main(String[] args) {
		initializeVehicles();

		boolean flag = true;
		System.out.println("---------------------------VEHICLE RENTAL SYSTEM--------------------------");
		while (flag) {
			showMenu();
			int ch = sc.nextInt();
			switch (ch) {
			case 1 -> handleAdminLogin();
			case 2 -> handleCustomerLogin();
			case 3 -> flag = false;
			default -> System.out.println("Invalid Choice.............");
			}
		}

		System.out.println("Thank you for visiting.....");

	}

	public static void showMenu() {
		System.out.println("\n1. Admin \n2. Customer");
		System.out.println("Enter your choice: ");

	}

	public static void handleAdminLogin() {
		System.out.println("Enter Username: ");
		String user = sc.next();
		System.out.println("Enter Password: ");
		String pass = sc.next();

		if (Admin.authenticate(user, pass)) {
			AdminMenu();
		} else {
			System.out.println("Incorrect username or password.....,Please try again");
		}

	}

	public static void AdminMenu() {
		boolean flag = true;
		while (flag) {
			System.out.println("-------------Option-----------");
			System.out.println("1. Add Vehicle");
			System.out.println("2. Remove Vehicle");
			System.out.println("3. Send Vehicle for maintenance");
			System.out.println("4. Complete vehicle maintenance");
			System.out.println("5. View All Customer Details with Rented vehicle Details");
			System.out.println("6. Show All vehicle info");
			System.out.println("7. Exit");

			System.out.println("Enter your choice: ");
			int choice = sc.nextInt();

			switch (choice) {
			case 1 -> {// Add Vehicle
				System.out.println("----------Add Vehicle-----------");
				System.out.println("1. Bike \n2. Car \n3. Bus");
				System.out.println("Enter your choice: ");
				int ch = sc.nextInt();

				System.out.println("Enter Vehicle Id: ");
				String id = sc.next();
				System.out.println("Enter Rental Rate: ");
				double rate = sc.nextDouble();

				switch (ch) {
				case 1 -> {
					System.out.println("Helmet Available? yes/no");
					String ha = sc.next();
					boolean b = ha.equalsIgnoreCase("yes");
					inventory.addVehicle(new Bike(id, rate, b));
					System.out.println("Vehicle added  Successfully....");
				}

				case 2 -> {
					System.out.println("Enter No of Seats: ");
					int noOfSeats = sc.nextInt();
					inventory.addVehicle(new Car(id, rate, noOfSeats));
					System.out.println("Vehicle added Successfully....");
				}

				case 3 -> {
					System.out.println("Enter No of Seats: ");
					int noOfSeats = sc.nextInt();
					inventory.addVehicle(new Bus(id, rate, noOfSeats));
					System.out.println("Vehicle added Successfully....");
				}

				}
			}

			case 2 -> {// Remove Vehicle

				System.out.println("Remove Vehicle");
				System.out.println("Enter Id of Vehcle to Remove: ");
				String id = sc.next();
				boolean remove = inventory.removeVehicle(id);
				if (remove) {
					System.out.println("Vehicle removed successfully...");
				} else {
					System.out.println("Vehicle not found....");
				}
			}

			case 3 -> {// Send Vehicle for maintenance
				System.out.println("Enter vehicle Id: ");
				String vid = sc.next();
				Vehicle vehicle = getVehicleByID(vid);
				if (vehicle != null) {
					vehicle.sendForMaintenace();
				} else {
					System.out.println("Vehicle is not Available...");
				}
			}
			case 4 -> {// Complete vehicle maintenance
				System.out.println("Enter vehicle Id: ");
				String vid = sc.next();
				Vehicle vehicle = getVehicleByID(vid);
				if (vehicle.isUnderMaintenance()) {
					vehicle.completeMaintenance();
					
				} else {
					System.out.println("Vehicle is not Available...");
				}
			}

			case 5 -> {// view All Customer Details with Rented vehicle Detail
				for (Customer customer : inventory.getAllCustomer()) {
					System.out.println("Name: 				" + customer.getCname());
					System.out.println("Mobile: 		    	" + customer.getMobileno());
					System.out.println("Licensce:			" + (customer.isLicenseAvailable() ? "Yes" : "No"));
					System.out.println("Rented Vehicle Id: 	");
					customer.getVehicle().forEach(val -> System.out.println(" " + val.getVehicleId()));
				}
			}
			case 6 -> {
				System.out.println("----------- All Vehicles -----------");
				System.out.println("Vehicle ID:   	Rate:   	  VehicleAvailable 	Undermaintenace");

				for (Vehicle vehicle : inventory.getallVehicle()) {
//					System.out.println("Vehicle ID: " + vehicle.getVehicleId() + ", Rate: " + vehicle.getRentalRate()
//							+ ", Available: " + (vehicle.isAvailable() ? "Yes" : "No") + ", Maintenance: "
//							+ (vehicle.isUnderMaintenance() ? "Yes" : "No"));
					System.out.println((vehicle.getVehicleId())+"		"+ (vehicle.getRentalRate())+"		" +((vehicle.isAvailable() ? "Yes" : "No"))+"		" +((vehicle.isUnderMaintenance() ? "Yes" : "No")) );
				}
			}

			case 7 -> {
				flag = false;
			}
			}
		}

	}

	public static void initializeVehicles() {
		inventory.addVehicle(new Car("C001", 1200, 6));
		inventory.addVehicle(new Car("C002", 1000, 8));
		inventory.addVehicle(new Bus("BS001", 5000, 16));
		inventory.addVehicle(new Bus("BS002", 10000, 30));
		inventory.addVehicle(new Bike("Bk001", 200, true));
		inventory.addVehicle(new Bike("Bk002", 100, false));
	}

	public static void handleCustomerLogin() {
		System.out.println("-------------Registration Form-----------");
		System.out.println("Enter your Name: ");
		String name = sc.next();
		System.out.println("Enter your Mobile No: ");
		long mobile = sc.nextLong();
		System.out.println("Licence Available ?(yes/no)");
		String available = sc.next();
		boolean isl = available.equalsIgnoreCase("yes");
		customer = new Customer(name, mobile, isl);
		inventory.addCustomers(customer);

		if (customer != null) {
			System.out.println("Registration Successfull.........");
			customerMenu();
		} else {
			System.out.println("Registration Failed........,Please try again");
		}

	}

	public static void customerMenu() {
		boolean flag = true;
		while (flag) {
			System.out.println("-----------Menu----------");
			System.out.println("1. Show Available Bikes");
			System.out.println("2. Show Availble Cars");
			System.out.println("3. Show Available Buses");
			System.out.println("4. Rent vehicle");
			System.out.println("5. Return all Rented Vehicle");
			System.out.println("6. View Rented vehicles");
			System.out.println("7. Exit");
			System.out.println("--------------------");

			int choice = sc.nextInt();

			switch (choice) {
			case 1 -> inventory.showAvailableVehicle(Bike.class);
			case 2 -> inventory.showAvailableVehicle(Car.class);
			case 3 -> inventory.showAvailableVehicle(Bus.class);
			case 4 -> {
				System.out.println("Enter Vehicle Id: ");
				String id = sc.next();
				Vehicle vehicle = getVehicleByID(id);

				System.out.println("Enter no of Days: ");
				int days = sc.nextInt();
				if (vehicle != null) {

					customer.rentvehicle(vehicle, days);
				} else {
					System.out.println("Vehicle not found.....");
				}
			}

			case 5 -> customer.returnAllvehicle();
			case 6 -> customer.viewRentedVehicle();
			case 7 -> flag = false;
			default -> System.out.println("Invalid Choice........");
			}
		}

	}

	private static Vehicle getVehicleByID(String id) {
		List<Vehicle> vehicles = inventory.getallVehicle();
		for (Vehicle vehicle : vehicles) {
			if (vehicle.getVehicleId().equalsIgnoreCase(id)) {
				return vehicle;
			}
		}
		return null;
	}

}
