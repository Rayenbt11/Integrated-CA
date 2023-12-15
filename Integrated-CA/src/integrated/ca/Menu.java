package integrated.ca;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private MovieHandler movieHandler;
    private Movie selectedMovieForRent; // To store the selected movie for rent
    private Rent activeRent; // To track the active rental

    // Constructor initializing MovieHandler with a CSV file path
    public Menu(String csvFilePath) {
        this.movieHandler = new MovieHandler(csvFilePath);
    }

    // Method to start the menu
    public void Menu(Scanner scanner) {
        boolean running = true;

        while (running) {
            displayMenuOptions(); // Display the menu options
int choice;
try {
    choice = scanner.nextInt();
} catch (InputMismatchException e) {
    System.out.println("Please enter a valid integer choice.");
    scanner.nextLine(); // Consume invalid input
    continue; // Restart the loop
}
scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    searchMovie(scanner); // Option to search for a movie
                    break;
                case 2:
                    rentMovie(); // Option to rent a movie
                    break;
                case 3:
                    checkRentalStatus(); // Option to check rental status
                    break;
                case 4:
                    System.out.println("Logging out..."); // Option to logout
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    // Display menu options
    private void displayMenuOptions() {
        System.out.println("=== Main Menu ===");
        System.out.println("1. Search Movie");
        System.out.println("2. Rent Movie");
        System.out.println("3. Check Rental Status");
        System.out.println("4. Logout");
        System.out.print("Enter your choice: ");
    }

    // Search for a movie by title
    private void searchMovie(Scanner scanner) {
        System.out.print("Enter movie title to search: ");
        String title = scanner.nextLine().toLowerCase(); // Get movie title input
        List<Movie> movies = movieHandler.searchMoviesByTitle(title); // Search movies by title

        if (movies.isEmpty()) {
            System.out.println("No movies found with that title.");
        } else {
            // Display found movies
            for (int i = 0; i < movies.size(); i++) {
                System.out.println((i + 1) + ". " + movies.get(i));
            }
        }
    }

    // Rent a movie
    private void rentMovie() {
        if (selectedMovieForRent != null) {
            if (activeRent != null && activeRent.rentActive()) {
                // If an active rental exists
                System.out.println("You already have an active rental for: " + activeRent.getMovie().getTitle());
            } else {
                // Rent the selected movie
                User currentUser = new User();
                activeRent = new Rent(currentUser, selectedMovieForRent);
                System.out.println("Movie rented: " + selectedMovieForRent.getTitle());
            }
        } else {
            // No movie selected for rent
            System.out.println("Please select a movie to rent first.");
        }
    }

    // Check rental status
    private void checkRentalStatus() {
        if (activeRent != null && activeRent.rentActive()) {
            // If there's an active rental, display details
            System.out.println("You have an active rental for: " + activeRent.getMovie().getTitle());
            System.out.println("Rental started at: " + activeRent.getRentalStartTime());
        } else {
            // No active rentals
            System.out.println("You have no active rentals.");
        }
    }
}
