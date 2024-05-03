package github.stephenwanjala.travelagency.model;

import javax.annotation.processing.Generated;

public class Flight {
    private int id;
    private String origin;
    private String destination;
    private int availableSeats;

    public Flight(int id, String origin, String destination, int availableSeats) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.availableSeats = availableSeats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    @Override
    public String toString() {
        return "Flight{" + "id=" + id + ", origin='" + origin + '\'' + ", destination='" + destination + '\'' + ", availableSeats=" + availableSeats + '}';
    }
}