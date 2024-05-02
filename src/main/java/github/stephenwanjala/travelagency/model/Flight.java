package github.stephenwanjala.travelagency.model;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public class Flight {
    @NotNull
    final String origin;
    @NotNull
    final LocalDateTime arrivalTime;
    @NotNull
    private final String id;
    @NotNull
    private final String destination;
    @NotNull
    private final LocalDateTime departureTime;
    @NotNull int availableSeats;
    @NotNull double price;
    @NotNull
    private FlightStatus status;

    public Flight(@NotNull String id, @NotNull String origin, @NotNull String destination, @NotNull LocalDateTime departureTime, @NotNull LocalDateTime arrivalTime, int availableSeats, double price, @NotNull FlightStatus status) {
        // Validate input parameters
        if (availableSeats < 0 || price < 0) {
            throw new IllegalArgumentException("Invalid flight details");
        }

        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableSeats = availableSeats;
        this.price = price;
        this.status = status;
    }

    // Getter methods
    public @NotNull String getId() {
        return id;
    }

    public @NotNull String getOrigin() {
        return origin;
    }

    public @NotNull String getDestination() {
        return destination;
    }

    public @NotNull LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public @NotNull LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getPrice() {
        return price;
    }

    public @NotNull FlightStatus getStatus() {
        return status;
    }

    // Method to book a seat on the flight
    public boolean bookSeat() {
        if (availableSeats > 0) {
            availableSeats--;
            return true;
        }
        return false;
    }

    // Method to cancel a booking on the flight
    public void cancelBooking() {
        availableSeats++;
    }

    // Method to update the status of the flight
    public void updateStatus(FlightStatus newStatus) {
        this.status = newStatus;
    }

    // Method to update the price of the flight
    public void updatePrice(double newPrice) {
        if (newPrice >= 0) {
            this.price = newPrice;
        } else {
            throw new IllegalArgumentException("Invalid price");
        }
    }

    @Override
    public String toString() {
        return "Flight{" + "id='" + id + '\'' + ", origin='" + origin + '\'' + ", destination='" + destination + '\'' + ", departureTime=" + departureTime + ", arrivalTime=" + arrivalTime + ", availableSeats=" + availableSeats + ", price=" + price + ", status=" + status + '}';
    }
}
