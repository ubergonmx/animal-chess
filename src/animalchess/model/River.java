package animalchess.model;

import animalchess.model.animals.Elephant;
import animalchess.model.animals.Mouse;

/**
 * A class for declaring the river.
 */
public class River extends Tile {

    private Piece riverPiece;

    public River() {
        super(TileType.River);
    }

    /**
     * This function sets the river piece position to being in the river.
     *
     * @param piece
     */
    public void setRiverPiece(Piece piece) {
        riverPiece = piece;
        ((RiverMove) riverPiece).setOnLand(false);
    }

    /**
     * This function removes the mouse
     */
    public void removeRiverPiece() {
        ((RiverMove) riverPiece).setOnLand(true);
        riverPiece = null;
    }

    /**
     * This function gets the mouse piece
     *
     * @return
     */
    public Piece getRiverPiece() {
        return riverPiece;
    }

    /**
     * This function checks if there is a mouse
     *
     * @return
     */
    public boolean hasRiverPiece() {
        return riverPiece != null;
    }

    @Override
    public String toString() {
        String image = null;
        if (hasRiverPiece()) {
            if (riverPiece instanceof Mouse)
                image = GameImages.MOUSE_RIVER;
            else if (riverPiece instanceof Elephant)
                image = GameImages.ELEPHANT_RIVER;
        } else
            image = GameImages.RIVER;
        return image;
    }
}