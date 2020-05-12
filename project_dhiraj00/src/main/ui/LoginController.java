package ui;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.FileNotFoundException;

public class LoginController {
    Rental main;

    public void setMain(Rental main) {
        this.main = main;
    }


    @FXML
    private PasswordField password;

    @FXML
    private TextField customer;

//    public void getId(java.awt.event.ActionEvent event) {
//        System.out.println(customer.getText());
//    }
//
//    @FXML
//    public void getPass(ActionEvent event) {
//        System.out.println(password.getText());
//    }
//
//    @FXML
//    public void checkId(ActionEvent event) throws FileNotFoundException {
//        int id = Integer.parseInt(customer.getText());
//        String pass = password.getText();
//        main.checkId(id, pass);
//
//    }
//
//    @FXML
//    public void goBack(ActionEvent event) {
//
//    }

    public void getId(javafx.event.ActionEvent actionEvent) {
        System.out.println(customer.getText());
    }

    public void getPass(javafx.event.ActionEvent actionEvent) {
        System.out.println(password.getText());
    }


    public void checkId(javafx.event.ActionEvent actionEvent) throws FileNotFoundException {
        int id = Integer.parseInt(customer.getText());
        String pass = password.getText();
        System.out.println(id + pass);
        Rental r = new Rental();
        r.checkId(id,pass);
    }


    public void goBack(javafx.event.ActionEvent actionEvent) {

    }
}
//
//public class LoginController {
//    Rental main;
//    Scene cust;
//
//    public void setMain(Rental main) {
//        this.main = main;
//    }
//
//    public void setCust(Scene cust) {
//        this.cust = cust;
//    }

//    @FXML
//    private TextField customerId;
//
//    @FXML
//    private PasswordField password;
//
//    @FXML
//    public void checkId(ActionEvent actionEvent) throws FileNotFoundException {
//        String Id = custId.getText();
//        int id = Integer.parseInt(Id);
//        String pass = password.getText();
//        main.checkId(id, pass);
//    }
//
//    public void goBack(ActionEvent actionEvent) throws IOException {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(Rental.class.getResource("customerMenu.fxml"));
//        SplitPane pane1 = loader.load();
//        CustomerController controllerCustomer = loader.getController();
//
//        Scene customer = new Scene(pane1);
//
//        controllerCustomer.setCust(customer);
//        controllerCustomer.setMain(main);
//
//        main.setScene(customer);
//    }
//
//    public void getId(ActionEvent actionEvent) {
//        String t = custId.getText();
//        custId.setText(t);
//    }
//
//    public void getPass(ActionEvent actionEvent) {
//        String p = password.getText();
//        password.setText(p);
//    }

