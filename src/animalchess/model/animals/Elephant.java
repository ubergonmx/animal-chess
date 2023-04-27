package animalchess.model.animals;

import animalchess.model.*;

/**
 * A class to declare an Elephant piece
 */

public class Elephant extends Piece {

    private boolean onLand;

    public Elephant(PlayerType player, GamePoint point) {
        super(player, PieceType.Elephant, point, 8);
    }

    @Override
    public boolean capture(Piece animal) {
        boolean canCapture;

        if (animal.TYPE != PieceType.Mouse && RANK >= animal.RANK && OWNER != animal.OWNER) {
            canCapture = true;
            //System.out.println(String.format("%s(%d<=%d) is captured by %s", animal.getPieceName(), animal.RANK, RANK, PlayerType.getName(OWNER)));
        } else if (animal.TYPE != PieceType.Mouse && animal.isTrapped() && OWNER != animal.OWNER) {
            canCapture = true;
            System.out.println(String.format("%s(%s && %s) is captured by %s", animal.getPieceName(), animal.isTrapped(), (OWNER != animal.OWNER), PlayerType.getName(OWNER)));
        } else {
            canCapture = false;
            System.out.println("Cannot be captured");
        }

        return canCapture;
    }

    public void setOnLand(boolean onLand) {
        this.onLand = onLand;
    }

    public boolean isOnLand() {
        return onLand;
    }
}
