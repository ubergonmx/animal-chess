package animalchess.model;

import java.awt.*;

/**
 * Enumeration of Player types returning the respective color of each player
 */
public enum PlayerType {
    PLAYER1 {
        public String toString() {
            return "<font color=BLUE>Player 1</font>";
        }
    }, PLAYER2 {
        public String toString() {
            return "<font color=RED>Player 2</font>";
        }
    };

    /**
     * This returns the name of the player
     *
     * @param player the type of player
     */

    public static String getName(PlayerType player) {
        return (player == PlayerType.PLAYER1) ? "Player 1" : "Player 2";
    }
}