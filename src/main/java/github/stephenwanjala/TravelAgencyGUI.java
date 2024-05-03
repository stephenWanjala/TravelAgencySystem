package github.stephenwanjala;

import github.stephenwanjala.travelagency.dao.*;
import github.stephenwanjala.travelagency.view.BookingPanel;
import github.stephenwanjala.travelagency.view.CustomerPanel;
import github.stephenwanjala.travelagency.view.FlightPanel;

import javax.swing.*;
import java.awt.*;

public class TravelAgencyGUI extends JFrame {
    private CustomerPanel customerPanel;
    private FlightPanel flightPanel;
    private BookingPanel bookingPanel;

    public TravelAgencyGUI() {
        setTitle("Travel Agency Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null); // Center the frame on screen

        // Initialize DAOs
        CustomerDAO customerDAO = new CustomerDAOImpl();
        FlightDAO flightDAO = new FlightDAOImpl();
        BookingDAO bookingDAO = new BookingDAOImpl();

        // Initialize panels with DAOs
        customerPanel = new CustomerPanel(customerDAO);
        flightPanel = new FlightPanel(flightDAO);
        bookingPanel = new BookingPanel(bookingDAO);

        // Create a tabbed pane to organize different functionalities
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Customers", customerPanel);
        tabbedPane.addTab("Flights", flightPanel);
        tabbedPane.addTab("Bookings", bookingPanel);

        // Add the tabbed pane to the main frame
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }

    public CustomerPanel getCustomerPanel() {
        return this.customerPanel;
    }

    public FlightPanel getFlightPanel() {
        return this.flightPanel;
    }

    public BookingPanel getBookingPanel() {
        return this.bookingPanel;
    }
}
