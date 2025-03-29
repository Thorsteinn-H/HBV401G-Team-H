package Storage;

import Model.Customer;

import java.util.ArrayList;
import java.util.List;


public class mockCustomerDB {
    
    private List<Customer> customers;

    public mockCustomerDB() {
        this.customers = new ArrayList<>();
    }
    public void insert(Customer customer) {
        customers.add(customer);
    }
    public void delete(String username) {
        customers.removeIf(customer -> customer.getUsername().equals(username));
    }
    public List<Customer> select(String username) {
        if (username == null || username.isEmpty()) {
            return new ArrayList<>(customers); 
        }
        List<Customer> matched = new ArrayList<>();
        for (Customer customer : customers) {
            if (customer.getUsername().contains(username)) {
                matched.add(customer);
            }
        }
        return matched;
    }

 }
