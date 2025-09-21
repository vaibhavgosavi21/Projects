package Model;

public class receiptGenerator {
	public static void generateReceipt(String name,Vehicle vehicle, double bill) {
		System.out.println("		-----------Bill Receipt-----------");
		System.out.println("Customer Name: 				"+name);
		System.out.println("Vehilce Id: 				"+vehicle.getVehicleId());
		System.out.println("Rental Rate: 				"+vehicle.getRentalRate());
		System.out.println("Rented Days: 				"+vehicle.getRentedDays());
		System.out.println("Total Bill: 				"+bill+" Rs");
		System.out.println("------------------------------------------");
	}

}
