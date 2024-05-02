package github.stephenwanjala.travelagency.model;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;

public record Booking(@NotNull String id,@NotNull Customer customer,@NotNull Flight flight,@NotNull LocalDateTime bookingTime) {


    // Method to get the total cost of the booking
    public double getTotalCost() {
        return flight.getPrice();
    }
}
