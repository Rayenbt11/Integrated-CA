

package com.mycompany.user;

/**
 *
 * @author gabri
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class User {
    private static final Map<String, String> userDatabase = new HashMap<>(); 

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean running = true;
            
            while (running) {
                System.out.println("1. Create Account");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); 
                
                switch (choice) {
                    case 1:
                        createAccount(scanner);
                        break;
                    case 2:
                        login(scanner);
                        break;
                    case 3:
                        running = false;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a valid option.");
                }
            }
        }
    }

private static void createAccount(Scanner scanner) {
    System.out.print("Enter email: ");
    String email = scanner.nextLine();
    System.out.print("Enter password: ");
    String password = scanner.nextLine();

    if (!email.contains("@")) {
        System.out.println("Invalid email format. Please enter a valid email address.");
    } else if (userDatabase.containsKey(email)) {
        System.out.println("Email already exists. Please choose another email.");
    } else {
        userDatabase.put(email, password);
        System.out.println("Account created successfully!");
    }
}

    private static void login(Scanner scanner) {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (userDatabase.containsKey(email) && userDatabase.get(email).equals(password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid email or password. Please try again.");
        }
    }
}