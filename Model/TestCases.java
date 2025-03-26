package Model;

import Controller.CustomerController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



/**
 * Nafn : Þorsteinn H. Erlendsson
 * Tölvupóstur: the85@hi.is
 * Lýsing: Later
 **/
public class TestCases {

    @BeforeEach
    public void setUp(){

        CustomerController customerController = new CustomerController();
        Customer customer = new Customer("username", "realName", "password", "email", "phoneNumber");

    }
    @AfterEach
    public void tearDown(){
        customerController = null;
        customer = null;
    }

    @Test
    public void testCustomer() {
        CustomerController.addCustomer(customer);
        testCustomer = CustomerController.getCustomer(customer.getUsername());
        assertEquals(customer,testCustomer(););
        CustomerController.removeCustomer(testCustomer.getUsername());
        listCustomers = CustomerController.getAllCustomers();
        assertTrue(listCustomers.isEmpty());
    }

}