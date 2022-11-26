package service;

import model.Customer;

import java.util.*;

public class CustomerService {

    private static final Map<String, Customer> allCustomers = new HashMap<>();

    public CustomerService() {
    }

    public static void addCustomer(String email, String firstName, String lastName) {
        Customer newCustomer = new Customer(firstName, lastName, email);
        allCustomers.put(email, newCustomer);
    }

    // TODO refactor CustomerService.getCustomer
    public static Customer getCustomer(String customerEmail) {
        if (customerEmail != null && customerEmail != "") {
            return allCustomers.get(customerEmail);
        }

        return null;
    }

    // TODO revisit Customer.getAllCustomers
    public static Collection<Customer> getAllCustomers() {
        return allCustomers.values();
    }
}
