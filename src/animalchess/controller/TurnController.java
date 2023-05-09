package animalchess.controller;
/**
 * This class allows the players to pick a random animal to determine who takes their turn first
 */

import animalchess.model.GameImages;
import animalchess.model.PlayerType;
import javafx.animation.PathTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.lang.reflect.Field;
import java.util.*;

public class TurnController {

    @FXML
    private Button animal1;

    @FXML
    private Button animal2;

    @FXML
    private Button animal3;

    @FXML
    private Button animal4;

    @FXML
    private Button animal5;

    @FXML
    private Button animal6;

    @FXML
    private Button animal7;

    @FXML
    private Button animal8;

    @FXML
    private Text turnText;

    @FXML
    private Text turnFinalText;

    @FXML
    private ImageView player1;

    @FXML
    private ImageView player2;

    private int[] rankHolder;
    private int Player1Rank;
    private int Player2Rank;
    private int turnStep;

    /**
     * Function that checks the turn of each player
     *
     * @param i the rank each player gets
     */
    private void turnChecker(int i) {
        try {
            switch (turnStep) {
                case 0:
                    Player1Rank = i;
                    turnText.setText("Player 2");
                    player1.setImage(new Image(getPieceImage(i)));
                    centerImage(player1);
                    turnStep++;

                    shuffle();
                    break;
                case 1:
                    turnText.setText("Game starting...");
                    Player2Rank = i;
                    player2.setImage(new Image(getPieceImage(i)));
                    centerImage(player2);
                    turnStep++;
                    break;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        if (Player1Rank != 0 && Player2Rank != 0)
            proceedToGame();

    }

    @FXML
    void selectAnimal1() {

        turnChecker(rankHolder[0]);
        animal1.setDisable(true);
    }

    @FXML
    void selectAnimal2() {
        turnChecker(rankHolder[1]);
        animal2.setDisable(true);
    }

    @FXML
    void selectAnimal3() {
        turnChecker(rankHolder[2]);
        animal3.setDisable(true);
    }

    @FXML
    void selectAnimal4() {
        turnChecker(rankHolder[3]);
        animal4.setDisable(true);
    }

    @FXML
    void selectAnimal5() {
        turnChecker(rankHolder[4]);
        animal5.setDisable(true);
    }

    @FXML
    void selectAnimal6() {
        turnChecker(rankHolder[5]);
        animal6.setDisable(true);
    }

    @FXML
    void selectAnimal7() {
        turnChecker(rankHolder[6]);
        animal7.setDisable(true);
    }

    @FXML
    void selectAnimal8() {
        turnChecker(rankHolder[7]);
        animal8.setDisable(true);
    }

    /**
     * This initializes the value of each button
     */
    public void initialize() {
        Random ran = new Random();
        rankHolder = new int[8];
        turnStep = 0;

        for (int i = 0; i < rankHolder.length; i++) {
            rankHolder[i] = ran.nextInt(8) + 1;
            for (int j = 0; j < i; j++) {
                if (rankHolder[i] == rankHolder[j])
                    i--;
            }
        }

    }

    private int[] getButton(Button[][] button, Button find){
        for(int i=0; i<button.length; i++){
            for(int j=0; j<button[0].length; j++){
                if(button[i][j] == find){
                    return new int[]{i,j}; // row (y) , column (x)
                }
            }
        }
        return new int[]{-1,-1};
    }

    /**
     * This shuffles the buttons on display
     */
    public void shuffle() {
        int baseX = 35;
        int baseY = 15;
        MoveTo base = new MoveTo(baseX, baseY);

        int toUpDown = 44;
        double toLeftRight = 83.5;

        ArrayList<Button> buttons = new ArrayList<>(Arrays.asList(animal1, animal2, animal3, animal4, animal5, animal6, animal7, animal8));
        Button[][] pos = {{animal1, animal2, animal3, animal4}, {animal5, animal6, animal7, animal8}};
        Button[][] taken = new Button[2][4];
        Path[][] buttonPath = new Path[2][4];
        //randomly select a unique pair of buttons and set a PathTransition for them
        for(int i = 0; i < buttons.size(); i++){
            var currButton = buttons.get(i);
            int[] currButtonPos = getButton(pos, currButton);
            int j = currButtonPos[1];
            int k = currButtonPos[0];
            taken[k][j] = buttons.get(i);
            var button = buttons.get(new Random().nextInt(buttons.size()));
            int[] buttonPos = getButton(pos, button);
            int x = buttonPos[1];
            int y = buttonPos[0];
            if (taken[y][x] != null) {
                i--;
                continue;
            }

            taken[y][x] = button;
            int distanceX = Math.abs(j-x);
            int distanceY = Math.abs(k-y);
            buttonPath[k][j] = new Path();
            buttonPath[k][j].getElements().add(base);
            buttonPath[y][x] = new Path();
            buttonPath[y][x].getElements().add(base);

            if(j<x){
                if(k<y){
                    buttonPath[k][j].getElements().add(new LineTo(baseX + toLeftRight*distanceX, baseY + toUpDown*distanceY));
                    buttonPath[y][x].getElements().add(new LineTo(baseX - toLeftRight*distanceX, baseY - toUpDown*distanceY));
                }
                else {
                    buttonPath[k][j].getElements().add(new LineTo(baseX + toLeftRight*distanceX, baseY - toUpDown*distanceY));
                    buttonPath[y][x].getElements().add(new LineTo(baseX - toLeftRight*distanceX, baseY + toUpDown*distanceY));
                }
            }
            else{
                if(k<y){
                    buttonPath[k][j].getElements().add(new LineTo(baseX - toLeftRight*distanceX, baseY + toUpDown*distanceY));
                    buttonPath[y][x].getElements().add(new LineTo(baseX + toLeftRight*distanceX, baseY - toUpDown*distanceY));
                }
                else {
                    buttonPath[k][j].getElements().add(new LineTo(baseX - toLeftRight*distanceX, baseY - toUpDown*distanceY));
                    buttonPath[y][x].getElements().add(new LineTo(baseX + toLeftRight*distanceX, baseY + toUpDown*distanceY));
                }
            }
            buttons.remove(currButton);
            buttons.remove(button);
            i--;
        }
        // play the PathTransition for each button
        transition(taken, buttonPath);
    }

    private void transition(Button[][] button, Path[][] path) {
        for(int i = 0; i < button.length; i++){
            for(int j = 0; j < button[0].length; j++){
                PathTransition trn = new PathTransition();
                trn.setDuration(Duration.seconds(1));
                trn.setPath(path[i][j]);
                trn.setNode(button[i][j]);
                trn.play();
            }
        }
    }

    public String getPieceImage(int rank) {
        String animalImage = switch (rank) {
            case 1 -> GameImages.MOUSE;
            case 2 -> GameImages.CAT;
            case 3 -> GameImages.WOLF;
            case 4 -> GameImages.DOG;
            case 5 -> GameImages.LEOPARD;
            case 6 -> GameImages.TIGER;
            case 7 -> GameImages.LION;
            case 8 -> GameImages.ELEPHANT;
            default -> "";
        };
        return animalImage;
    }

    /**
     * The function that adds the image under the corresponding player
     *
     * @param imageView variable used to load images
     */

    public void centerImage(ImageView imageView) {
        Image img = imageView.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = imageView.getFitWidth() / img.getWidth();
            double ratioY = imageView.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if (ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            imageView.setX((imageView.getFitWidth() - w) / 2);
            imageView.setY((imageView.getFitHeight() - h) / 2);

        }
    }

    /**
     * The function that compares the rank of the animals the players have picked
     */
    void proceedToGame() {
        // disable all Buttons in this class using getClass().getDeclaredFields()
        for (Field field : getClass().getDeclaredFields()) {
            if (field.getType().equals(Button.class)) {
                try {
                    ((Button) field.get(this)).setDisable(true);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        PlayerType firstPlayer = (Player1Rank > Player2Rank) ? PlayerType.PLAYER1 : PlayerType.PLAYER2;
        turnFinalText.setText(PlayerType.getName(firstPlayer) + " will go first!");
        Timer t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        Stage window = (Stage) animal1.getScene().getWindow();
                        window.hide();
                        new GameManager(firstPlayer);
                    }
                });
            }
        }, 4000);
    }
}
