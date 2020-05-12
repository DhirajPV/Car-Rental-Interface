package ui;

import model.Car;

import java.io.IOException;

public interface RentObserver {
    void update(Car car) throws IOException;
}
