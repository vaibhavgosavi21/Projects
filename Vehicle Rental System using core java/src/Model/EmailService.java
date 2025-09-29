package Model;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {
	//sender mail
	private final static String senderMail="hackedsoul25@gmail.com";
	private final static String senderpass="igjz uzby wrcc rrgv";
	
	public static void sendBookingConfirmation(String name, int days, double rentalRate, double bill, String VehicleId ,String email ) {
		
		String subject="Vehicle Rental Confirmation";


		 String body="Hello "+name+", your booked vehicle succesfully. with vehicleId:"+VehicleId+"\n Rental Rate:"+


		 rentalRate+"\n No of Days:"+days+"\nTotal Bill:"+bill;


		


		 //smtp -s


		 Properties properties=new Properties();

		 properties.put("mail.smtp.auth", "true");

		 properties.put("mail.smtp.host", "smtp.gmail.com");

		 properties.put("mail.smtp.port", "587");

		 properties.put("mail.smtp.starttls.enable", "true");



		 Session session=Session.getInstance(properties,new Authenticator() {

		 @Override

		 protected PasswordAuthentication getPasswordAuthentication() {

		 return new PasswordAuthentication(senderMail, senderpass);
		 }

		 });
		 
		 //create new mail message
		 System.out.println("1 ");


		 Message message =new MimeMessage(session);
		 try {

		 message.setFrom(new InternetAddress(senderMail));
		 
		 message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));

		 message.setSubject(subject);

		 message.setText(body);
		 
		 Transport.send(message);
		 System.out.println("mail sent to "+email+" .......");
		 } catch (MessagingException e) {

		 System.out.println("Some issues occured");

		 e.printStackTrace();


		 }
	}

}
