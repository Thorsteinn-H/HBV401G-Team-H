package com.hopur7h.hotels.hopur7h.controller;

/**
 * Nafn : Þorsteinn H. Erlendsson
 * Tölvupóstur: the85@hi.is
 * Lýsing:
 **/

import com.hopur7h.hotels.hopur7h.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerController {

    private List<Customer> customers = new ArrayList<>();

    // retrieves a customer based on username
    public Customer getCustomer(String username) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) {
                return customer;
            }
        }
        return null; // or throw an exception if not found
    }

    // retrieves all customers managed by this controller
    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }

    // adds costumer to the list
    public void addCustomer(Customer customer) {
        customers.add(customer);

    }

    // removes customer from the list
    public void removeCustomer(String username) {
        customers.removeIf(customer -> customer.getUsername().equals(username));
    }


}
