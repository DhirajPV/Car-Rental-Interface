package model;

public class Car {
    protected int year;
    protected String model;
    protected String company;
    protected Boolean available;
    protected Boolean premium;
    protected Customer rentedBy;
    protected String location;


    //REQUIRES: The year, model and the company provided
    //MODIFIES: this
    //EFFECTS: Sets the details for a new Car
    public Car(int year, String model, String company, Boolean premium, String loca) {
        this.year = year;
        this.model = model;
        this.company = company;
        available = true;
        this.premium = premium;
        rentedBy = null;
        location = loca;
    }

//    @Override
//    public Boolean ableToRent(Customer c, CarAbstract car) {
//        if (car.getAvailable()) {
//            return true;
//        } else {
//            return false;
//        }
//    }

    public void setReturn() {
        available = true;
        rentedBy = null;
    }

    public Customer getRentedBy() {
        return rentedBy;
    }

    public void setRental() {
        available = false;
    }

    public void setRentedBy(Customer c) {
        this.rentedBy = c;
    }
    // public String getLocation() {
   //     return location;
   // }


    public int getYear() {
        return year;
    }

    public Boolean getPremium() {
        return premium;
    }

    public String getCompany() {
        return company;
    }

    public String getModel() {
        return model;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPremium(Boolean premium) {
        this.premium = premium;
    }

    public String getLocation() {
        return location;
    }
}