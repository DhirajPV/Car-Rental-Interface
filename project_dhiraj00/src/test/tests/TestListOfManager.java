package tests;

import model.ListOfManager;
import model.Manager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestListOfManager {
    Manager m;
    ListOfManager lom;

    @BeforeEach
    public void setup() {
        m =new Manager(123456);
        lom = new ListOfManager();
    }

    @Test
    public void testAddManager() {
        assertTrue(lom.getManagers().size() == 0);
        lom.addManagers(m);
        assertTrue(lom.getManagers().size() == 1);
    }
}
