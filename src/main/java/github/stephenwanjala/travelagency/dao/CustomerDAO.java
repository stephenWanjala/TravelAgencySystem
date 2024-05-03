package github.stephenwanjala.travelagency.dao;

import github.stephenwanjala.travelagency.model.Customer;

import java.util.List;

public interface CustomerDAO {
    List<Customer> getAllCustomers();
    Customer getCustomerById(int id);
    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(int id);
}
