package model;

import java.util.ArrayList;

public class ListOfCar {
    private ArrayList<Car> cars = new ArrayList<>();

    public void validYear(int year) throws IllegalCarYearException {
        if (year < 1000) {
            throw new IllegalCarYearException();
        }
    }

    public void addCars(Car c) {
        cars.add(c);
    }

    public void removeCar(String mdl) {
        ArrayList<Car> newCars = new ArrayList<>();
        for (Car c : getCars()) {
            if (!c.getModel().equals(mdl)) {
                newCars.add(c);
            }
        }
        setCars(newCars);
    }

    public ArrayList<Car> getCars() {
        return cars;
    }

    public void setCars(ArrayList<Car> cars) {
        this.cars = cars;
    }

}
