package animalchess.view;

import animalchess.model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.net.URL;

/**
 * A renderer class used to render images of the game on board.
 */

public class ImageRenderer extends DefaultTableCellRenderer {

    /**
     * Responsible for the rendering of images, cell highlights and focus.
     */
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        BoardTableModel board = (BoardTableModel) table.getModel();
        Tile tile = (Tile) value;
        Component comp = this;

        /*
        int leadRow = table.getSelectionModel().getLeadSelectionIndex();
        int leadCol = table.getColumnModel().getSelectionModel().getLeadSelectionIndex();
        boolean hasTileFocus = (row == leadRow && column == leadCol);*/

        JLabel lbl;
        if (tile.equals("")) {
            if (tile.isHighlighted()) {
                lbl = new JLabel();
                lbl.setOpaque(true);
                lbl.setBackground(tile.getColorHighlight());
                comp = lbl;
            }
        } else {
            ImageIcon icon = null;
            try {
                icon = new ImageIcon(new ImageIcon(ImageIO.read(new URL(tile.toString()))).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH));
            } catch (Exception ex) {
                //System.out.println(ex.getMessage());
            }
            if (tile instanceof Piece)
                lbl = new PieceLabel(icon, ((Piece) tile).COLOR);
            else if (tile instanceof River && ((River) tile).hasRiverPiece())
                lbl = new PieceLabel(icon, ((River) tile).getRiverPiece().COLOR);
            else if (tile instanceof Trap && ((Trap) tile).hasTrappedPiece())
                lbl = new PieceLabel(icon, ((Trap) tile).getTrappedPiece().COLOR);
            else
                lbl = new JLabel(icon);

            if (tile.isHighlighted()) {
                lbl.setOpaque(true);
                lbl.setBackground(tile.getColorHighlight());
            }

            if (tile.isFocused()) {
                boolean applyFocus = false;

                if ((tile instanceof Piece && board.getCurrentPlayer().PLAYER == ((Piece) tile).OWNER)) {
                    //System.out.println("PIECE SELECTED - APPLY FOCUS TRUE");
                    applyFocus = true;
                } else if ((tile instanceof River && ((River) tile).hasRiverPiece() && board.getCurrentPlayer().PLAYER == ((River) tile).getRiverPiece().OWNER)) {
                    //System.out.println("RIVER SELECTED - APPLY FOCUS TRUE");
                    applyFocus = true;
                } else if ((tile instanceof Trap && ((Trap) tile).hasTrappedPiece() && board.getCurrentPlayer().PLAYER == ((Trap) tile).getTrappedPiece().OWNER)) {
                    applyFocus = true;
                }
                if (applyFocus) {
                    lbl.setOpaque(true);
                    lbl.setBackground(tile.getColorFocus());
                    lbl.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
                }
            }
            comp = lbl;
        }

        return comp;
    }
}