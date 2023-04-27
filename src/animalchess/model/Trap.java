package animalchess.model;

/**
 * A class that manages the traps
 */
public class Trap extends Tile {
    public final PlayerType OWNER;
    private Piece trappedPiece;

    /**
     * The function that gets the attributes and methods of the parent class Tile
     *
     * @param owner
     */
    public Trap(PlayerType owner) {
        super(TileType.Trap);
        OWNER = owner;
    }

    /**
     * The function that specifies if the animal is trapped
     *
     * @param animal the animal piece
     */
    public void setTrappedPiece(Piece animal) {
        trappedPiece = animal;
        trappedPiece.setTrapped(true);
    }

    /**
     * The function that sets the animal as out of the trap
     */
    public void removeTrappedPiece() {
        trappedPiece.setTrapped(false);
        trappedPiece = null;
    }

    /**
     * Function that gets the piece in the trap
     *
     * @return
     */
    public Piece getTrappedPiece() {
        return trappedPiece;
    }

    /**
     * This allows the game to know that an animal is trapped
     *
     * @return
     */
    public boolean hasTrappedPiece() {
        return trappedPiece != null;
    }


    @Override
    public String toString() {
        String displayImage;

        if (hasTrappedPiece()) {
            displayImage = switch (trappedPiece.TYPE) {
                case Mouse -> GameImages.MOUSE_TRAP;
                case Cat -> GameImages.CAT_TRAP;
                case Wolf -> GameImages.WOLF_TRAP;
                case Dog -> GameImages.DOG_TRAP;
                case Leopard -> GameImages.LEOPARD_TRAP;
                case Tiger -> GameImages.TIGER_TRAP;
                case Lion -> GameImages.LION_TRAP;
                case Elephant -> GameImages.ELEPHANT_TRAP;
            };
        } else
            displayImage = GameImages.TRAP;

        return displayImage;
    }
}