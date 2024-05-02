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

    @Override
    public List<Booking> getAllBookings() {
        List<Booking> bookings = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_BOOKINGS)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                int customerId = rs.getInt("customer_id");
                int flightId = rs.getInt("flight_id");
                bookings.add(new Booking(customerId, flightId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookings;
    }

    @Override
    public Booking getBookingById(int id) {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BOOKING_BY_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int customerId = rs.getInt("customer_id");
                    int flightId = rs.getInt("flight_id");
                    return new Booking(customerId, flightId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void addBooking(Booking booking) {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_BOOKING)) {
            stmt.setInt(1, booking.customerId());
            stmt.setInt(2, booking.flightId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBooking(int id) {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_BOOKING)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
