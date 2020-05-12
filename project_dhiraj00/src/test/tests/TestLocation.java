package tests;

import model.Car;
import model.ListOfCar;
import model.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class TestLocation {
    Location loca;
    Map<String, ArrayList<Car>> location;
    ListOfCar loc;
    Car c;

    @BeforeEach
    public void setup() {
        loc = new ListOfCar();
        c = new Car(2019,"aaaa","bbbb",true,"Downtown");
        loc.addCars(c);
        loca = new Location();
        location = new HashMap<>();
        loca.setup(loc);
    }

    @Test
    public void testCheckEmpty() {
        assertFalse(location.containsKey("kids"));
    }


    @Test
    public void testSizeIsZero() {
        assertTrue(location.size() == 0);
    }

    @Test
    public void testHashMap() {
        location.put("Downtown",loc.getCars());
        assertTrue(location.get("Downtown").equals(loc.getCars()));
    }

    @Test
    public void testSetUpOneLocationCorrectArrayList() {
        assertTrue(loca.getLocation().get("Downtown").equals(loc.getCars()));
    }

    @Test
    public void testReturnOfIncorrectArrayList() {
        assertFalse(loca.getLocation().get("Downtown").equals(null));
    }

    @Test
    public void testAddOneLocationSetUpCorrectLocationAnsSize() {
        assertTrue(loca.getLocation().containsKey("Downtown"));
        assertTrue(loca.getLocation().size() == 1);
    }

    @Test
    public void testOneLocationDoesntContainDifferentLocation() {
        assertFalse(loca.getLocation().containsKey("Wrong"));
    }

    @Test
    public void testSetUpTwoLocations() {
        Car b = new Car(2016,"juvsh","jbdv",false,"UBC");
        loc.addCars(b);
        loca.setup(loc);
        assertTrue(loca.getLocation().size() == 2);
        ArrayList<Car> test =new ArrayList<>();
        test.add(c);
        assertTrue(loca.getLocation().containsKey("Downtown"));
        assertTrue(loca.getLocation().containsKey("UBC"));
        System.out.println(loca.getLocation().get("Downtown"));
    }

    @Test
    public void testSetUpWithMultipleSameLists() {
        loca.setup(loc);
        assertTrue(loca.getLocation().get("Downtown").size() == 1);
    }

    @Test
    public void testSetupTwoCarsOneNotAvailable() {
        Car b = new Car(2016,"juvsh","jbdv",false,"Downtown");
        b.setAvailable(false);
        loc.addCars(b);
        loca.setup(loc);
        assertTrue(loca.getLocation().size() == 1);
    }

    @Test
    public void testSetupTwoCarsSameLocation() {
        Car b = new Car(2016,"juvsh","jbdv",false,"Downtown");
        loc.addCars(b);
        loca.setup(loc);
        assertTrue(loca.getLocation().size() == 1);
    }
}
