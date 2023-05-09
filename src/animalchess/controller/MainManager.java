package animalchess.controller;
/**
 * This class shows the main menu of the game
 */

import animalchess.model.GameImages;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The controller for the main menu (Menu, Instructions, and Turn GUI/Controller)
 */

public class MainManager extends Application {

    public static void open() {
        launch();
    }

    /**
     * This initializes the display window showing the main menu
     *
     * @param stage the variable that will become a window to be initialized
     */
    @Override
    public void start(Stage stage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/MenuGUI.fxml"));
            Scene scene = new Scene(root, 600, 400);
            stage.getIcons().add(new Image(GameImages.ANIMAL_DEN));
            stage.setTitle("Animal Chess");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            stage.setOnCloseRequest(e -> Platform.exit());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
