package model;

import java.io.FileNotFoundException;

public interface Loadable {
    public ListOfManager loadManagers() throws FileNotFoundException;

    public ListOfCar loadCars(String fileName) throws FileNotFoundException;

    public ListOfCustomer loadCustomers() throws FileNotFoundException;
}
