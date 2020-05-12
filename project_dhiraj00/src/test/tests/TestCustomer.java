package tests;

import model.Car;
import model.Customer;
import model.ListOfCar;
import model.ListOfCustomer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class TestCustomer {
    ListOfCustomer loc;
    ListOfCar locar;
    Car c;
    Customer a;
    Customer b;

    @BeforeEach
    public void setup() {
        a = new Customer();
        loc = new ListOfCustomer();
        locar = new ListOfCar();
        c = new Car(2000,"aaaa","bbbb",false,"cccc");
        b = new Customer(1234, "aaaa","bbbb");
        loc.addCustomers(b);
        locar.addCars(c);
    }

    @Test
    public void testCustomerConstructor() {
        assertFalse(a.getPremiumCustomer());
        assertTrue(a.getCurrentlyRented() == null);
    }

    @Test
    public void testSecondConstructor() {
        assertEquals(b.getCustomerId(),1234);
        assertTrue(b.getName().equals("aaaa"));
        assertTrue(b.getPassword().equals("bbbb"));
        assertFalse(b.getPremiumCustomer());
        assertTrue(b.getCurrentlyRented() == null);
    }

    @Test
    public void testCorrectSignIn() {
        assertTrue(a.signIn(1234,"bbbb",loc));
    }

    @Test
    public void testIncorrectIdSignIn() {
        assertFalse(a.signIn(25465,"jjjjj",loc));
    }

    @Test
    public void testIncorrectPasswordSignIn() {
        assertFalse(a.signIn(1234,"aaaa",loc));
    }

    @Test
    public void testAbleToRentSuccessfully() throws IOException {
        b.ableToRent("aaaa",locar);
        b.getCurrentlyRented().equals(c);
        assertFalse(c.getAvailable());
    }

    @Test
    public void testAbleToRentFail() throws IOException {
        b.ableToRent("bbbb",locar);
        if (b.getCurrentlyRented() != null ) {
            fail("Should be null");
        }
    }

    @Test
    public void testAbleToRentFailPremiumCar() throws IOException {
        c.setPremium(true);
        b.ableToRent("aaaa",locar);
        if (b.getCurrentlyRented() != null ) {
            fail("Should be null");
        }
        assertTrue(c.getAvailable());
    }

    @Test
    public void testAbleToRentPremiumPass() throws IOException {
        b.setPremiumCust(true);
        c.setPremium(true);
        b.ableToRent("aaaa",locar);
        b.getCurrentlyRented().equals(c);
    }

    @Test
    public void testRent() throws IOException {
        b.rent(c);
        assertFalse(c.getAvailable());
    }

    @Test
    public void testGetCustomer() {
        Customer d = a.getCustomer(1234,loc);
        assertTrue(d.getCustomerId() == 1234);
        assertTrue(d.getName().equals("aaaa"));
        assertTrue(d.getPassword().equals("bbbb"));
    }

    @Test
    public void testGetCustomerNull() {
        Customer d = a.getCustomer(12345,loc);
        if (d != null) {
            fail();
        }
    }

    @Test
    public void testUpdateReturn() {
        a.setCurrentlyRented(c);
        a.setRented(locar.getCars());
        a.updateReturn();
        if (a.getCurrentlyRented() != null) {
            fail();
        }
    }

    @Test
    public void testSetCustomerId() {
        a.setCustomerId(12356);
        assertTrue(a.getCustomerId() == 12356);
    }

    @Test
    public void testSetPassword() {
        a.setPassword("asdf");
        assertTrue(a.getPassword().equals("asdf"));
    }

    @Test
    public void testSetName() {
        a.setName("ASDF");
        assertTrue(a.getName().equals("ASDF"));
    }

    @Test
    public void testGetRentedBefore() {
        if (a.getRentedBefore()!=null) {
            fail();
        }
    }
}
