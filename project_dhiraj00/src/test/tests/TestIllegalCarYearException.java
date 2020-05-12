package tests;

import model.IllegalCarYearException;
import model.ListOfCar;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class TestIllegalCarYearException {

    ListOfCar loc = new ListOfCar();

    @Test
    public void testValidYearExceptionThrown() {
        try {
            loc.validYear(900);
            fail();
        } catch (IllegalCarYearException e) {
            System.out.println("pass");        }
    }

    @Test
    public void testvalidYearExceptionEdge(){
        try {
            loc.validYear(1000);
        } catch (IllegalCarYearException e) {
            fail("Shouldn't throw exception");
        }
    }

    @Test
    public void testvalidYearExceptionLowerBoundry(){
        try {
            loc.validYear(999);
            fail("Should throw exception");
        } catch (IllegalCarYearException e) {
            System.out.println("pass");
        }
    }

    @Test
    public void testvalidYearExceptionUpperBoundary(){
        try {
            loc.validYear(1001);
        } catch (IllegalCarYearException e) {
            fail("Shouldn't throw exception");
        }
    }

    @Test
    public void testvalidYearExceptionNotThrown(){
        try {
            loc.validYear(2019);
        } catch (IllegalCarYearException e) {
            fail("Shouldn't throw exception");
        }
    }
}
