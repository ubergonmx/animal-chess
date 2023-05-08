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

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

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

    /**
     * This function displays the buttons and their equivalent value
     *
     * @param event variable that represents that a card has been selected
     */
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

    /**
     * This shuffles the value of teh buttons
     */
    public void shuffle() {
        int baseX = 35;
        int baseY = 15;
        MoveTo base = new MoveTo(baseX, baseY);

        int y = 44;
        int toRight = 81;
        int toLeft = 86;
        Path down = new Path();
        down.getElements().add(base);
        down.getElements().add(new LineTo(baseX, baseY + y));

        Path up = new Path();
        up.getElements().add(base);
        up.getElements().add(new LineTo(baseX, baseY - y));

        Path left = new Path();
        left.getElements().add(base);
        left.getElements().add(new LineTo(baseX - toLeft, baseY));

        Path right = new Path();
        right.getElements().add(base);
        right.getElements().add(new LineTo(baseX + toRight, baseY));

        Path upLeft = new Path();
        upLeft.getElements().add(base);
        upLeft.getElements().add(new LineTo(baseX - toLeft, baseY - y));

        Path upRight = new Path();
        upRight.getElements().add(base);
        upRight.getElements().add(new LineTo(baseX + toRight, baseY - y));

        Path downLeft = new Path();
        downLeft.getElements().add(base);
        downLeft.getElements().add(new LineTo(baseX - toLeft, baseY + y));

        Path downRight = new Path();
        downRight.getElements().add(base);
        downRight.getElements().add(new LineTo(baseX + toRight, baseY + y));

        PathTransition trn = new PathTransition();
        trn.setDuration(Duration.seconds(1));
        trn.setPath(right);
        trn.setNode(animal1);
        trn.play();

        PathTransition trn2 = new PathTransition();
        trn2.setDuration(Duration.seconds(1));
        trn2.setPath(left);
        trn2.setNode(animal2);
        trn2.play();

        PathTransition trn3 = new PathTransition();
        trn3.setDuration(Duration.seconds(1));
        trn3.setPath(downRight);
        trn3.setNode(animal3);
        trn3.play();

        PathTransition trn4 = new PathTransition();
        trn4.setDuration(Duration.seconds(1));
        trn4.setPath(downLeft);
        trn4.setNode(animal4);
        trn4.play();


        PathTransition trn5 = new PathTransition();
        trn5.setDuration(Duration.seconds(1));
        trn5.setPath(right);
        trn5.setNode(animal5);
        trn5.play();

        PathTransition trn6 = new PathTransition();
        trn6.setDuration(Duration.seconds(1));
        trn6.setPath(left);
        trn6.setNode(animal6);
        trn6.play();

        PathTransition trn7 = new PathTransition();
        trn7.setDuration(Duration.seconds(1));
        trn7.setPath(upRight);
        trn7.setNode(animal7);
        trn7.play();

        PathTransition trn8 = new PathTransition();
        trn8.setDuration(Duration.seconds(1));
        trn8.setPath(upLeft);
        trn8.setNode(animal8);
        trn8.play();

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
