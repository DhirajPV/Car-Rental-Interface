package model;

import java.util.ArrayList;

public class ListOfManager {
    private ArrayList<Manager> managers = new ArrayList<>();

    public void addManagers(Manager m) {
        managers.add(m);
    }

    public ArrayList<Manager> getManagers() {
        return managers;
    }
}

