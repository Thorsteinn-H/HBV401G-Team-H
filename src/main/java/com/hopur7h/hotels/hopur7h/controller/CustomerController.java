package com.hopur7h.hotels.hopur7h.controller;

import com.hopur7h.hotels.hopur7h.model.Customer;
import com.hopur7h.hotels.hopur7h.storage.CustomerDB;

import java.util.List;

public class CustomerController {

    private CustomerDB customerDB = new CustomerDB();

    // retrieves a customer based on username
    public Customer getCustomer(String username) {
        return customerDB.getAllCustomers().stream()
                .filter(c -> c.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    // retrieves all customers from the database
    public List<Customer> getAllCustomers() {
        return customerDB.getAllCustomers();
    }

    // adds a customer to the database
    public void addCustomer(Customer customer) {
        customerDB.addCustomer(
                customer.getUsername(),
                customer.getName(),
                "default",
                customer.getEmail(),
                customer.getPhoneNumber()
        );
    }
/**
 public void removeCustomer(String username) {
 List<Customer> all = customerDB.getAllCustomers();
 for (Customer c : all) {
 if (c.getUsername().equals(username)) {

 }
 }
 }
 **/
}
