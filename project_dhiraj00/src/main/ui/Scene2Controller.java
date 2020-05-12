package ui;

import javafx.scene.control.SplitPane;

import java.io.IOException;

public class Scene2Controller {
    private Rental main;
    SplitPane pane;

    public void setMain(Rental main) {
        this.main = main;
    }

    public void sceneCust() throws IOException {
        main.setCustScene();
    }

    public void sceneManag() throws IOException {
        main.setManagScene();
    }

    public void sceneContact() throws IOException {
        main.setContact();
    }
    // this method is called by clicking the button
}
