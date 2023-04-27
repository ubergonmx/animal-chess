package animalchess.model;

import animalchess.model.animals.*;

/**
 * A class for declaring a new Player depending on given type of player
 */

public class Player {
    public static final int MAX_PIECE = 8;

    private Piece[] pieces;
    private int nCount;
    public final PlayerType PLAYER;

    /**
     * This creates a player.
     *
     * @param player the owner
     * @see PlayerType
     */

    public Player(PlayerType player) {
        pieces = new Piece[MAX_PIECE];
        nCount = 0;
        PLAYER = player;
    }

    /**
     * This function instantiates an animal piece to the pieces collection of the player
     * depending on the given rank.
     *
     * @param rank    int from 1-8
     * @param initPos the initial position for the piece
     * @return returns the instantiated animal piece
     */

    public Piece addPiece(int rank, GamePoint initPos) {
        switch (rank) {
            case 1 -> pieces[nCount] = new Mouse(PLAYER, initPos);
            case 2 -> pieces[nCount] = new Cat(PLAYER, initPos);
            case 3 -> pieces[nCount] = new Wolf(PLAYER, initPos);
            case 4 -> pieces[nCount] = new Dog(PLAYER, initPos);
            case 5 -> pieces[nCount] = new Leopard(PLAYER, initPos);
            case 6 -> pieces[nCount] = new Tiger(PLAYER, initPos);
            case 7 -> pieces[nCount] = new Lion(PLAYER, initPos);
            case 8 -> pieces[nCount] = new Elephant(PLAYER, initPos);
        }
        nCount++;
        return pieces[nCount - 1];
    }

    /**
     * This function removes the piece by setting the bCaptured to true
     *
     * @param piece the piece to be removed
     */

    public void removePiece(Piece piece) {
        System.out.println("removing");
        for (int i = 0; i < nCount; i++)
            if (pieces[i].equals(piece)) {
                System.out.println("removed");
                pieces[i].setCaptured(true);
            }
    }

    /**
     * This function checks if all the pieces are captured
     *
     * @return returns true if each piece in the pieces collection are captured, otherwise returns false
     */

    public boolean isEmpty() {

        /*DEBUGGING PURPOSES
        for (int i = 0; i < nCount; i++)
            System.out.println("(" + PLAYER + ") P#" + i + "=" + pieces[i].isCaptured() + ", " + pieces[i]);
        System.out.println();
        */
        for (int i = 0; i < nCount; i++) {
            if (!pieces[i].isCaptured())
                return false;
        }
        return true;
    }

    /**
     * This returns a copy of the pieces of the player
     *
     * @return a copy of the pieces collection
     */

    public Piece[] getPieces() {
        return pieces.clone();
    }

    /**
     * This gets the number of the index in the Piece array
     */

    public int getCount() {
        return nCount;
    }

    /**
     * This returns the name of the player
     *
     * @return the player name with color
     * @see PlayerType
     */

    @Override
    public String toString() {
        return PLAYER.toString();
    }
}