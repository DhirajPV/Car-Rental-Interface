package model;

import java.util.ArrayList;

public class ListOfCustomer {
    private ArrayList<Customer> customers = new ArrayList<>();

    public void addCustomers(Customer c) {
        customers.add(c);
    }

    public boolean correctId(int id) {
        for (Customer c : customers) {
            if (c.getCustomerId() == id) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Customer> getCustomers() {
        return customers;
    }
}
