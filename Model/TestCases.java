package Model;

import Controller.CustomerController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;



/**
 * Nafn : Þorsteinn H. Erlendsson
 * Tölvupóstur: the85@hi.is
 * Lýsing: Later
 **/
public class TestCases {

    private CustomerController customerController;
    private Customer customer;


    @BeforeEach
    public void setUp(){
        customerController = new CustomerController();
        customer = new Customer("username", "realName", "password", "email", "phoneNumber");
    }
    @AfterEach
    public void tearDown(){
        customerController = null;
        customer = null;
    }
    @Test
    public void testAddCustomer() {
        customerController.addCustomer(customer);
        Customer testCustomer = customerController.getCustomer(customer.getUsername());
        assertEquals(customer,testCustomer);    
    }
    @Test
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