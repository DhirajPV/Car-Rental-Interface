package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Location {
    Map<String,ArrayList<Car>> location = new HashMap<>();

    public void setup(ListOfCar loc) {
        for (Car c : loc.getCars()) {
            if (c.getAvailable()) {
                if (location.containsKey(c.getLocation())) {
                    if (!location.get(c.getLocation()).contains(c)) {
                        ArrayList<Car> cars = location.get(c.getLocation());
                        cars.add(c);
                        location.put(c.getLocation(), cars);
                    }
                } else {
                    ArrayList<Car> newarray = new ArrayList<>();
                    newarray.add(c);
                    location.put(c.getLocation(),newarray);
                }
            }
        }
    }

    public Map<String, ArrayList<Car>> getLocation() {
        return location;
    }
}