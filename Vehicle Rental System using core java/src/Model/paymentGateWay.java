package Model;

import java.util.Scanner;

public class paymentGateWay {
	static Scanner sc=new Scanner(System.in);
	
	public static boolean processPayment(double bill) {
		System.out.println("Enter Amount To Pay: ");
		int amount=sc.nextInt();
		System.out.println("Processing Payment........");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Payment Done :"+amount);
		if(bill<=amount) {
			if(bill==amount)
				System.out.println("Process Done");
			if(bill<amount)
				System.out.println("Paid Extra: "+(amount-bill));
			System.out.println("Vehicle Rented Successfully");
			return true;
		}
		else if(bill>amount) {
			System.out.println("Pending Amount: "+(bill-amount));
			System.out.println("Vehicle Not allocated");
			return false;
		}
		return false;
	}
}
