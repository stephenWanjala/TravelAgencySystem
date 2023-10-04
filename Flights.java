import java.util.LinkedList;

public class Flights {
    private LinkedList<Flight> flights;
    private Agency agency;

    public Flights(Agency agency) {
        this.flights = new LinkedList<>();
        this.agency = agency;
        addInitialFlights();
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public boolean hasFlight(String takeoff, String landing) {
        for (Flight flight : flights) {
            if (flight.getTakeoff().equals(takeoff) && flight.getLanding().equals(landing)) {
                return true;
            }
        }
        return false;
    }

    private void addInitialFlights() {
        addFlight(new Flight("American Airlines", 232, "France", "Australia", 120.99));
        addFlight(new Flight("QANTAS", 189, "Australia", "Peru", 559.20));
        addFlight(new Flight("United Airlines", 144, "France", "Peru", 380.75));
        addFlight(new Flight("JetStar", 99, "Peru", "Australia", 440.00));
        // Add more initial flights as needed
    }
}



