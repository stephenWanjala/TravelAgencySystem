public class Destination {
    private String name;
    private String country;

    public Destination(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getDestination() {
        return name; // Added getDestination() method
    }

    public void displayDestination() {
        System.out.println("Destination: " + name + " in " + country);
    }
}



