package service;

import model.Customer;

import java.util.*;

public class CustomerService {

    private static CustomerService INSTANCE;
    private static final Map<String, Customer> allCustomers = new HashMap<>();

    public CustomerService() {
    }

    public static CustomerService getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CustomerService();
        }
        return INSTANCE;
    }

    public void addCustomer(String email, String firstName, String lastName) {
        if (allCustomers.containsKey(email)) {
            throw new IllegalArgumentException("This account already exists.");
        } else {
            Customer newCustomer = new Customer(firstName, lastName, email);
            allCustomers.put(email, newCustomer);
        }
    }

    public Customer getCustomer(String customerEmail) {
        if (customerEmail != null && !customerEmail.equals("")) {
            return allCustomers.get(customerEmail);
        }

        return null;
    }

    public Collection<Customer> getAllCustomers() {
        return allCustomers.values();
    }
}
