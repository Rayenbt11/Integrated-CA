package integrated.ca;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
// Gabrielius 2021350
public class User {
    // Database to store user credentials
    private static final Map<String, String> userDatabase = new HashMap<>();

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean loggedIn = false;
            Menu menu = new Menu("Menu.java"); // Instantiating the Menu class

            // Continuously prompt user until logged in
            while (!loggedIn) {
                displayOptions(); // Display menu options
                int choice = scanner.nextInt(); // Get user's choice
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        createAccount(scanner); // Call function to create a new user account
                        break;
                    case 2:
                        loggedIn = login(scanner); // Call function to log in a user
                        if (loggedIn) {
                            menu.Menu(scanner); // If login successful, start the Menu
                        }
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        }
    }

    // Display menu options
    private static void displayOptions() {
        System.out.println("1. Create Account");
        System.out.println("2. Login");
        System.out.print("Enter your choice: ");
    }

    // Function to create a new user account
    private static void createAccount(Scanner scanner) {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Validate and add the new user account to the database
        if (!email.contains("@")) {
            System.out.println("Invalid email format. Please enter a valid email address.");
        } else if (userDatabase.containsKey(email)) {
            System.out.println("Email already exists. Please choose another email.");
        } else {
            userDatabase.put(email, password);
            System.out.println("Account created successfully!");
        }
    }

    // Function to log in a user
    private static boolean login(Scanner scanner) {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // Check if the provided credentials match those in the database
        if (userDatabase.containsKey(email) && userDatabase.get(email).equals(password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid email or password. Please try again.");
            return false;
        }
    }
}
