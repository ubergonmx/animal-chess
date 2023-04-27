package animalchess.model;

import animalchess.model.animals.Elephant;
import animalchess.model.animals.Mouse;

import java.awt.*;

/**
 * An abstract class for declaring the animal pieces depending on the given animal type.
 */
public abstract class Piece extends Tile {

    public final int RANK;
    public final PlayerType OWNER;
    public final PieceType TYPE;
    public final Color COLOR;

    private boolean bTrapped;
    private boolean bCaptured;
    private GamePoint currentPos;

    /**
     * The animal piece to be created.
     *
     * @param player the owner of the piece
     * @param type   the animal type
     * @param pos    the starting position on the board
     */

    public Piece(PlayerType player, PieceType type, GamePoint pos, int rank) {
        super(TileType.Piece);
        RANK = rank;
        OWNER = player;
        TYPE = type;
        currentPos = pos;
        bTrapped = false;
        bCaptured = false;

        COLOR = (OWNER == PlayerType.PLAYER1) ? Color.blue : Color.red;
    }

    /**
     * This checks the rank of the two animals that meet in one position.
     * It also captures the animal if it has lower rank and
     * It outputs whether the animal has been captured or cannot be captured.
     *
     * @param animal the opponent's piece
     * @return returns true if the animal can be captured, otherwise returns false
     */
    public abstract boolean capture(Piece animal);

    /**
     * This checks the owner of the animal den.
     * This prevents the player to go to his own den.
     *
     * @param animalDen the animal den owned by a player.
     * @return canCapture true or false if the animal can go to the den.
     */


    public boolean capture(AnimalDen animalDen) {
        boolean canCapture;

        if (OWNER != animalDen.OWNER) {
            canCapture = true;
        } else {
            canCapture = false;
            System.out.println("Cannot move piece to own den!");
        }

        return canCapture;
    }

    /**
     * This checks the owner of the trap and the trapped piece.
     * This prevents the player to go to his own trap.
     *
     * @param trap the trap owned by a player.
     * @return canCapture true or false if the animal can go to the trap or capture a trapped piece.
     */
    public boolean capture(Trap trap) {
        boolean canCapture;

        if (OWNER != trap.OWNER) {
            canCapture = true;
        } else if (trap.hasTrappedPiece()) {
            canCapture = true;
        } else {
            canCapture = false;
            System.out.println("Cannot move piece to own trap!");
        }

        return canCapture;
    }

    /**
     * This checks the mouse on the river and the trapped piece.
     * This prevents the player to go to the river unless its a Mouse.
     *
     * @param river the river (destination).
     * @return canCapture true or false if the animal can go to the river.
     */
    public boolean capture(River river) {
        boolean canCapture;

        if (this instanceof RiverMove) {
            if (river.hasRiverPiece()) {
                /*if (river.getRiverPiece() instanceof Mouse) {
                    if (((Mouse) this).isOnLand() != ((Mouse) river.getRiverPiece()).isOnLand())
                        canCapture = false;
                    else
                        canCapture = true;
                } else if (river.getRiverPiece() instanceof Elephant) {
                    if (((Elephant) this).isOnLand() != ((Elephant) river.getRiverPiece()).isOnLand())
                        canCapture = false;
                    else
                        canCapture = true;
                }*/
                if (((RiverMove) this).isOnLand() != ((RiverMove) river.getRiverPiece()).isOnLand()) {
                    canCapture = false;
                    if (this instanceof Elephant)
                        canCapture = true;
                } else
                    canCapture = true;
            } else
                canCapture = true;
        } else {
            canCapture = false;
            System.out.println("Cannot move piece to the river");
        }

        return canCapture;
    }


    /**
     * Sets true or false to bCaptured if an animal is captured.
     *
     * @param isCaptured a boolean parameter if animal is captured.
     */

    public void setCaptured(boolean isCaptured) {
        bCaptured = isCaptured;
    }

    /**
     * Sets true or false to bTrapped if an animal is trapped.
     *
     * @param isTrapped a boolean parameter if animal is trapped.
     */

    public void setTrapped(boolean isTrapped) {
        bTrapped = isTrapped;
    }

    /**
     * This sets the location of an animal to currentPos
     *
     * @param pos contains the position of an animal
     */

    public void setPos(GamePoint pos) {
        currentPos = pos;
    }

    /**
     * This returns if the animal is captured or not.
     */
    public boolean isCaptured() {
        return bCaptured;
    }

    /**
     * This returns if the animal is trapped or not.
     */
    public boolean isTrapped() {
        return bTrapped;
    }

    /**
     * This returns the full name of the animal.
     */
    public String getPieceName() {
        return TYPE.name();
    }

    /**
     * This returns the current position of the animal.
     */
    public GamePoint getPos() {
        return currentPos;
    }


    /**
     * This converts the GameImage (path) assigned to each animal to string
     *
     * @return returns the respective image path of the animal
     * @see GameImages
     */
    @Override
    public String toString() {

        String animalImage = switch (TYPE) {
            case Mouse -> GameImages.MOUSE;
            case Cat -> GameImages.CAT;
            case Wolf -> GameImages.WOLF;
            case Dog -> GameImages.DOG;
            case Leopard -> GameImages.LEOPARD;
            case Tiger -> GameImages.TIGER;
            case Lion -> GameImages.LION;
            case Elephant -> GameImages.ELEPHANT;
        };

        return animalImage;
    }
}