import java.util.List;

public class Flight {
    private String airline;
    private int flightNumber;
    private String takeoff;
    private String landing;
    private double cost;

    public Flight(String airline, int flightNumber, String takeoff, String landing, double cost) {
        this.airline = airline;
        this.flightNumber = flightNumber;
        this.takeoff = takeoff;
        this.landing = landing;
        this.cost = cost;
    }

    public String getAirline() {
        return airline;
    }

    public int getFlightNumber() {
        return flightNumber;
    }

    public String getTakeoff() {
        return takeoff;
    }

    public String getLanding() {
        return landing;
    }

    public double getCost() {
        return cost;
    }

    @Override
    public String toString() {
        return airline + " Flight " + flightNumber + " from " + takeoff + " to " + landing + " for " + cost;
    }

    public static boolean hasFlight(List<Flight> flights, String takeoff, String landing) {
        for (Flight flight : flights) {
            if (flight.getTakeoff().equalsIgnoreCase(takeoff) && flight.getLanding().equalsIgnoreCase(landing)) {
                return true;
            }
        }
        return false;
    }

    public static void addFlight(List<Flight> flights, Flight flight) {
        flights.add(flight);
    }
}



