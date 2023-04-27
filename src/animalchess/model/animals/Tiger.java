package animalchess.model.animals;

import animalchess.model.*;

/**
 * A class to declare a Tiger piece
 */

public class Tiger extends Piece implements RiverJump {
    public Tiger(PlayerType player, GamePoint point) {
        super(player, PieceType.Tiger, point, 6);
    }

    @Override
    public boolean capture(Piece animal) {
        boolean canCapture;

        if (RANK >= animal.RANK && OWNER != animal.OWNER) {
            canCapture = true;
            //System.out.println(String.format("%s(%d<=%d) is captured by %s", animal.getPieceName(), animal.RANK, RANK, PlayerType.getName(OWNER)));
        } else if (animal.isTrapped() && OWNER != animal.OWNER) {
            canCapture = true;
            //System.out.println(String.format("%s(%d<=%d) is captured by %s", animal.getPieceName(), animal.RANK, RANK, PlayerType.getName(OWNER)));
        } else {
            canCapture = false;
            System.out.println("Cannot be captured");
        }

        return canCapture;
    }
}
