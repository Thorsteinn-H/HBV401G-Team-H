package Model;

import Controller.CustomerController;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



/**
 * Nafn : Þorsteinn H. Erlendsson
 * Tölvupóstur: the85@hi.is
 * Lýsing: Later
 **/
public class TestCases {

    @Before
    public void setUp(){

        CustomerController customerController = new CustomerController();
        Customer customer = new Customer("username", "realName", "password", "email", "phoneNumber");

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