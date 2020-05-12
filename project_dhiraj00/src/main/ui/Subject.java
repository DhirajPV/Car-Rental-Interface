package ui;

import model.Car;

import java.io.IOException;

public class Subject {
    private RentObserver observers = new RentObserver() {
        @Override
        public void update(Car car) throws IOException {
        }
    };

    public void addObserver(RentObserver rentObserver) {
        if (!observers.equals(rentObserver)) {
            observers = rentObserver;
        }
    }

    public void notifyObservers(Car car) throws IOException {
        observers.update(car);
        observers = null;
    }
}
