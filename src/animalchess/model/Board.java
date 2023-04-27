package animalchess.model;

/**
 * The class that creates the board given the number of rows and columns
 */

public class Board {
    private Tile[][] board;
    public final int ROW;
    public final int COL;

    public final Player P1;
    public final Player P2;
    private Player currentPlayer;

    private GameState isGameOver;

    /**
     * The board to be created.
     *
     * @param row number of rows
     * @param col number of columns
     */
    public Board(int row, int col) {
        ROW = row;
        COL = col;
        board = new Tile[ROW][COL];

        P1 = new Player(PlayerType.PLAYER1);
        P2 = new Player(PlayerType.PLAYER2);

        isGameOver = GameState.CONTINUE;
        DefaultBoardSetup();
    }

    /**
     * This initializes the tiles and the position of pieces, traps, river, and animal den.
     */
    private void DefaultBoardSetup() {
        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COL; j++)
                board[i][j] = new Tile();

        for (int i = 2; i <= 6; i++) {
            if (i == 2) {
                for (int j = 2; j <= 4; j++)
                    board[i][j] = new River();
            }
            if (i == 6) {
                for (int j = 2; j <= 4; j++)
                    board[i][j] = new River();
            }
        }

        board[0][3] = new AnimalDen(PlayerType.PLAYER1);
        board[2][1] = new Trap(PlayerType.PLAYER1);
        board[3][3] = new Trap(PlayerType.PLAYER1);
        board[2][5] = new Trap(PlayerType.PLAYER1);

        board[8][3] = new AnimalDen(PlayerType.PLAYER2);
        board[6][1] = new Trap(PlayerType.PLAYER2);
        board[5][3] = new Trap(PlayerType.PLAYER2);
        board[6][5] = new Trap(PlayerType.PLAYER2);

        board[0][0] = P1.addPiece(7, InitPiecePos.LION_P1);
        board[0][6] = P1.addPiece(6, InitPiecePos.TIGER_P1);
        board[1][1] = P1.addPiece(4, InitPiecePos.DOG_P1);
        board[1][5] = P1.addPiece(2, InitPiecePos.CAT_P1);
        board[2][0] = P1.addPiece(1, InitPiecePos.MOUSE_P1);
        board[1][2] = P1.addPiece(5, InitPiecePos.LEOPARD_P1);
        board[1][4] = P1.addPiece(3, InitPiecePos.WOLF_P1);
        board[2][6] = P1.addPiece(8, InitPiecePos.ELEPHANT_P1);

        board[6][0] = P2.addPiece(8, InitPiecePos.ELEPHANT_P2);
        board[7][2] = P2.addPiece(3, InitPiecePos.WOLF_P2);
        board[7][4] = P2.addPiece(5, InitPiecePos.LEOPARD_P2);
        board[6][6] = P2.addPiece(1, InitPiecePos.MOUSE_P2);
        board[7][1] = P2.addPiece(2, InitPiecePos.CAT_P2);
        board[7][5] = P2.addPiece(4, InitPiecePos.DOG_P2);
        board[8][0] = P2.addPiece(6, InitPiecePos.TIGER_P2);
        board[8][6] = P2.addPiece(7, InitPiecePos.LION_P2);
    }

    /**
     * Returns the data of the board
     *
     * @return 2 dimensional Tile-array
     */
    public Tile[][] getData() {
        return board;
    }

    /**
     * This is the getter of the currentPlayer variable
     *
     * @return returns the assigned player
     */

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * This finds the nearest position that is not of a River tile type.
     *
     * @param curPos    the current position of the chosen animal
     * @param direction the direction to which the player wants to move the piece.
     * @return newPos the new position assigned to the chosen piece
     */

    public GamePoint findNewPosNotRiver(GamePoint curPos, char direction) {
        int i;
        boolean canMove = false;
        GamePoint newPos = curPos;

        switch (Character.toUpperCase(direction)) {
            case 'L' -> {
                for (i = curPos.c() - 1; i >= 0 && !canMove; i--) {
                    if (!(board[curPos.r()][i] instanceof River)) {
                        canMove = true;
                        newPos = new GamePoint(curPos.r(), i);
                    } else if (((River) board[curPos.r()][i]).hasRiverPiece())
                        i = -1;
                }
            }
            case 'R' -> {
                for (i = curPos.c() + 1; i < COL && !canMove; i++) {
                    if (!(board[curPos.r()][i] instanceof River)) {
                        canMove = true;
                        newPos = new GamePoint(curPos.r(), i);
                    } else if (((River) board[curPos.r()][i]).hasRiverPiece())
                        i = COL;
                }
            }
            case 'U' -> {
                for (i = curPos.r() - 1; i >= 0 && !canMove; i--) {
                    if (!(board[i][curPos.c()] instanceof River)) {
                        canMove = true;
                        newPos = new GamePoint(i, curPos.c());
                    } else if (((River) board[i][curPos.c()]).hasRiverPiece())
                        i = -1;
                }
            }
            case 'D' -> {
                for (i = curPos.r() + 1; i < ROW && !canMove; i++) {
                    if (!(board[i][curPos.c()] instanceof River)) {
                        canMove = true;
                        newPos = new GamePoint(i, curPos.c());
                    } else if (((River) board[i][curPos.c()]).hasRiverPiece())
                        i = ROW;
                }
            }
        }
        return newPos;
    }

    /**
     * This sets the tiles to be higlighted when rendered.
     *
     * @param focusPiece the selected/focused piece on board
     */
    public void highlightTiles(Piece focusPiece) {
        GamePoint pt = focusPiece.getPos();
        board[pt.r()][pt.c()].setFocused(true);

        if (focusPiece instanceof RiverMove) {
            if (pt.r() - 1 != -1) //UP
                if (movePiece(focusPiece, new GamePoint(pt.r() - 1, pt.c()), false))
                    board[pt.r() - 1][pt.c()].setHighlight(true);

            if (pt.r() + 1 != ROW) //DOWN
                if (movePiece(focusPiece, new GamePoint(pt.r() + 1, pt.c()), false))
                    board[pt.r() + 1][pt.c()].setHighlight(true);

            if (pt.c() - 1 != -1) //LEFT
                if (movePiece(focusPiece, new GamePoint(pt.r(), pt.c() - 1), false))
                    board[pt.r()][pt.c() - 1].setHighlight(true);

            if (pt.c() + 1 != COL) //RIGHT
                if (movePiece(focusPiece, new GamePoint(pt.r(), pt.c() + 1), false))
                    board[pt.r()][pt.c() + 1].setHighlight(true);
        } else if (focusPiece instanceof RiverJump) {
            GamePoint up = findNewPosNotRiver(pt, 'U');
            GamePoint down = findNewPosNotRiver(pt, 'D');
            GamePoint left = findNewPosNotRiver(pt, 'L');
            GamePoint right = findNewPosNotRiver(pt, 'R');

            //UP
            if (movePiece(focusPiece, up, false) && !pt.equals(up))
                board[up.r()][up.c()].setHighlight(true);

            //DOWN
            if (movePiece(focusPiece, down, false) && !pt.equals(down))
                board[down.r()][down.c()].setHighlight(true);

            //LEFT
            if (movePiece(focusPiece, left, false) && !pt.equals(left))
                board[left.r()][left.c()].setHighlight(true);

            //RIGHT
            if (movePiece(focusPiece, right, false) && !pt.equals(right))
                board[right.r()][right.c()].setHighlight(true);
        } else {
            if (pt.r() - 1 != -1) //UP
                if (!(board[pt.r() - 1][pt.c()] instanceof River) && movePiece(focusPiece, new GamePoint(pt.r() - 1, pt.c()), false))
                    board[pt.r() - 1][pt.c()].setHighlight(true);

            if (pt.r() + 1 != ROW) //DOWN
                if (!(board[pt.r() + 1][pt.c()] instanceof River) && movePiece(focusPiece, new GamePoint(pt.r() + 1, pt.c()), false))
                    board[pt.r() + 1][pt.c()].setHighlight(true);

            if (pt.c() - 1 != -1) //LEFT
                if (!(board[pt.r()][pt.c() - 1] instanceof River) && movePiece(focusPiece, new GamePoint(pt.r(), pt.c() - 1), false))
                    board[pt.r()][pt.c() - 1].setHighlight(true);

            if (pt.c() + 1 != COL) //RIGHT
                if (!(board[pt.r()][pt.c() + 1] instanceof River) && movePiece(focusPiece, new GamePoint(pt.r(), pt.c() + 1), false))
                    board[pt.r()][pt.c() + 1].setHighlight(true);
        }
    }

    /**
     * Removes the highlight and focus when rendered.
     */
    public void removeHighlightAndFocus() {
        for (int i = 0; i < ROW; i++)
            for (int j = 0; j < COL; j++) {
                board[i][j].setHighlight(false);
                board[i][j].setFocused(false);
            }
    }

    /**
     * This checks if the piece can be moved to the destination
     *
     * @param piece    the current/holding piece
     * @param dest     the destination of the piece
     * @param trueMove if true, apply changes to the board
     */
    public boolean movePiece(Piece piece, GamePoint dest, boolean trueMove) {
        boolean canMove = false;
        boolean mouseMove = false;
        boolean trapMove = false;

        if (piece != null) {
            Tile target = board[dest.r()][dest.c()];
            GamePoint prev = piece.getPos();

            switch (target.getTileType()) {
                case Piece -> {
                    if (piece.capture((Piece) target)) {
                        System.out.println("Piece captured");
                        canMove = true;

                        if (trueMove) {
                            if (currentPlayer == P1)
                                P2.removePiece((Piece) target);
                            else
                                P1.removePiece((Piece) target);

                            board[dest.r()][dest.c()] = piece;
                            PlaySound.capture();
                        }
                    }
                }
                case AnimalDen -> {
                    if (piece.capture((AnimalDen) target)) {
                        System.out.println("Animal Den captured");
                        canMove = true;
                    }
                    if (canMove && trueMove) {
                        isGameOver = GameState.WIN_BY_DEN;
                        board[dest.r()][dest.c()] = piece;
                        PlaySound.end();
                    }
                }
                case Trap -> {
                    if (piece.capture((Trap) target)) {
                        System.out.println("Trap captured");
                        canMove = true;
                        trapMove = !((Trap) target).hasTrappedPiece();
                    }
                    if (canMove && trueMove) {
                        if (trapMove) {
                            ((Trap) board[dest.r()][dest.c()]).setTrappedPiece(piece);
                            PlaySound.moveTrap();
                        } else {
                            if (currentPlayer == P1)
                                P2.removePiece(((Trap) target).getTrappedPiece());
                            else
                                P1.removePiece(((Trap) target).getTrappedPiece());

                            board[dest.r()][dest.c()] = piece;
                            PlaySound.capture();
                        }
                    }
                }
                case River -> {
                    if (piece.capture((River) target)) {
                        System.out.println("River captured");
                        mouseMove = !((River) target).hasRiverPiece();
                        canMove = true;
                    }
                    if (canMove && trueMove) {
                        ((River) board[dest.r()][dest.c()]).setRiverPiece(piece);
                        if (mouseMove) {

                            PlaySound.moveRiver();
                        } else {
                            System.out.println("CAPTURED MOUSE");
                            if (currentPlayer == P1)
                                P2.removePiece(((River) target).getRiverPiece());
                            else
                                P1.removePiece(((River) target).getRiverPiece());

                            PlaySound.captureRiver();
                        }

                    }
                }
                case Empty -> {
                    System.out.println("moved");
                    canMove = true;
                    if (trueMove) {
                        board[dest.r()][dest.c()] = piece;
                        PlaySound.move();
                    }
                }
            }

            if (canMove && trueMove) {

                if (board[prev.r()][prev.c()] instanceof River) {
                    if (board[dest.r()][dest.c()] instanceof River) {
                        board[prev.r()][prev.c()] = new River();
                    } else
                        ((River) board[prev.r()][prev.c()]).removeRiverPiece();
                } else if (board[prev.r()][prev.c()] instanceof Trap) {
                    ((Trap) board[prev.r()][prev.c()]).removeTrappedPiece();
                    board[prev.r()][prev.c()] = new Tile();
                } else
                    board[prev.r()][prev.c()] = new Tile();

                piece.setPos(dest);
            }
        }
        return canMove;
    }

    /**
     * This is the setter of the currentPlayer variable
     *
     * @param nextPlayer assigns the new player to currentPlayer
     */

    public void setCurrentPlayer(Player nextPlayer) {
        currentPlayer = nextPlayer;
    }

    /**
     * This checks the number of players' pieces left on the board. If one player has no more pieces, then this will indicate that the game is over.
     *
     * @return isGameOver the status of the game whether it will continue or has ended by one player having no more pieces.
     */

    public GameState isGameOver() {
        if (P1.isEmpty() || P2.isEmpty())
            isGameOver = GameState.WIN_BY_PIECES;

        return isGameOver;
    }
}
