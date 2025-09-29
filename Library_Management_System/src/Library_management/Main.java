package Library_management;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LibrarySystem system = new LibrarySystem();

        System.out.println("Welcome to Library Management System!");
        int loginAttempts = 0;
        boolean loggedIn = false;
        while (loginAttempts < 3 && !loggedIn) {
            loggedIn = system.authenticate();
            if (!loggedIn) {
                loginAttempts++;
                if (loginAttempts < 3) {
                    System.out.println("Try again. Attempts left: " + (3 - loginAttempts));
                } else {
                    System.out.println("Max login attempts exceeded. Exiting.");
                    return;
                }
            }
        }

        if (loggedIn) {
            system.showMenu();
        }

        scanner.close();
    }
}
