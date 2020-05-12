package model;

import ui.Subject;

import java.io.IOException;
import java.util.ArrayList;

public class Customer extends Subject {
    private String name;
    private int customerId;
    private String password;
    private Boolean premiumCustomer;
    private ArrayList<Car> rentedBefore;
    private Car currentlyRented;

    public Customer() {
        premiumCustomer = false;
        rentedBefore = null;
        currentlyRented = null;
    }

    public Customer(int id, String name, String password) {
        customerId = id;
        this.name = name;
        this.password = password;
        premiumCustomer = false;
        rentedBefore = null;
        currentlyRented = null;
    }

    // REQUIRES: custId and password
    // EFFECTS : Check whether the entered customer details exist in the database of the customers file.
    public boolean signIn(int custId, String password, ListOfCustomer loc) {
        for (Customer c : loc.getCustomers()) {
            if (c.getCustomerId() == custId) {
                if (c.getPassword().equals(password)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }

    public void ableToRent(String model, ListOfCar loc) throws IOException {
        for (Car a : loc.getCars()) {
            if (a.getModel().equals(model)) {
                if (a.getPremium()) {
                    if (premiumCustomer) {
                        //addObserver();
                        rent(a);
                        break;
                    } else {
                        printInvalidCustomer();
                        break;
                    }
                } else {
                    //addObserver(a);
                    rent(a);
                    break;
                }
            }
        }
    }

    private void printInvalidCustomer() {
        System.out.println("Sorry, this is a premium car please upgrade to a premium customer "
                + "Until then please opt for some of the other cars available");
    }

    // REQUIRES : Customer who has already signed in
    // EFFECTS :
    public void rent(Car a) throws IOException {
        a.setRentedBy(this);
        a.setRental();
        currentlyRented = a;
        //notifyObservers(a);
        System.out.println("Successfully rented");
        //   System.out.println("You can pick up the car from "); //+ a.getLocation());
        //ArrayList<Car> cars = lh.getLocationHash().get(a.getLocation());
        //cars.remove(a);
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        customer customer = (customer) o;
//        return CustomerId == customer.CustomerId &&
//                Objects.equals(Name, customer.Name);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(Name, CustomerId);
//    }

    public Customer getCustomer(int id, ListOfCustomer locust) {
        for (Customer c : locust.getCustomers()) {
            if (c.getCustomerId() == id) {
                Customer b = new Customer();
                b.setCurrentlyRented(c.getCurrentlyRented());
                b.setCustomerId(c.getCustomerId());
                b.setName(c.getName());
                b.setPassword(c.getPassword());
                b.setPremiumCust(c.getPremiumCustomer());
                b.setRented(c.getRentedBefore());
                return b;
            }
        }
        return null;
    }

    public void updateReturn() {
        rentedBefore.add(currentlyRented);
        currentlyRented = null;
    }

    public Boolean getPremiumCustomer() {
        return premiumCustomer;
    }

    public String getName() {
        return name;
    }

    public int getCustomerId() {
        return customerId;
    }

    public ArrayList<Car> getRentedBefore() {
        return rentedBefore;
    }

    public String getPassword() {
        return password;
    }

    public Car getCurrentlyRented() {
        return currentlyRented;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrentlyRented(Car currentlyRented) {
        this.currentlyRented = currentlyRented;
    }

    public void setPremiumCust(Boolean premiumCust) {
        this.premiumCustomer = premiumCust;
    }

    public void setRented(ArrayList<Car> rented) {
        rentedBefore = rented;
    }
}