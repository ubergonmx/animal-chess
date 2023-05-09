package animalchess.view;

import animalchess.model.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.URL;

/**
 * The view of the game
 */

public class GameGUI extends JFrame {

    private JLabel lblHeader;
    private JButton closeBtn;

    private JTable board;
    private BoardTableModel model;

    private final int CELL_HEIGHT = 60;
    private final int CELL_WIDTH = 60;


    public GameGUI() {
        super();

        setTitle("Animal Chess Game");
        try {
            setIconImage(new ImageIcon(ImageIO.read(new URL(GameImages.ANIMAL_DEN))).getImage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        InitializeComponents();

        //https://stackoverflow.com/a/2442615
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Setups the window and the components of the game
     */
    private void InitializeComponents() {
        // top --------------------------------------
        JPanel northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());

        lblHeader = new JLabel("<html>Animal Chess: <font color=blue>Player 1</font>'s turn</html>");
        northPanel.add(lblHeader);

        add(northPanel, BorderLayout.NORTH);

        // bottom -----------------------------------
        JPanel southPanel = new JPanel();
        southPanel.setLayout(new FlowLayout());


        closeBtn = new JButton("Close game");
        southPanel.add(closeBtn);


        add(southPanel, BorderLayout.SOUTH);

        //----------------CENTER--------------------
        board = new JTable();

        model = new BoardTableModel(new Board(9, 7));
        board.setModel(model);
        board.setDefaultRenderer(Object.class, new ImageRenderer());
        board.setGridColor(new Color(150, 150, 150));

        //https://stackoverflow.com/a/39676115
        board.getTableHeader().setUI(null);

        //https://stackoverflow.com/a/6451356
        board.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        board.setCellSelectionEnabled(true);

        board.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        fixBoard();

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.add(board);


        JScrollPane scroll = new JScrollPane(centerPanel);
        add(scroll, BorderLayout.CENTER);


    }

    /**
     * Refreshes cell size of the board (JTable)
     */
    public void fixBoard() {
        board.setRowHeight(CELL_HEIGHT);
        board.setAlignmentX(Component.CENTER_ALIGNMENT);
        for (int i = 0; i < board.getColumnCount(); i++)
            board.getColumnModel().getColumn(i).setPreferredWidth(CELL_WIDTH);
    }

    public void setActionListener(ActionListener listener) {
        closeBtn.addActionListener(listener);
    }

    public void setMouseListener(MouseListener listener) {
        board.addMouseListener(listener);
    }

    /**
     * Returns the GamePoint given the coordinates (Point)
     *
     * @see GamePoint
     */
    public GamePoint getGamePointAt(Point pt) {
        return new GamePoint(board.rowAtPoint(pt), board.columnAtPoint(pt));
    }

    public Tile getTileAt(GamePoint pt) {
        return (Tile) board.getValueAt(pt.r(), pt.c());
    }

    /**
     * Displays the current player
     *
     * @param playerName the String of PlayerType
     */
    public void setCurrentPlayer(String playerName) {
        lblHeader.setText("<html>Animal Chess: " + playerName + "'s turn</html>");
    }

    /**
     * Sets a text on the header
     *
     * @param headerText custom message on header
     */
    public void setHeaderText(String headerText) {
        lblHeader.setText("<html>" + headerText + "</html>");
    }

    /**
     * Refreshes the board on display when passed a new model (Board)
     *
     * @param boardModel the Board class
     */
    public void setModel(Board boardModel) {
        board.setModel(new BoardTableModel(boardModel));
        board.invalidate();
        fixBoard();
        pack();
    }
}