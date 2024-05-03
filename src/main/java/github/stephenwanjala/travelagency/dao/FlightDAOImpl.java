package github.stephenwanjala.travelagency.dao;

import github.stephenwanjala.travelagency.model.Flight;
import github.stephenwanjala.travelagency.utlis.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightDAOImpl implements FlightDAO {
    private static final String SELECT_ALL_FLIGHTS = "SELECT * FROM Flights";
    private static final String SELECT_FLIGHT_BY_ID = "SELECT * FROM Flights WHERE id = ?";
    private static final String INSERT_FLIGHT = "INSERT INTO Flights (origin, destination, available_seats) VALUES (?, ?, ?)";
    private static final String UPDATE_FLIGHT = "UPDATE Flights SET origin = ?, destination = ?, available_seats = ? WHERE id = ?";
    private static final String DELETE_FLIGHT = "DELETE FROM Flights WHERE id = ?";

    @Override
    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_FLIGHTS)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String origin = rs.getString("origin");
                String destination = rs.getString("destination");
                int availableSeats = rs.getInt("available_seats");
                flights.add(new Flight(id, origin, destination, availableSeats));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.printf("Error %s\n", e.getMessage());
            System.out.printf("Error getting all flights%n");
        }
        return flights;
    }

    @Override
    public Flight getFlightById(int id) {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_FLIGHT_BY_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String origin = rs.getString("origin");
                    String destination = rs.getString("destination");
                    int availableSeats = rs.getInt("available_seats");
                    return new Flight(id, origin, destination, availableSeats);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.printf("Error %s\n", e.getMessage());
            System.out.printf("Error getting flight with id %d%n", id);
        }
        return null;
    }

    @Override
    public void addFlight(Flight flight) {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_FLIGHT, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, flight.getOrigin());
            stmt.setString(2, flight.getDestination());
            stmt.setInt(3, flight.getAvailableSeats());
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
                flight.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.printf("Error %s\n", e.getMessage());
            System.out.printf("Error adding flight with id %d%n", flight.getId());
        }
    }

    @Override
    public void updateFlight(Flight flight) {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_FLIGHT)) {
            stmt.setString(1, flight.getOrigin());
            stmt.setString(2, flight.getDestination());
            stmt.setInt(3, flight.getAvailableSeats());
            stmt.setInt(4, flight.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.printf("Error %s\n", e.getMessage());
            System.out.printf("Error updating flight with id %d%n", flight.getId());
        }
    }

    @Override
    public void deleteFlight(int id) {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_FLIGHT)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.printf("Error %s\n", e.getMessage());
            System.out.printf("Error deleting flight with id %d%n", id);
        }
    }
}