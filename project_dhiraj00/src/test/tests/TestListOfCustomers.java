package tests;

import model.Customer;
import model.ListOfCustomer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestListOfCustomers {
    ListOfCustomer loc;
    Customer c;

    @BeforeEach
    public void setup() {
        loc = new ListOfCustomer();
        c = new Customer(123,"aaaa","password");
    }

    @Test
    public void testAddCustomers() {
        assertTrue(loc.getCustomers().size() == 0);
        loc.addCustomers(c);
        assertTrue(loc.getCustomers().size() == 1);
    }

    @Test
    public void testCorrectIdRight() {
        loc.addCustomers(c);
        assertTrue(loc.correctId(123));
    }

    @Test
    public void testCorrectIdWrong() {
        loc.addCustomers(c);
        assertFalse(loc.correctId(156));
    }
}
