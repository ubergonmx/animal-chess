package animalchess.model;

import javax.swing.table.*;

/**
 * A class to set the table model of the board (JTable)
 */

public class BoardTableModel extends DefaultTableModel {

    private Board board;

    /**
     * The table model for the board on View (JTable)
     *
     * @param board assigning the Board.
     */
    public BoardTableModel(Board board) {
        super(board.getData(), new String[board.COL]);
        this.board = board;
    }

    /**
     * Setting the JTable (board) to be non-editable
     */
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    /**
     * This returns the Tile on the cell given the row and column.
     */
    @Override
    public Tile getValueAt(int row, int column) {
        return (Tile) super.getValueAt(row, column);
    }

    /**
     * Returns current player.
     */
    public Player getCurrentPlayer() {
        return board.getCurrentPlayer();
    }

}