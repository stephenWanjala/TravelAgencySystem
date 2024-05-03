package github.stephenwanjala.travelagency;

import github.stephenwanjala.TravelAgencyGUI;
import github.stephenwanjala.travelagency.dao.*;

import javax.swing.*;
import java.sql.SQLException;


public class Main extends JFrame {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CustomerDAO customerDAO = new CustomerDAOImpl();
            FlightDAO flightDAO = new FlightDAOImpl();
            BookingDAO bookingDAO = null;
            try {
                bookingDAO = new BookingDAOImpl();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            TravelAgencyGUI mainFrame = null;
            try {
                mainFrame = new TravelAgencyGUI();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            mainFrame.getCustomerPanel().setCustomerDAO(customerDAO);
            mainFrame.getFlightPanel().setFlightDAO(flightDAO);
            mainFrame.getBookingPanel().setBookingDAO(bookingDAO);


            mainFrame.setVisible(true);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        });
    }


}

