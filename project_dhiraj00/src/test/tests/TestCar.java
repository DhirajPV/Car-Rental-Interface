package tests;

import model.Car;
import model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestCar {
    Car c;
    Customer cust;
    Car b;

    @BeforeEach
    public void setup() {
        c= new Car(1234,"aaaa", "bbbb",false,"dddd");
        cust = new Customer(1111,"name","password");
        b = new Car(1234,"aaaa", "bbbb",false,"dddd");
    }

    @Test
    public void testCarConstructor() {
         assertTrue(b.getAvailable());
         assertFalse(b.getPremium());
         assertEquals(b.getYear(),1234);
         assertTrue(b.getCompany().equals("bbbb"));
         assertTrue(b.getModel().equals("aaaa"));
         assertTrue(b.getLocation().equals("dddd"));
    }

//    @Test
//    public void testAbleToRentCanRent() {
//        assertTrue(b.ableToRent(cust,c));
//    }
//
//    @Test
//    public void testAbleToRentNotAvailable() {
//        c.setAvailable(false);
//        assertFalse(b.ableToRent(cust,c));
//    }

    @Test
    public void testSetRental() {
        c.setRental();
        assertFalse(c.getAvailable());
    }

    @Test
    public void testSetReturn() {
        c.setReturn();
        assertTrue(c.getAvailable());
        if(c.getRentedBy() != null) {
            fail("Should Be Null");
        }
    }

    @Test
    public void testSetCompany() {
        c.setCompany("Comp");
        assertTrue(c.getCompany().equals("Comp"));
    }

    @Test
    public void testSetModel() {
        c.setModel("mdl");
        assertTrue(c.getModel().equals("mdl"));
    }

    @Test
    public void testSetYear() {
        c.setYear(1998);
        assertTrue(c.getYear() == 1998);
    }

    @Test
    public void testSetRentedBy() {
        c.setRentedBy(cust);
        assertTrue(c.getRentedBy().equals(cust));
    }

//    @Test
//    public void testupdate() throws IOException {
//        Gson gson = new Gson();
//        JsonReader reader = null;
//        try {
//            reader = new JsonReader(new FileReader("cars.json"));
//            Car[] cars = gson.fromJson(reader, Car[].class);
//            ListOfCar loc = new ListOfCar();
//            for (Car c : cars) {
//                loc.addCars(c);
//            }
//            for (Car c : loc.getCars()) {
//                c.update(c);
//            }
//        } catch (FileNotFoundException e) {
//            fail();
//        }
//    }
}

