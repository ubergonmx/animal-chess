package animalchess.controller;
/**
 * This class is used to display teh instructions before the game starts
 */

import animalchess.model.GameImages;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

public class InstructionsController {

    @FXML
    private Button okButton;

    /**
     * This function switches to the TurnGUI window
     */
    @FXML
    void nextScene() {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/TurnGUI.fxml")));
            Stage window = (Stage) okButton.getScene().getWindow();
            window.getIcons().add(new Image(GameImages.ANIMAL_DEN));
            window.setScene(new Scene(root, 600, 400));
            window.setOnCloseRequest(e -> Platform.exit());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}

