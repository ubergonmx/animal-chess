package animalchess.model;

/**
 * A class used for the positioning of the animal pieces.
 */
public class GamePoint {
    private int nRow;
    private int nCol;

    /**
     * This assigns nRow and nCol with the row and column of the animal specified by the player.
     *
     * @param r the row to which the specified animal is located in
     * @param c the column to which the specified animal is located in
     */

    public GamePoint(int r, int c) {
        nRow = r;
        nCol = c;
    }

    /**
     * This assigns the location of the piece given its letter and number position on the board.
     *
     * @param a a combination of a letter and a number corresponding to the position of the piece on the board.
     */

    public GamePoint(String a) {
        if (a.length() == 2) {
            nRow = (int) a.toUpperCase().charAt(0) - 'A';
            nCol = (int) a.charAt(1) - '1';
        } else
            System.out.println("Wrong input");
    }

    /**
     * This returns the row of the piece
     */

    public int r() {
        return nRow;
    }

    /**
     * This returns the column of the piece
     */

    public int c() {
        return nCol;
    }

    /**
     * For debugging purposes only
     * This is used to check the row and column of an animal piece.
     */

    @Override
    public String toString() {
        return "ROW: " + nRow + " COL: " + nCol;
    }

    /**
     * This checks if the row and column of the GamePoint matches with given object (GamePoint)
     *
     * @param obj the GamePoint to be compared
     */

    @Override
    public boolean equals(Object obj) {
        if (obj != null) {
            GamePoint newPoint = (GamePoint) obj;
            return nRow == newPoint.nRow && nCol == newPoint.nCol;
        }
        return false;
    }

}