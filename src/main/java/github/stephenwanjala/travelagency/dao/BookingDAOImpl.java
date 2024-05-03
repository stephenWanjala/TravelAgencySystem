package github.stephenwanjala.travelagency.dao;

import github.stephenwanjala.travelagency.model.Booking;
import github.stephenwanjala.travelagency.utlis.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingDAOImpl implements BookingDAO {
    private static final String SELECT_ALL_BOOKINGS = "SELECT * FROM Bookings";
    private static final String SELECT_BOOKING_BY_ID = "SELECT * FROM Bookings WHERE id = ?";
    private static final String INSERT_BOOKING = "INSERT INTO Bookings (customer_id, flight_id) VALUES (?, ?)";
    private static final String DELETE_BOOKING = "DELETE FROM Bookings WHERE id = ?";
    private static final String BOOK_FLIGHT = "UPDATE Flights SET available_seats = available_seats - 1 WHERE id = ?";

    private Connection connection; // Connection member

    public BookingDAOImpl() throws SQLException {
        this.connection = DatabaseUtil.getConnection();
    }

    @Override
    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(SELECT_ALL_BOOKINGS)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int customerId = rs.getInt("customer_id");
                int flightId = rs.getInt("flight_id");
                bookings.add(new Booking(id, customerId, flightId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.printf("Error getting all bookings: %s\n", e.getMessage());
        }
        return bookings;
    }

    @Override
    public Booking getBookingById(int id) {
        try (PreparedStatement stmt = connection.prepareStatement(SELECT_BOOKING_BY_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int customerId = rs.getInt("customer_id");
                    int flightId = rs.getInt("flight_id");
                    return new Booking(id, customerId, flightId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.printf("Error getting booking with id %d: %s\n", id, e.getMessage());
        }
        return null;
    }

    @Override
    public void addBooking(Booking booking) {
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_BOOKING)) {
            stmt.setInt(1, booking.customerId());
            stmt.setInt(2, booking.flightId());
            stmt.executeUpdate();
            bookFlight(booking.flightId()); // Update flight available seats
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.printf("Error adding booking for customer %d: %s\n", booking.customerId(), e.getMessage());
        }
    }

    @Override
    public void deleteBooking(int id) {
        try (PreparedStatement stmt = connection.prepareStatement(DELETE_BOOKING)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.printf("Error deleting booking with id %d: %s\n", id, e.getMessage());
        }
    }

//    @Override
//    public void bookFlight(int customerId, int flightId) {
//
//    }

    private void bookFlight(int flightId) {
        try (PreparedStatement stmt = connection.prepareStatement(BOOK_FLIGHT)) {
            stmt.setInt(1, flightId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.printf("Error booking flight with id %d: %s\n", flightId, e.getMessage());
        }
    }
}
