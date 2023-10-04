import java.util.List;

public class Trip {
    private List<Destination> destinations;
    private List<Flight> flights;

    public Trip(List<Destination> destinations, List<Flight> flights) {
        this.destinations = destinations;
        this.flights = flights;
    }

    public void displayTrip() {
        System.out.println("Trip Information");
        System.out.println("----------------");

        for (Destination destination : destinations) {
            System.out.println("Destination: " + destination.getDestination());
            System.out.println("Country: " + destination.getCountry());
            System.out.println("Flights to " + destination.getCountry() + ":");

            for (Flight flight : flights) {
                if (flight.getLanding().equalsIgnoreCase(destination.getCountry())) {
                    System.out.println(flight);
                }
            }

            System.out.println();
        }
    }

    // Other methods as needed

    public List<Destination> getDestinations() {
        return destinations;
    }

    public List<Flight> getFlights() {
        return flights;
    }
}



