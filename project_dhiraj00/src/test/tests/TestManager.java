package tests;

import model.Customer;
import model.ListOfCustomer;
import model.ListOfManager;
import model.Manager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestManager {
    Manager m;
    ListOfManager lom;

    @BeforeEach
    public void setup() {
        m = new Manager(123,"password");
        lom = new ListOfManager();
        lom.addManagers(m);
    }

    @Test
    public void testManagerConstructor() {
        Manager n = new Manager(123);
        assertTrue(n.getManagerId() == 123);
    }

    @Test
    public void testSecondConstructor() {
        assertTrue(m.getManagerId() == 123);
        assertTrue(m.getPassword().equals("password"));
    }


    @Test
    public void testSecondIncorrectConstructor() {
        assertFalse(m.getManagerId() == 1234);
        assertFalse(m.getPassword().equals("Password"));
    }

    @Test
    public void testCheckManagerCorrect() {
        lom.addManagers(m);
        assertTrue(m.checkManager(123,lom));
    }

    @Test
    public void testCheckManagerIncorrect() {
        lom.addManagers(m);
        assertFalse(m.checkManager(1234,lom));
    }

    @Test
    public void testUpgradeCustomerWorks() {
        Customer b = new Customer(1234, "aaaa","bbbb");
        ListOfCustomer loc = new ListOfCustomer();
        loc.addCustomers(b);
        m.upgradeCustomer(loc,1234);
        assertTrue(b.getPremiumCustomer());
    }

    @Test
    public void testUpgradeCustomerDoesntWorks() {
        Customer b = new Customer(1234, "aaaa","bbbb");
        ListOfCustomer loc = new ListOfCustomer();
        loc.addCustomers(b);
        m.upgradeCustomer(loc,123);
        assertFalse(b.getPremiumCustomer());
    }
}
