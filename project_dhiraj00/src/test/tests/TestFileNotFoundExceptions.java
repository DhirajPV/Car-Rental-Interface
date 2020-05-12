package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Rental;

import java.io.FileNotFoundException;

import static org.junit.jupiter.api.Assertions.fail;

public class TestFileNotFoundExceptions {
    Rental r;

    @BeforeEach
    public void setup() {
        r = new Rental();
    }

    @Test
    public void testLoadCars() {
        try {
            r.loadCars("cars.json");
        } catch (FileNotFoundException e) {
            fail("No exception must be thrown");
        }
    }

    @Test
    public void testLoadCarsWithException() {
        try {
            r.loadCars("car.json");
            fail("Should have thrown exception");
        } catch (FileNotFoundException e) {
            System.out.println("pass");
        }
    }
}
