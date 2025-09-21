package Model;

public class Admin {
	private final static String username = "vaibhav";
	private final static String password = "1111";

	public Admin(String username, String password) {
		super();
		username = username;
		password = password;
	}

	public static boolean authenticate(String user, String pass) {
		if ((user.equals(username)) && (pass.equals(password))) {
			System.out.println("Login successful.....");
			return true;
		} else {
			
			return false;
		}
		

	}

}
