package github.stephenwanjala.travelagency.dao;

import github.stephenwanjala.travelagency.model.Booking;

import java.util.List;

public interface BookingDAO {
    List<Booking> getAllBookings();
    Booking getBookingById(int id);
    void addBooking(Booking booking);
    void deleteBooking(int id);
}
