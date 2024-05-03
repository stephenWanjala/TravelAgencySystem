package github.stephenwanjala.travelagency.view;

import github.stephenwanjala.travelagency.dao.BookingDAO;
import github.stephenwanjala.travelagency.dao.CustomerDAO;
import github.stephenwanjala.travelagency.dao.FlightDAO;
import github.stephenwanjala.travelagency.model.Booking;
import github.stephenwanjala.travelagency.model.Customer;
import github.stephenwanjala.travelagency.model.Flight;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class BookingPanel extends JPanel {
    private JComboBox<Customer> customerComboBox;
    private JComboBox<Flight> flightComboBox;
    private JButton bookButton;
    private JTable bookingTable;
    private DefaultTableModel tableModel;
    private BookingDAO bookingDAO;
    private final CustomerDAO customerDAO;
    private final FlightDAO flightDAO;

    public BookingPanel(CustomerDAO customerDAO, FlightDAO flightDAO, BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
        this.customerDAO = customerDAO;
        this.flightDAO = flightDAO;

        setLayout(new BorderLayout());

        // Panel for booking inputs (customer, flight, book button)
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        customerComboBox = new JComboBox<>();
        flightComboBox = new JComboBox<>();
        bookButton = new JButton("Book Now");

        inputPanel.add(new JLabel("Select Customer:"));
        inputPanel.add(customerComboBox);
        inputPanel.add(Box.createVerticalStrut(10)); // Vertical spacing
        inputPanel.add(new JLabel("Select Flight:"));
        inputPanel.add(flightComboBox);
        inputPanel.add(Box.createVerticalStrut(10)); // Vertical spacing
        inputPanel.add(bookButton);

        // Panel for booking table
        JPanel bookingTablePanel = new JPanel(new BorderLayout());
        tableModel = new DefaultTableModel(new String[]{"Booking ID", "Customer", "Flight"}, 0);
        bookingTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(bookingTable);
        bookingTablePanel.add(new JLabel("List of Bookings"), BorderLayout.NORTH);
        bookingTablePanel.add(tableScrollPane, BorderLayout.CENTER);

        // Add components to the BookingPanel
        add(inputPanel, BorderLayout.NORTH);
        add(bookingTablePanel, BorderLayout.CENTER);

        // Populate customer and flight combo boxes
        populateCustomerComboBox(customerDAO.getAllCustomers());
        populateFlightComboBox(flightDAO.getAllFlights());

        // Populate initial booking table
        refreshBookingTable();

        // Add action listener to bookButton
        bookButton.addActionListener(e -> {
            Customer selectedCustomer = (Customer) customerComboBox.getSelectedItem();
            Flight selectedFlight = (Flight) flightComboBox.getSelectedItem();
            if (selectedCustomer != null && selectedFlight != null) {
                bookingDAO.addBooking(new Booking(0, selectedCustomer.id(), selectedFlight.getId()));
                refreshBookingTable();
                JOptionPane.showMessageDialog(this, "Booking successful!");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a customer and a flight.");
            }
        });
    }

    private void populateCustomerComboBox(List<Customer> customers) {
        for (Customer customer : customers) {
            customerComboBox.addItem(customer);
        }
    }

    private void populateFlightComboBox(List<Flight> flights) {
        for (Flight flight : flights) {
            flightComboBox.addItem(flight);
        }
    }

    private void refreshBookingTable() {
        // Clear existing rows in the table
        tableModel.setRowCount(0);

        // Retrieve list of bookings from the database
        List<Booking> bookings = bookingDAO.getAllBookings();

        // Populate the table with booking data
        for (Booking booking : bookings) {
            Customer customer = customerDAO.getCustomerById(booking.customerId());
            Flight flight = flightDAO.getFlightById(booking.flightId());
            if (customer != null && flight != null) {
                Object[] rowData = {booking.id(), customer.name(), flight.getOrigin() + " - " + flight.getDestination()};
                tableModel.addRow(rowData);
            }
        }
    }

    public void setBookingDAO(BookingDAO bookingDAO) {
        this.bookingDAO = bookingDAO;
    }
}
