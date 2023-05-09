package animalchess.controller;
/**
 * This class is to perform the actions performed in the main menu
 */

import animalchess.model.GameImages;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class MenuController {

    @FXML
    private ImageView bg;

    @FXML
    private Button Play;

    @FXML
    private Button Exit;

    /**
     * Function that closes the game when exit button is pressed
     *
     * @param event variable that represents the action done to the exit button
     */
    @FXML
    void closeGame(ActionEvent event) {
        System.out.println("bye!");
        // get a handle to the stage
        Stage stage = (Stage) Exit.getScene().getWindow();
        stage.close();
    }

    /**
     * This function starts the game when play button is pressed
     *
     * @param event a variable that represents teh action done to the play button
     */
    @FXML
    void startGame(ActionEvent event) {
        try {
            //https://stackoverflow.com/a/25217393
            Parent root = FXMLLoader.load(getClass().getResource("/fxml/InstructionsGUI.fxml"));
            Stage window = (Stage) Play.getScene().getWindow();
            window.getIcons().add(new Image(GameImages.ANIMAL_DEN));
            window.setScene(new Scene(root, 600, 400));
            window.setOnCloseRequest(e -> Platform.exit());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }


}
