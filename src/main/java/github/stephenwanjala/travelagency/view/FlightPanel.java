package github.stephenwanjala.travelagency.view;

import github.stephenwanjala.travelagency.dao.FlightDAO;
import github.stephenwanjala.travelagency.model.Flight;

import javax.swing.*;
import java.awt.*;
import java.util.List;
public class FlightPanel extends JPanel {
    private JTextArea flightListArea;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private FlightDAO flightDAO;

    public FlightPanel(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;

        setLayout(new BorderLayout());

        flightListArea = new JTextArea(20, 40);
        flightListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(flightListArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addButton = new JButton("Add Flight");
        editButton = new JButton("Edit Flight");
        deleteButton = new JButton("Delete Flight");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners to buttons
        addButton.addActionListener(e -> {
            String origin = JOptionPane.showInputDialog(this, "Enter origin:");
            if (origin != null && !origin.isEmpty()) {
                String destination = JOptionPane.showInputDialog(this, "Enter destination:");
                if (destination != null && !destination.isEmpty()) {
                    String availableSeatsStr = JOptionPane.showInputDialog(this, "Enter available seats:");
                    if (availableSeatsStr != null && !availableSeatsStr.isEmpty()) {
                        int availableSeats = Integer.parseInt(availableSeatsStr);
                        Flight newFlight = new Flight(1,origin, destination, availableSeats);
                        flightDAO.addFlight(newFlight);
                        refreshFlightList();
                    }
                }
            }
        });

        // Implement editButton and deleteButton action listeners similarly

        // Refresh flight list initially
        refreshFlightList();
    }

    public void refreshFlightList() {
        List<Flight> flights = flightDAO.getAllFlights();
        StringBuilder sb = new StringBuilder();
        for (Flight flight : flights) {
            sb.append(flight.getId()).append(": ")
                    .append(flight.getOrigin()).append(" - ")
                    .append(flight.getDestination()).append(" (")
                    .append(flight.getAvailableSeats()).append(" seats)\n");
        }
        flightListArea.setText(sb.toString());
    }

    public void setFlightDAO(FlightDAO flightDAO) {
        this.flightDAO = flightDAO;
    }
}