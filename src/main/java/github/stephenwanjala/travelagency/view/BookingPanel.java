package github.stephenwanjala.travelagency.view;

import github.stephenwanjala.travelagency.dao.BookingDAO;

import javax.swing.*;
import java.awt.*;
import  java.awt.event.ActionListener;
public class BookingPanel extends JPanel {
    private JComboBox<String> customerComboBox;
    private JComboBox<String> flightComboBox;
    private JButton bookButton;
    private BookingDAO bookingDAO;

    public BookingPanel(BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;

        setLayout(new GridLayout(3, 2));

        customerComboBox = new JComboBox<>();
        flightComboBox = new JComboBox<>();
        bookButton = new JButton("Book Now");

        add(new JLabel("Select Customer:"));
        add(customerComboBox);
        add(new JLabel("Select Flight:"));
        add(flightComboBox);
        add(new JLabel()); // Empty label for spacing
        add(bookButton);

        // Populate customer and flight combo boxes
        // Assuming DAO methods to fetch customer and flight data

        // Add action listener to bookButton
        bookButton.addActionListener(e -> {
            String selectedCustomer = (String) customerComboBox.getSelectedItem();
            String selectedFlight = (String) flightComboBox.getSelectedItem();
            if (selectedCustomer != null && selectedFlight != null) {
                // Extract customer ID and flight ID from selected strings
                int customerId = Integer.parseInt(selectedCustomer.split(":")[0].trim());
                int flightId = Integer.parseInt(selectedFlight.split(":")[0].trim());
                bookingDAO.bookFlight(customerId, flightId);
                JOptionPane.showMessageDialog(this, "Booking successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a customer and a flight.");
            }
        });
    }

    public void setCustomerOptions(String[] customers) {
        customerComboBox.setModel(new DefaultComboBoxModel<>(customers));
    }

    public void setFlightOptions(String[] flights) {
        flightComboBox.setModel(new DefaultComboBoxModel<>(flights));
    }

    public void setBookingDAO(BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }
}