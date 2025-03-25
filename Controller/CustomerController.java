package Controller;

/**
 * Nafn : Þorsteinn H. Erlendsson
 * Tölvupóstur: the85@hi.is
 * Lýsing:
 **/

import Model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerController {

    private List<Customer> customers = new ArrayList<>();

    public Customer getCustomer(String username) {
        for (Customer customer : customers) {
            if (customer.getUsername().equals(username)) {
                return customer;
            }
        }
        return null; // or throw an exception if not found
    }

    public List<Customer> getAllCustomers() {
        return new ArrayList<>(customers);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void removeCustomer(String username) {
        customers.removeIf(customer -> customer.getUsername().equals(username));
    }





}
