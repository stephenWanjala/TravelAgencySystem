import java.util.LinkedList;
import java.util.List;

public class Destinations {
    private List<Destination> destinations;
    private Agency agency;

    public Destinations(Agency agency) {
        this.destinations = new LinkedList<>();
        this.agency = agency;
        addInitialDestinations();
    }

    private void addInitialDestinations() {
        addDestination(new Destination("Eiffel Tower", "France"));
        addDestination(new Destination("Opera House", "Australia"));
        addDestination(new Destination("Uluru", "Australia"));
        addDestination(new Destination("Machu Picchu", "Peru"));
        addDestination(new Destination("Great Pyramids", "Egypt"));
        addDestination(new Destination("Niagara Falls", "Canada"));
    }

    public void addDestination(Destination destination) {
        destinations.add(destination);
    }

    public void removeDestination(String name) {
        destinations.removeIf(destination -> destination.getName().equals(name));
    }

    public void viewDestinations() {
        System.out.println("+----------------------+----------------------+");
        System.out.println("| Destination Name     | Country              |");
        System.out.println("+----------------------+----------------------+");
        for (Destination destination : destinations) {
            System.out.printf("| %-20s | %-20s |%n", destination.getName(), destination.getCountry());
        }
        System.out.println("+----------------------+----------------------+");
    }
}



