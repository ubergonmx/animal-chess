package animalchess.view;

import javax.swing.*;
import java.awt.*;

/**
 * A JLabel class to draw circle/oval behind pieces
 */

public class PieceLabel extends JLabel {

    private final Color color;

    public PieceLabel(Icon image, Color color) {
        super(image);

        this.color = color;
    }

    /**
     * Paints component to have a circle/oval behind the piece
     * SOURCE: https://stackoverflow.com/a/2839517
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(2));
        g2.setColor(color);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawOval(5, 5, 49, 49);
        ui.update(g2, this);
    }
}
