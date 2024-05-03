package github.stephenwanjala.travelagency;

import github.stephenwanjala.TravelAgencyGUI;
import github.stephenwanjala.travelagency.dao.*;

import javax.swing.*;


public class Main extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CustomerDAO customerDAO = new CustomerDAOImpl();
            FlightDAO flightDAO = new FlightDAOImpl();
            BookingDAO bookingDAO = new BookingDAOImpl();

            TravelAgencyGUI mainFrame = new TravelAgencyGUI();
            mainFrame.getCustomerPanel().setCustomerDAO(customerDAO);
            mainFrame.getFlightPanel().setFlightDAO(flightDAO);
            mainFrame.getBookingPanel().setBookingDAO(bookingDAO);

            mainFrame.setVisible(true);
        });
    }


}

