package github.stephenwanjala.travelagency.dao;

import github.stephenwanjala.travelagency.model.Flight;

import java.util.List;

public interface FlightDAO {
    List<Flight> getAllFlights();
    Flight getFlightById(int id);
    void addFlight(Flight flight);
    void updateFlight(Flight flight);
    void deleteFlight(int id);
}
