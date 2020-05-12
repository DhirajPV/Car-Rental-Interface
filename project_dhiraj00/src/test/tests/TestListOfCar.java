package tests;

import model.Car;
import model.ListOfCar;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestListOfCar {
    Car c;
    ListOfCar loc;

    @BeforeEach
    public void setup() {
        c = new Car(2000,"aaaa","bbbb",false,"cccc");
        loc = new ListOfCar();
    }

    @Test
    public void testAddCars() {
        assertTrue(loc.getCars().size() == 0);
    }

    @Test
    public void testAddOneCar() {
        loc.addCars(c);
        assertTrue(loc.getCars().size() == 1);
    }

    @Test
    public void testRemoveCars() {
        loc.addCars(c);
        loc.removeCar("aaaa");
        assertTrue(loc.getCars().size() == 0);
    }

    @Test
    public void testRemoveCarsIncorrect() {
        loc.addCars(c);
        loc.removeCar("bbbb");
        assertTrue(loc.getCars().size() == 1);
    }

    @Test
    public void testRemoveCarsInTwoCars() {
        Car b =new Car(1256,"ssss","cccc",false,"tttt");
        loc.addCars(c);
        loc.addCars(b);
        loc.removeCar("aaaa");
        assertTrue(loc.getCars().size() == 1);
    }

    @Test
    public void testRemoveCarsInTwoIncorrect() {
        Car b =new Car(1256,"ssss","cccc",false,"tttt");
        loc.addCars(c);
        loc.addCars(b);
        loc.removeCar("vvvv");
        assertTrue(loc.getCars().size() == 2);
    }
}
