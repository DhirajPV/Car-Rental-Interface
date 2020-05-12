package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class CustomerController {
    Rental main;
    Scene prevScene;
    AnchorPane pane;
    Scene cust;

    public void setPrevScene(Scene prevScene) {
        this.prevScene = prevScene;
    }

    public void setMain(Rental main) {
        this.main = main;
    }

    public void setCust(Scene cust) {
        this.cust = cust;
    }

    public void customerSignIn(ActionEvent actionEvent) throws IOException {
        main.customerLogIn();
    }

    public void newCustomer(ActionEvent actionEvent) {

    }

    public void viewAll(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Rental.class.getResource("viewAll.fxml"));
        pane = loader.load();
        Scene1Controller sc = loader.getController();

        sc.setScene2(cust);
        sc.setMain(main);

        Scene viewAll = new Scene(pane);

        main.setScene(viewAll);
    }

    public void goBack(ActionEvent actionEvent) {
        main.setScene(prevScene);
    }
}
