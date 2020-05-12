package model;

public class Manager {
    private int managerId;
    private String password;

    public Manager(int id) {
        managerId = id;
    }

    public Manager(int id, String password) {
        managerId = id;
        this.password = password;
    }

    public Boolean checkManager(int id, ListOfManager lom) {
        for (Manager m : lom.getManagers()) {
            if (m.getManagerId() == id) {
                return true;
            }
        }
        return false;
    }

    public void upgradeCustomer(ListOfCustomer locust,int id) {
        for (Customer c : locust.getCustomers()) {
            if (c.getCustomerId() == id) {
                c.setPremiumCust(true);
            }
        }
    }

    public String getPassword() {
        return password;
    }

    public int getManagerId() {
        return managerId;
    }
}
