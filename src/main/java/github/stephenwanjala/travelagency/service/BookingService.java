package github.stephenwanjala.travelagency.service;

import github.stephenwanjala.travelagency.model.Booking;

import java.util.List;

public interface BookingService {
    List<Booking> getAllBookings();
    boolean makeBooking(int customerId, int flightId);
    void cancelBooking(int bookingId);
}
