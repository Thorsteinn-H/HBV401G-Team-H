package com.hopur7h.hotels.hopur7h.storage;

import com.hopur7h.hotels.hopur7h.model.Customer;

import java.util.ArrayList;
import java.util.List;


public class mockCustomerDB {

    private List<Customer> customers; // list to hold all customer objects in memory

    public mockCustomerDB() {
        this.customers = new ArrayList<>(); // initalizes the list of customers
    }

    // adds a new customer to the list
    public void insert(Customer customer) {
        customers.add(customer);
    }

    // deletes a customer from list based on username
    public void delete(String username) {
        customers.removeIf(customer -> customer.getUsername().equals(username));
    }

    // selects customer based on username gives back list of matched customers
    public List<Customer> select(String username) {
        if (username == null || username.isEmpty()) {
            return new ArrayList<>(customers);  // returns all customer if no filter is applied
        }
        List<Customer> matched = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getUsername().contains(username)) {
                matched.add(customer);  // adds customer if they match filter
            }
        }
        return matched;
    }

}
