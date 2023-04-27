package animalchess.model;

import java.awt.Color;

/**
 * A class that is mainly used as a data type for the creation of the board.
 */

public class Tile {
    private TileType type;
    private boolean bHighlighted;
    private boolean bFocused;
    private Color colorFocus;
    private Color colorHighlight;

    /**
     * This sets the type to empty, and initializes the colors for highlight and focus.
     */
    public Tile() {
        this.type = TileType.Empty;
        colorHighlight = new Color(184, 207, 229);
        colorFocus = new Color(230, 216, 166);
    }

    /**
     * This sets the type to the specific Tile type
     *
     * @param type the Tile to set type
     * @see TileType
     */
    public Tile(TileType type) {
        this();
        this.type = type;
    }

    /**
     * This returns the type of the tile
     *
     * @see TileType
     */
    public TileType getTileType() {
        return type;
    }

    /**
     * This returns the color of the highlight
     */
    public Color getColorHighlight() {
        return colorHighlight;
    }

    /**
     * This sets the Tile to be highlighted or not when rendered
     *
     * @param bHighlighted flag to set highlight
     */
    public void setHighlight(boolean bHighlighted) {
        this.bHighlighted = bHighlighted;
    }

    /**
     * This returns if the Tile is highlighted or not
     */
    public boolean isHighlighted() {
        return bHighlighted;
    }

    /**
     * This returns the Color of the focus
     */
    public Color getColorFocus() {
        return colorFocus;
    }

    /**
     * This sets the Tile to be focused or not when rendered
     *
     * @param bFocused flag to set focus
     */
    public void setFocused(boolean bFocused) {
        this.bFocused = bFocused;
    }

    /**
     * This returns if the Tile is focused or not
     */
    public boolean isFocused() {
        return bFocused;
    }

    @Override
    public String toString() {
        return "";
    }
}