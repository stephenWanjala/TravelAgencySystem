package github.stephenwanjala.travelagency.dao;

import github.stephenwanjala.travelagency.model.Customer;
import github.stephenwanjala.travelagency.utlis.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    private static final String SELECT_ALL_CUSTOMERS = "SELECT * FROM Customers";
    private static final String SELECT_CUSTOMER_BY_ID = "SELECT * FROM Customers WHERE id = ?";
    private static final String INSERT_CUSTOMER = "INSERT INTO Customers (name, email) VALUES (?, ?)";
    private static final String UPDATE_CUSTOMER = "UPDATE Customers SET name = ?, email = ? WHERE id = ?";
    private static final String DELETE_CUSTOMER = "DELETE FROM Customers WHERE id = ?";

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_CUSTOMERS)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String email = rs.getString("email");
                customers.add(new Customer(id, name, email));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.printf("Error %s\n", e.getMessage());
            System.out.printf("Error getting all customers%n");
        }
        return customers;
    }

    @Override
    public Customer getCustomerById(int id) {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_CUSTOMER_BY_ID)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String email = rs.getString("email");
                    return new Customer(id, name, email);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.printf("Error %s\n", e.getMessage());
            System.out.printf("Error getting customer with id %d%n", id);
        }
        return null;
    }

    @Override
    public void addCustomer(Customer customer) {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_CUSTOMER)) {
            stmt.setString(1, customer.name());
            stmt.setString(2, customer.email());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.printf("Error %s\n", e.getMessage());
            System.out.printf("Error adding customer %s%n", customer.name());
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_CUSTOMER)) {
            stmt.setString(1, customer.name());
            stmt.setString(2, customer.email());
            stmt.setInt(3, customer.id());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.printf("Error %s\n", e.getMessage());
            System.out.printf("Error updating customer with id %d%n", customer.id());
        }
    }

    @Override
    public void deleteCustomer(int id) {
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_CUSTOMER)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.printf("Error %s\n", e.getMessage());
            System.out.printf("Error deleting customer with id %d%n", id);
        }
    }
}
