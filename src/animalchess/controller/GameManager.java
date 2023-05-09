package animalchess.controller;
/**
 * A class that manages the whole game.
 */

import animalchess.view.*;
import animalchess.model.*;

import javax.swing.*;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

/**
 * The controller that handles the game (GameGUI and Board)
 */

public class GameManager implements ActionListener, MouseListener {

    private final GameGUI view;
    private final Board board;

    private boolean bHolding;
    private boolean hasMoved;
    private Piece pcHolding;

    /**
     * The class that sets up the board and manages the moves in the board
     *
     * @param firstPlayer the current player
     */
    public GameManager(PlayerType firstPlayer) {
        bHolding = false;

        PlaySound.preload();
        board = new Board(9, 7);
        board.setCurrentPlayer((firstPlayer == board.P1.PLAYER) ? board.P1 : board.P2);
        view = new GameGUI();
        view.setModel(board);
        view.setMouseListener(this);
        view.setActionListener(this);
        view.setCurrentPlayer(board.getCurrentPlayer().toString());

        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (Exception ignored) {
        }
        PlaySound.start();
    }

    /**
     * The function that closes the game
     *
     * @param e the event that specifies the action
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Close game")) {
            view.dispatchEvent(new WindowEvent(view, WindowEvent.WINDOW_CLOSING));
        }

    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    /**
     * The function that performs the movement of the pieces
     *
     * @param e the event that specifies the action
     */
    public void mousePressed(MouseEvent e) {
        GamePoint pt = view.getGamePointAt(e.getPoint());
        Tile tile = view.getTileAt(pt);

        if (bHolding && tile.isHighlighted()) {

            hasMoved = board.movePiece(pcHolding, pt, true);
            if (hasMoved) {
                view.setModel(board);

                if (board.isGameOver() == GameState.CONTINUE) {
                    board.setCurrentPlayer((board.getCurrentPlayer() == board.P1) ? board.P2 : board.P1);
                    view.setCurrentPlayer(board.getCurrentPlayer().toString());
                } else {
                    board.removeHighlightAndFocus();
                    displayGameOver();
                }
                pcHolding = null;
            } else {
                bHolding = false;
            }
            /*board.removeHighlightAndFocus();
            view.setModel(board);*/
        } else {
            bHolding = false;
        }

        board.removeHighlightAndFocus();
        view.setModel(board);
        if (board.isGameOver() == GameState.CONTINUE) {
            switch (tile.getTileType()) {
                case Piece:
                    if (((Piece) tile).OWNER == board.getCurrentPlayer().PLAYER && !hasMoved) {
                        System.out.println("HOLDING! 1");
                        bHolding = true;
                        pcHolding = (Piece) tile;
                        board.removeHighlightAndFocus();
                        board.highlightTiles(pcHolding);
                        view.setModel(board);
                    }
                    break;
                case River:
                    Piece mousePiece = ((River) tile).getRiverPiece();
                    if (mousePiece != null && mousePiece.OWNER == board.getCurrentPlayer().PLAYER && !hasMoved) {
                        System.out.println("HOLDING! 2");
                        bHolding = true;
                        pcHolding = mousePiece;
                        board.highlightTiles(pcHolding);
                        view.setModel(board);
                    }
                    break;
                case Trap:
                    Piece trappedPiece = ((Trap) tile).getTrappedPiece();
                    if (trappedPiece != null) {
                        if (trappedPiece.OWNER == board.getCurrentPlayer().PLAYER && !hasMoved) {
                            System.out.println("HOLDING! 3");
                            bHolding = true;
                            pcHolding = trappedPiece;
                            board.highlightTiles(pcHolding);
                            view.setModel(board);
                        }
                    }
                    break;
            }
            hasMoved = false;

            //System.out.println(pt.r() + "-" + pt.c() + " " + tile.getTileType() + "~" + bHolding);
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    /**
     * This determines if the game has ended
     */
    public void displayGameOver() {
        if (board.isGameOver() == GameState.WIN_BY_PIECES) {
            //JOptionPane.showMessageDialog(null, "GAME OVER! Congratulations, " + board.getCurrentPlayer() + ", you have captured all the opponent's pieces!");
            view.setHeaderText("GAME OVER! " + board.getCurrentPlayer() + " have captured all the opponent's pieces!");
            PlaySound.end();
        } else if (board.isGameOver() == GameState.WIN_BY_DEN)
            //JOptionPane.showMessageDialog(null, "GAME OVER! Congratulations, " + board.getCurrentPlayer() + ", you have reached the opponent's den!");
            view.setHeaderText("GAME OVER! " + board.getCurrentPlayer() + " have reached the opponent's den!");

    }
}

