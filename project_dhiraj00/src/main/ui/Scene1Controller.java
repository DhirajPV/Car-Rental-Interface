package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;

public class Scene1Controller {
    private Scene scene2;
    private Rental main;

    public void setMain(Rental main) {
        this.main = main;
    }

    public void setScene2(Scene scene2) {
        this.scene2 = scene2;
    }

    @FXML
    public void nextScene() {
        main.setScene(scene2);
    }

    public void byeBye(ActionEvent actionEvent) {
        main.close();
    }
}
