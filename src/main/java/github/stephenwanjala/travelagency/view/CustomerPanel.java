package github.stephenwanjala.travelagency.view;

import github.stephenwanjala.travelagency.dao.CustomerDAO;
import github.stephenwanjala.travelagency.model.Customer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CustomerPanel extends JPanel {
    private JTextArea customerListArea;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private CustomerDAO customerDAO;

    public CustomerPanel(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;

        setLayout(new BorderLayout());

        customerListArea = new JTextArea(20, 40);
        customerListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(customerListArea);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        addButton = new JButton("Add Customer");
        editButton = new JButton("Edit Customer");
        deleteButton = new JButton("Delete Customer");
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add action listeners to buttons
        addButton.addActionListener(e -> {
            String name = JOptionPane.showInputDialog(this, "Enter customer name:");
            if (name != null && !name.isEmpty()) {
                String email = JOptionPane.showInputDialog(this, "Enter customer email:");
                if (email != null && !email.isEmpty()) {
                    Customer newCustomer = new Customer(1,name, email);
                    customerDAO.addCustomer(newCustomer);
                    refreshCustomerList();
                }
            }
        });

        editButton.addActionListener(e -> {
            String customerIdStr = JOptionPane.showInputDialog(this, "Enter customer ID to edit:");
            if (customerIdStr != null && !customerIdStr.isEmpty()) {
                int customerId = Integer.parseInt(customerIdStr);
                String name = JOptionPane.showInputDialog(this, "Enter new name:");
                String email = JOptionPane.showInputDialog(this, "Enter new email:");
                Customer updatedCustomer = new Customer(customerId, name, email);
                customerDAO.updateCustomer(updatedCustomer);
                refreshCustomerList();
            }
        });

        deleteButton.addActionListener(e -> {
            String customerIdStr = JOptionPane.showInputDialog(this, "Enter customer ID to delete:");
            if (customerIdStr != null && !customerIdStr.isEmpty()) {
                int customerId = Integer.parseInt(customerIdStr);
                customerDAO.deleteCustomer(customerId);
                refreshCustomerList();
            }
        });

        // Refresh customer list initially
        refreshCustomerList();
    }

    public void refreshCustomerList() {
        List<Customer> customers = customerDAO.getAllCustomers();
        StringBuilder sb = new StringBuilder();
        for (Customer customer : customers) {
            sb.append(customer.id()).append(": ")
                    .append(customer.name()).append(" - ")
                    .append(customer.email()).append("\n");
        }
        customerListArea.setText(sb.toString());
    }

    public void setCustomerDAO(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }
}