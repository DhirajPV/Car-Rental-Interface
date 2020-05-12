package model;

import java.io.IOException;

public interface Saveable {
    public void saveCars(ListOfCar loc) throws IOException;

    public void saveCustomers(ListOfCustomer loc) throws IOException;
}
