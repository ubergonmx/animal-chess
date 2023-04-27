package animalchess.model.animals;

import animalchess.model.*;

/**
 * A class to declare a Mouse piece
 */

public class Mouse extends Piece implements RiverMove {

    private boolean onLand;

    public Mouse(PlayerType player, GamePoint point) {
        super(player, PieceType.Mouse, point, 1);

        onLand = true;
    }

    @Override
    public boolean capture(Piece animal) {
        boolean canCapture;

        if (animal.TYPE == PieceType.Mouse || animal.TYPE == PieceType.Elephant) {
            canCapture = true;
            if (animal instanceof Mouse) {
                System.out.println(String.format("%s (%s onland) vs %s (%s onland) by %s", animal.getPieceName(), ((Mouse) animal).onLand, getPieceName(), onLand, PlayerType.getName(OWNER)));
                if (onLand != ((Mouse) animal).onLand)
                    canCapture = false;
            }
            if (animal instanceof Elephant) {
                System.out.println(String.format("%s vs %s (%s onland) by %s", animal.getPieceName(), getPieceName(), onLand, PlayerType.getName(OWNER)));
                if (!onLand)
                    canCapture = false;
            }
        } else if (animal.isTrapped() && OWNER != animal.OWNER) {
            canCapture = true;
            System.out.println(String.format("%s(%d<=%d) 2 is captured by %s", animal.getPieceName(), animal.RANK, RANK, PlayerType.getName(OWNER)));
        } else {
            canCapture = false;
            System.out.println("Cannot be captured");
        }

        return canCapture;
    }

    public boolean isOnLand() {
        return onLand;
    }

    public void setOnLand(boolean onLand) {
        this.onLand = onLand;
    }
}
