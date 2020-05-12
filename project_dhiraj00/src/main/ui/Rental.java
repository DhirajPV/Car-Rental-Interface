package ui;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Rental extends Application implements Loadable, Saveable, RentObserver {

    Stage window;
    Scene scene1;
    Scene mainMenu;
    // The panes are associated with the respective .fxml files
    SplitPane pane1;
    SplitPane pane2;
    Rental main;

    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Rental.class.getResource("gui.fxml"));
        pane1 = loader.load();
        Scene1Controller controller1 = loader.getController();
        loader = new FXMLLoader();
        loader.setLocation(Rental.class.getResource("rentalMain.fxml"));
        pane2 = loader.load();
        Scene2Controller controller2 = loader.getController();
        Scene scene1 = new Scene(pane1);
        Scene scene2 = new Scene(pane2);
        mainMenu = scene2;
        controller1.setScene2(scene2);
        controller1.setMain(this);
        controller2.setMain(this);
        window.setScene(scene1);
        window.setTitle("ABC Car Rental");
        window.show();
    }

    public void starter() throws IOException {
        ListOfCar loc = loadCars("cars.json");
        ListOfManager lom = loadManagers();
        ListOfCustomer locust = loadCustomers();
        Location location = new Location();
        location.setup(loc);
        Map<String, ArrayList<Car>> carLocation = location.getLocation();
        int selection;
        do {
            selection = getSelection(loc, locust, carLocation,lom);
        } while (selection > 0 && selection <= 3);
        saveCars(loc);
        saveCustomers(locust);
    }

    private int getSelection(ListOfCar loc, ListOfCustomer locust, Map<String, ArrayList<Car>> carLocation,
                             ListOfManager lom) {
        int selection;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to ABC Car Rentals \n Please select one of the following options: ");
        System.out.println("1.Customer \n2.Manager \n3.Contact Us \n4.Exit");
        selection = scanner.nextInt();
        if (selection == 1) {
            customerMain(loc, locust, carLocation, scanner);
        } else if (selection == 2) {
            managerMain(loc,locust,carLocation,scanner, lom);
        } else if (selection == 3) {
            System.out.println("\nCall Us Today on : 614-666-7777 \nEmail Us : "
                    + "rentalsinfo@abccars.net\nOr else Drop by in person to any of our stores near you\n");
        }
        return selection;
    }

    private void managerMain(ListOfCar loc, ListOfCustomer locust, Map<String, ArrayList<Car>> carLocation,
                             Scanner scanner, ListOfManager lom) {
        Scanner login = new Scanner(System.in);
        System.out.println("Enter Manager Id");
        int id = login.nextInt();
        Manager m = new Manager(id);
        if (m.checkManager(id, lom)) {
            managerMenu(loc,locust,m);
        } else {
            System.out.println("Incorrect ManagerId");
        }
    }

    public void managerMenu(ListOfCar loc,ListOfCustomer locust, Manager m) {
        Scanner scanner = new Scanner(System.in);
        int selection = 0;
        do {
            printManagerMenu();
            selection = scanner.nextInt();
            if (selection == 1) {
                checkException(loc, scanner);
            } else if (selection == 2) {
                removeCar(loc,scanner);
            } else if (selection == 3) {
                upgradeCustomer(locust,scanner, m);
            } else if (selection == 4) {
                printAvailableCars(loc);
            } else if (selection == 5) {
                printAllCars(loc);
            }
        } while (selection > 0 && selection <= 3);
    }

    //EFFECTS: Prints out all the cars in the database, irrespective to whether they are rented or not
    public void printAllCars(ListOfCar loc) {
        System.out.println("The cars current in our cohort are ");
        int i = 1;
        for (Car c : loc.getCars()) {
            System.out.println(" " + i + ". " + c.getCompany() + " "
                    + c.getModel() + " " + c.getYear());
            i++;
        }
    }

    //EFFECTS: prints out all the cars which are available
    public void printAvailableCars(ListOfCar loc) {
        System.out.println("We currently have the following cars");
        int i = 1;
        for (Car c : loc.getCars()) {
            if (c.getAvailable()) {
                System.out.println(" " + i + ". " + c.getCompany() + " "
                        + c.getModel() + " " + c.getYear());
                i++;
            }
        }
        System.out.println("These are the only cars available at this moment");
    }


    private void checkException(ListOfCar loc, Scanner scanner) {
        try {
            addNewCar(scanner, loc);
        } catch (IllegalCarYearException e) {
            System.out.println("Enter correct car year");
        }
    }

    private void printManagerMenu() {
        System.out.println("\n \nWelcome to ABC Car rentals \nPlease select a category");
        System.out.println(" 1.Add Car \n 2.Remove Car \n Update Customer Status "
                + "\n 4.View Available Cars \n 5.View All Cars");
    }

    public void addNewCar(Scanner input,ListOfCar loc) throws IllegalCarYearException {
        int year;
        String company = "";
        String model = "";
        Boolean premiere;
        //Scanner input = new Scanner(System.in);
        System.out.println("Enter the company");
        company = input.nextLine();
        System.out.println("you selected: " + company);
        System.out.println("Enter the model");
        model = input.nextLine();
        System.out.println("you selected: " + model);
        System.out.println("Enter the year");
        year = input.nextInt();
        loc.validYear(year);
        System.out.println("you selected: " + year);
        System.out.println("Is it a premiere car only?");
        premiere = input.nextBoolean();
        Car c = new Car(year, model, company, premiere,"Downtown");
        loc.addCars(c);
    }

    public void removeCar(ListOfCar loc, Scanner scanner) {
        printAllCars(loc);
        System.out.println("Select model to remove");
        String mdl = "";
        mdl = scanner.nextLine();
        loc.removeCar(mdl);
    }

    public void upgradeCustomer(ListOfCustomer locust, Scanner scanner, Manager m) {
        printAllCustomer(locust);
        int id;
        System.out.println("Enter Id of customer to upgrade");
        id = scanner.nextInt();
        if (locust.correctId(id)) {
            m.upgradeCustomer(locust, id);
        } else {
            System.out.println("Enter a correct customer Id");
        }
    }

    private void printAllCustomer(ListOfCustomer locust) {
        int i = 1;
        for (Customer c : locust.getCustomers()) {
            System.out.println(i + ". " + c.getName() + " " + c.getCustomerId());
            i++;
        }
    }

    private void customerMain(ListOfCar loc, ListOfCustomer locust, Map<String, ArrayList<Car>> carLocation,
                              Scanner scanner) {
        int input;
        do {
            System.out.println("Please select one of the option \n 1. Sign In"
                    + "\n 2. New Customer \n 3. Show Available Cars \n 4. Back");
            input = scanner.nextInt();
            if (input == 1) {
                oldCustomer(loc, locust, carLocation);
            } else if (input == 2) {
                newCustomerSignUp(locust, scanner);
            } else if (input == 3) {
                printAvailableCars(loc);
            } else if (input == 4) {
                break;
            }
        } while (input <= 3);
    }

    private void newCustomerSignUp(ListOfCustomer locust, Scanner scanner) {
        do {
            System.out.println("Welcome to ABC car rentals \nTo create a new account"
                    + " we will need the following details \n Please select an Id number:");
            int select = scanner.nextInt();
            System.out.println(select);
            if (uniqueId(select,locust)) {
                newCustomerDetails(locust, select);
                break;
            }
        } while (true);
    }

    private void newCustomerDetails(ListOfCustomer locust, int select) {
        Scanner enter = new Scanner(System.in);
        System.out.println("Please enter your name");
        String name = enter.nextLine();
        System.out.println("Please enter a password");
        String pass = enter.nextLine();
        locust.addCustomers(new Customer(select,name,pass));
        System.out.println("You will be logged out and will require to use the given data to log in");
    }

    private void oldCustomer(ListOfCar loc, ListOfCustomer locust, Map<String, ArrayList<Car>> loca) {
        Customer c = new Customer();
        Scanner scanner = new Scanner(System.in);
        Scanner pass = new Scanner(System.in);
        System.out.println("Please enter your Customer Id");
        int id = scanner.nextInt();
        String enter = "";
        System.out.println("Please enter the password");
        enter = pass.nextLine();
        System.out.println(enter);
        if (c.signIn(id,enter,locust)) {
            Customer b = c.getCustomer(id,locust);
            signedInCustomerMenu(b,loc,loca,pass);
        } else {
            System.out.println("You have entered either an incorrect customer Id or password");
        }
    }

    private boolean uniqueId(int select, ListOfCustomer locust) {
        for (Customer c : locust.getCustomers()) {
            if (c.getCustomerId() == select) {
                System.out.println("This customer Id is already in use. "
                        + "Please select a new Id or else proceed to Login");
                return false;
            }
        }
        return true;
    }

    private void signedInCustomerMenu(Customer b, ListOfCar loc, Map<String,ArrayList<Car>> loca, Scanner scanner) {
        int choice;
        do {
            printCustomerMenu(b);
            choice = scanner.nextInt();
            if (choice == 1) {
                rentCar(b, loc,scanner,loca);
            } else if (choice == 2) {
                returnRental(b, loc);
            } else if (choice == 3) {
                printAvailableCars(loc);
            } else if (choice == 4) {
                printRented(b);
            } else if (choice == 5) {
                printCarsLocation(loca);
            } else if (choice == 6) {
                break;
            }
        } while (choice > 0 && choice <= 5);
    }

    private void printCustomerMenu(Customer b) {
        System.out.println("Welcome back " + b.getName() + ". What would like to do today? \n 1. Rent A Car \n 2. "
                + "Return Rented Car \n 3. View Available Cars \n 4. View Past rentals \n 5. View according to car"
                + " pick up location \n 6. Log Out");
    }

    private void returnRental(Customer b, ListOfCar loc) {
        if (b.getCurrentlyRented() == null) {
            System.out.println("You need to have rented a car to return it");
        } else {
            returnCar(b, loc);
        }
    }

    private void printCarsLocation(Map<String, ArrayList<Car>> carLocation) { //TODO
        carsAtDifferentLocations(carLocation,"Downtown");
        carsAtDifferentLocations(carLocation,"Richmond");
        carsAtDifferentLocations(carLocation,"Kerrih,sdale");
        carsAtDifferentLocations(carLocation,"Port Moody");
    }

    private void carsAtDifferentLocations(Map<String, ArrayList<Car>> carLocation, String city) {
        if (carLocation.get(city) != null) {
            System.out.println("The cars available" + city + "are ");
            int i = 1;
            for (Car c : carLocation.get(city)) {
                System.out.println(i + ". " + c.getCompany() + " " + c.getModel() + " " + c.getYear());
                i++;
            }
        }
    }

    private void printRented(Customer b) {
        if (b.getRentedBefore() == null) {
            System.out.println("You haven't rented any cars yet from us.");
        } else {
            System.out.println("Your previously rented cars are: ");
            int i = 1;
            for (Car a : b.getRentedBefore()) {
                System.out.println(i + ". " + a.getCompany() + " "
                        + a.getModel() + " " + a.getYear());
                i++;
            }
        }
    }

    private void returnCar(Customer b, ListOfCar loc) {
        Scanner con = new Scanner(System.in);
        System.out.println("Are you ready to return back the vehicle? true/false");
        Boolean confirm = con.nextBoolean();
        if (confirm) {
            updateCarAndCustomerReturn(b, loc);
        } else {
            System.out.println("Continue to enjoy your vehicle till "
                    + "you're ready to return it");
        }
    }

    private void updateCarAndCustomerReturn(Customer b, ListOfCar loc) { //TODO
        for (Car c : loc.getCars()) {
            if (c.getModel().equals(b.getCurrentlyRented().getModel())) {
                c.setReturn();
            }
        }
        Car x = b.getCurrentlyRented();
        b.updateReturn();
        System.out.println("Successfully returned " + x.getCompany() + " " + x.getModel() + " " + x.getYear());
    }

    private void rentCar(Customer b, ListOfCar loc, Scanner word, Map<String, ArrayList<Car>> loca) {
        Scanner scan = new Scanner(System.in);
        printAvailableCars(loc);
        System.out.println("Please enter the model of the car you wish to rent");
        String mdl = scan.nextLine();
        try {
            b.ableToRent(mdl,loc);
        } catch (IOException e) {
            System.out.println("Something happened");
        }
        for (Car c : loc.getCars()) {
            if (c.getModel().equals(mdl) && !c.getAvailable()) {
                ArrayList<Car> x = loca.get(c.getLocation());
                x.remove(c);
            }
        }
    }

    @Override
    public ListOfManager loadManagers() throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader("managers.json"));
        Manager[] managers = gson.fromJson(reader, Manager[].class);
        ListOfManager lom = new ListOfManager();
        for (Manager m : managers) {
            lom.addManagers(m);
        }
        return lom;
    }

    @Override
    public ListOfCar loadCars(String fileName) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(fileName));
        Car[] cars = gson.fromJson(reader, Car[].class);
        ListOfCar loc = new ListOfCar();
        for (Car c : cars) {
            loc.addCars(c);
        }
        return loc;
    }

    @Override
    public ListOfCustomer loadCustomers() throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader("customers.json"));
        Customer[] customers = gson.fromJson(reader, Customer[].class);
        ListOfCustomer locust = new ListOfCustomer();
        for (Customer c : customers) {
            locust.addCustomers(c);
        }
        return locust;
    }

    @Override
    public void saveCars(ListOfCar loc) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(loc.getCars());
        FileWriter writer = new FileWriter("cars.json");
        writer.write(json);
        writer.close();
    }

    @Override
    public void saveCustomers(ListOfCustomer loc) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(loc.getCustomers());
        FileWriter writer = new FileWriter("customers.json");
        writer.write(json);
        writer.close();
    }

    public void setScene(Scene scene2) {
        window.setScene(scene2);
    }

    public void close() {
        window.close();
    }

    public void setCustScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Rental.class.getResource("customerMenu.fxml"));
        pane1 = loader.load();
        CustomerController controllerCustomer = loader.getController();

        Scene cust = new Scene(pane1);
        scene1 = cust;

        controllerCustomer.setCust(cust);
        controllerCustomer.setPrevScene(mainMenu);
        controllerCustomer.setMain(this);

        window.setScene(cust);
    }

    public void setManagScene() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Rental.class.getResource("managerMenu.fxml"));
        pane1 = loader.load();
        ManagerController mc = loader.getController();

        Scene manag = new Scene(pane1);

        window.setScene(manag);
    }

    public void setContact() throws IOException {
        AnchorPane pane;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Rental.class.getResource("ContactDetails.fxml"));
        pane = loader.load();
        Scene cust = new Scene(pane);

        window.setScene(cust);
    }

    public void customerLogIn() throws IOException {
        SplitPane pane;
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Rental.class.getResource("customerLogIn.fxml"));
        pane = loader.load();
        LoginController lc = new LoginController();

        lc.setMain(this);

        Scene login = new Scene(pane);

        window.setScene(login);
    }

    public void checkId(int id, String pass) throws FileNotFoundException {
        ListOfCar loc = loadCars("cars.json");
        Scanner scanner = new Scanner(System.in);
        ListOfCustomer locust = loadCustomers();
        Location location = new Location();
        location.setup(loc);
        Map<String, ArrayList<Car>> carLocation = location.getLocation();
        Customer c = new Customer();
        if (c.signIn(id,pass,locust)) {
            Customer b = c.getCustomer(id,locust);
            signedInCustomerMenu(b,loc,carLocation,scanner);
        } else {
            System.out.println("You have entered either an incorrect customer Id or password");
        }
    }

    public Scene getMainMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Rental.class.getResource("rentalMain.fxml"));
        pane2 = loader.load();
        Scene2Controller controller2 = loader.getController();

        Scene scene2 = new Scene(pane2);
        mainMenu = scene2;
        controller2.setMain(main);

        return mainMenu;
    }

    @Override
    public void update(Car car) throws IOException {
        if (car.getModel().equals("XJ")) {
            url("https://upload.wikimedia.org/wikipedia/commons/4/41/Jaguar_XJ_L%2C_Bangladesh._%2840852631065%29.jpg");
        } else if (car.getModel().equals("Coupe")) {
            url("https://img.newatlas.com/2019-bmw-8-series-coupe-9.jpg?auto=format%2Ccompress&ch=Width%2CDPR&fit=crop&h=347&q=60&rect=0%2C33%2C1619%2C911&w=616&s=4c13ec41ee8e4609c722f29f9a12ecb6");
        } else if (car.getModel().equals("Evoke Sport")) {
            url("https://vehicle-photos-published.vauto.com/85/50/12/fb-2b2f-480f-ad13-b14d38209c7b/image-1.jpg");
        } else if (car.getModel().equals("A5")) {
            url("https://www.audi.ca/content/dam/nemo/ca/Models/MYCO_2017/A5-Sportback/1920x1080-Gallery/1920x1080_A169167_large.jpg");
        } else if (car.getModel().equals("Corolla")) {
            url("https://st.motortrend.ca/uploads/sites/10/2016/02/2016-Toyota-Corolla-front-three-quarter.jpg?interpolation=lanczos-none&fit=around%7C392%3A261");
        } else if (car.getModel().equals("GTR")) {
            url("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT4YQApTl7YoJsmMlRxrmNbXJkmnyrzCawfOtfqos65fx8qDoOkbw");
        } else if (car.getModel().equals("CRV")) {
            url("https://upload.wikimedia.org/wikipedia/commons/b/bf/2012_Honda_CR-V_EX_--_07-11-2012_2.JPG");
        }
    }

    public void url(String str) throws IOException {
        final URL lenna =
                new URL(str);

        final ImageComponent image = new ImageComponent(lenna);
        image.main(image);
    }

    //    @Override
//    public void start(Stage primaryStage) throws Exception {
//        primaryStage.setTitle("Car Rental");
//    }


}
