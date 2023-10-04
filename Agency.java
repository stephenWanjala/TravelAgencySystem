import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Agency {
    private static Administrators administrators;
    private static String loggedInUser;
    private static List<Destination> destinations = new ArrayList<>();
    private static List<Flight> flights = new ArrayList<>();
    private static List<Destination> tripDestinations = new ArrayList<>();

    public Agency() {
        administrators = new Administrators(); // Initialize the Administrators object
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            if (isValidCredentials(username, password)) {
                loggedInUser = getUsernameFromEmail(username);
                System.out.println("Welcome to the Prog2 Travel Agency " + loggedInUser + ", Please make a selection from the menu:");
                showMainMenu(scanner);
                break;
            } else {
                System.out.println("Invalid Credentials! Try Again.");
            }
        }

        scanner.close();
    }

    private static boolean isValidCredentials(String username, String password) {
        // Use the Administrators class to validate credentials
        return administrators.isValidCredentials(username, password);
    }

    private static String getUsernameFromEmail(String email) {
        int atIndex = email.indexOf('@');
        if (atIndex != -1) {
            return email.substring(0, atIndex);
        }
        return email;
    }

    private static void showMainMenu(Scanner scanner) {
        while (true) {
            System.out.println("1. Explore Flights");
            System.out.println("2. Explore Destinations");
            System.out.println("3. Book a Trip");
            System.out.println("X. Exit the System");
            System.out.print("Please enter an option: ");
            String choice = scanner.nextLine();

            switch (choice.toUpperCase()) {
                case "1":
                    exploreFlights(scanner);
                    break;
                case "2":
                    exploreDestinations(scanner);
                    break;
                case "3":
                    bookATrip(scanner);
                    break;
                case "X":
                    System.out.println("Thanks for using the Prog2 Travel Agency.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Please enter a valid choice, or press X to exit.");
            }
        }
    }

    private static void exploreFlights(Scanner scanner) {
        System.out.println("Welcome to the Flights section " + loggedInUser + ", Please make a selection from the menu:");
        while (true) {
            System.out.println("1. View All Flights");
            System.out.println("2. View Flights by Country");
            System.out.println("3. Add a Flight");
            System.out.println("4. Remove a Flight");
            System.out.println("X. Return to Main Menu");
            System.out.print("Please enter an option: ");
            String choice = scanner.nextLine();

            switch (choice.toUpperCase()) {
                case "1":
                    viewAllFlights();
                    break;
                case "2":
                    viewFlightsByCountry(scanner);
                    break;
                case "3":
                    addFlight(scanner);
                    break;
                case "4":
                    removeFlight(scanner);
                    break;
                case "X":
                    showMainMenu(scanner);
                    return;
                default:
                    System.out.println("Please enter a valid choice, or press X to exit.");
            }
        }
    }

    private static void viewAllFlights() {
        if (flights.isEmpty()) {
            System.out.println("No flights available.");
        } else {
            System.out.println("+----------------------+----------------+--------------+-------+-------+");
            System.out.println("| Airline              | Flight Number  | Takeoff      | Landing | Cost  |");
            System.out.println("+----------------------+----------------+--------------+-------+-------+");
            for (Flight flight : flights) {
                System.out.printf("| %-20s | %-14d | %-12s | %-7s | $%-5.2f |\n", flight.getAirline(), flight.getFlightNumber(), flight.getTakeoff(), flight.getLanding(), flight.getCost());
            }
            System.out.println("+----------------------+----------------+--------------+-------+-------+");
        }
    }

    private static void viewFlightsByCountry(Scanner scanner) {
        System.out.print("Enter the country to view flights for: ");
        String country = scanner.nextLine();
        boolean found = false;

        for (Flight flight : flights) {
            if (flight.getLanding().equalsIgnoreCase(country)) {
                found = true;
                System.out.println(flight);
            }
        }

        if (!found) {
            System.out.println("No flights available to " + country + ".");
        }
    }

    private static void addFlight(Scanner scanner) {
        System.out.print("Enter airline name: ");
        String airline = scanner.nextLine();
        System.out.print("Enter flight number: ");
        int flightNumber = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter takeoff location: ");
        String takeoff = scanner.nextLine();
        System.out.print("Enter landing location: ");
        String landing = scanner.nextLine();
        System.out.print("Enter cost: ");
        double cost = Double.parseDouble(scanner.nextLine());

        Flight newFlight = new Flight(airline, flightNumber, takeoff, landing, cost);
        flights.add(newFlight);
        System.out.println("Flight added successfully.");
    }

    private static void removeFlight(Scanner scanner) {
        System.out.print("Enter the flight number to remove: ");
        int flightNumber = Integer.parseInt(scanner.nextLine());
        boolean removed = false;

        for (Flight flight : flights) {
            if (flight.getFlightNumber() == flightNumber) {
                flights.remove(flight);
                removed = true;
                System.out.println("Flight removed successfully.");
                break;
            }
        }

        if (!removed) {
            System.out.println("Flight with flight number " + flightNumber + " not found.");
        }
    }

    private static void exploreDestinations(Scanner scanner) {
        System.out.println("Welcome to the Destinations section " + loggedInUser + ", Please make a selection from the menu:");
        while (true) {
            System.out.println("1. View All Destinations");
            System.out.println("2. Add a Destination");
            System.out.println("3. Remove a Destination");
            System.out.println("X. Return to Main Menu");
            System.out.print("Please enter an option: ");
            String choice = scanner.nextLine();

            switch (choice.toUpperCase()) {
                case "1":
                    viewAllDestinations();
                    break;
                case "2":
                    addDestination(scanner);
                    break;
                case "3":
                    removeDestination(scanner);
                    break;
                case "X":
                    showMainMenu(scanner);
                    return;
                default:
                    System.out.println("Please enter a valid choice, or press X to exit.");
            }
        }
    }

    private static void viewAllDestinations() {
        if (destinations.isEmpty()) {
            System.out.println("No destinations available.");
        } else {
            System.out.println("+----------------+--------------+");
            System.out.println("| Destination    | Country      |");
            System.out.println("+----------------+--------------+");
            for (Destination destination : destinations) {
                System.out.printf("| %-14s | %-12s |\n", destination.getDestination(), destination.getCountry());
            }
            System.out.println("+----------------+--------------+");
        }
    }

    private static void addDestination(Scanner scanner) {
        System.out.print("Enter destination: ");
        String destinationName = scanner.nextLine();
        System.out.print("Enter country: ");
        String country = scanner.nextLine();

        Destination newDestination = new Destination(destinationName, country);
        destinations.add(newDestination);
        System.out.println("Destination added successfully.");
    }

    private static void removeDestination(Scanner scanner) {
        System.out.print("Enter the country of the destination to remove: ");
        String country = scanner.nextLine();
        boolean removed = false;

        for (Destination destination : destinations) {
            if (destination.getCountry().equalsIgnoreCase(country)) {
                destinations.remove(destination);
                removed = true;
                System.out.println("Destination removed successfully.");
                break;
            }
        }

        if (!removed) {
            System.out.println("Destination in " + country + " not found.");
        }
    }

    private static void bookATrip(Scanner scanner) {
        Utils.bookATrip(tripDestinations, flights, destinations);
    }

    // Other getters and setters as needed

    public List<Flight> getFlights() {
        return flights;
    }

    public List<Destination> getDestinations() {
        return destinations;
    }
}



