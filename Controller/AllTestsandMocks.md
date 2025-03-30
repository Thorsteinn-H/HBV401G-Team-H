# Text figure and mock object documentation

This documentation shows an overview of the tests and mock components implemented for the hotel booking system.

To provide context for the test and mock components, the supporting classes `CustomerController.java` and `Customer.java` are also provided in this file. 

# Controller

## CustomerController.java 
```
package Controller;

import Model.Customer;

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
 ```

# Model 

## Customer.java
```
package Model;

public class Customer {


    private String username;
    private String realName;
    private String password;
    private String email;
    private String phoneNumber;

    // constructs a new customer object with details
    public Customer(String username, String realName, String password, String email, String phoneNumber) {
        this.username = username;
        this.realName = realName;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // retrieves username of the customer
    public String getUsername() {
        return username;
    }

    // retrieves real name of the customer
    public String getName() {
        return realName;
    }

    // retrieves the email address of the customer
    public String getEmail() {
        return email;
    }

    // retrieves the phone number of the customer
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // checks if given input matches the customer passwords
    public boolean verifyPassword(String input) {
        return password.equals(input);
    }

}
 ```

## TestCases.java
```
package Model;

import Controller.CustomerController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestCases {

    private CustomerController customerController;
    private Customer customer;


    @BeforeEach // sets up ojects before every test
    public void setUp() {
        customerController = new CustomerController();
        customer = new Customer("username", "realName", "password", "email", "phoneNumber");
    }

    @AfterEach  // reset after each test
    public void tearDown() {
        customerController = null;
        customer = null;
    }

    @Test   // test adding a costumer
    public void testAddCustomer() {
        customerController.addCustomer(customer);
        Customer testCustomer = customerController.getCustomer(customer.getUsername());
        assertEquals(customer, testCustomer);
    }

    @Test  // test removing a costumer
    public void testRemoveCustomer() {
        //add customer from fresh plate (assumes previous test works and input customerController is empty)
        customerController.addCustomer(customer);
        Customer testCustomer = customerController.getCustomer(customer.getUsername());

        //Removes based on previous getcustomer
        customerController.removeCustomer(testCustomer.getUsername());
        List<Customer> listCustomers = customerController.getAllCustomers();
        assertTrue(listCustomers.isEmpty());

    }

}
```

# Storage

## mockCustomerDB.java
```
package Storage;

import Model.Customer;

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
 ```



