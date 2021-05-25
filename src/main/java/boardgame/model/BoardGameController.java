package boardgame.model;


import boardgame.exceptions.InvalidPointException;
import boardgame.exceptions.PointOccupiedException;
import boardgame.Figure;
import boardgame.Player;

/**
 * This is the Game Controller
 * It holds the rules of the game
 */
public class BoardGameController {

    private static final String Game_Name = "Board Game";
    private static final int Player1 = 0;
    private static final int Player2 = 1;
    private static final int DIAG_UP = 1;
    private static final int DIAG_DOWN = 0;
    private static final int DIAGS_FOR_CHECK = 2;
    private final String gameName;
    private final Player[] players;
    private final Board board;
    private final int boardLength;


    /**
     * The board game controller checks if the game name is null, if not assign the following:
     * @param gameName Game Name.
     * @param players  Players.
     * @param board    Board.
     */
    public BoardGameController(final String gameName, final Player[] players, final Board board) {
        if (gameName == null || gameName.isEmpty()) {
            this.gameName = Game_Name;

        } else {
            this.gameName = gameName;

        }
        this.board = board;
        this.players = players;
        this.boardLength = board.getFiguresArray().length;


    }

    /**
     * Gets the winner of the game
     *
     * @return winner if conditions for a win are met
     */
    public Player getWinner() {
        for (Player playerForCheck : players) {
            String figureToCheck = playerForCheck.getFigure().toString();
            if (checkRowsForWin(figureToCheck) || checkDiagsForWin(figureToCheck) || checkColsForWin(figureToCheck)) {
                return playerForCheck;

            }


        }
        return null;
    }

    /**
     * Gets game name
     *
     * @return game name
     */
    public String getGameName() {
        return gameName;
    }

    /**
     * Gets players
     *
     * @return players
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * gets board
     *
     * @return Board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * This Checks the moves
     *
     * @param x coordinate
     * @param y coordinate
     * @param player ( 1 or 2)
     * @throws PointOccupiedException if coordinate chosen is not in the coordinates
     */
    public void move(final int x, final int y, final Player player) throws PointOccupiedException {
        try {
            if (board.getFigure(x, y) != null) {
                throw new PointOccupiedException();

            } else {
                try {
                    board.setFigure(x, y, player.getFigure());


                } catch (InvalidPointException e) {
                    e.printStackTrace();
                }
            }
        } catch (InvalidPointException e) {
            e.printStackTrace();
        }

    }

    /**
     * Gets Next Turns
     *
     * @return if there is no winner and next player can player
     */
    public boolean getNextTurn() {
        final Figure[][] figures = getBoard().getFiguresArray();
        if (getWinner() != null) {
            return false;
        }
        for (Figure[] figureArray : figures) {
            for (Figure figureValue : figureArray) {
                if (figureValue == null) {

                    return true;
                }

            }
        }
        return false;
    }

    /**
     * Gets Current Player
     *
     * @param firstPlayer Always Starts
     * @return returns the the current player
     */
    public Player getCurrentPlayer(final Player firstPlayer) {
        int firstPlayerNum = getFirstPlayerNum(firstPlayer);
        int[] playersTurns = getPlayersTurns();

        if (playersTurns[Player1] == playersTurns[Player2]) {
            return players[firstPlayerNum];
        } else if (firstPlayerNum == Player1) {

            return players[Player2];

        } else {

            return players[Player1];
        }
    }

    /**
     * Checks Column for win
     *
     * @param playerFigure (X or O)
     * @return if its a win or not
     */
    private boolean checkColsForWin(final String playerFigure) {
        for (int column = 0; column < boardLength; column++) {
            if (getPlayerForLine(column, playerFigure) == 3) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks Rows for win
     *
     * @param playerFigure (X or O)
     * @return if its a win in the Rows or not
     */

    private boolean checkRowsForWin(final String playerFigure) {
        for (int row = 0; row < boardLength; row++) {
            if (getPlayerForColumn(row, playerFigure) == 3) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks Diagonals for win
     *
     * @param playerFigure (X or O)
     * @return if its a win in the diagonals or not
     */
    private boolean checkDiagsForWin(final String playerFigure) {

        for (int diag_direction = 0; diag_direction < DIAGS_FOR_CHECK; diag_direction++) {
            if (getPlayerForDiag(diag_direction, playerFigure) == 3) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks player or figure in the diagonal
     *
     * @param direction Diagonals
     * @param playerFigure X or O
     * @return if there is 3 matching player figure X or O
     */
    private int getPlayerForDiag(final int direction, final String playerFigure) {
        int playerDiagCount = 0;
        try {
            if (direction == DIAG_UP) {
                for (int i = 0; i < boardLength; i++) {

                    if ((board.getFigure(i, i) != null) && equalsFigures(i, i, playerFigure)) {
                        playerDiagCount++;
                    }
                }
            }


            if (direction == DIAG_DOWN) {
                for (int i = 0; i < boardLength; i++) {
                    int row = (boardLength - 1) - 2;
                    if ((board.getFigure(row, i) != null) && equalsFigures(row, i, playerFigure)) {
                        playerDiagCount++;
                    }
                }
            }
            if (direction == DIAG_DOWN) {
                for (int i = 0; i < boardLength; i++) {
                    int row = (boardLength - 1) - 1;
                    if ((board.getFigure(row, i) != null) && equalsFigures(row, i, playerFigure)) {
                        playerDiagCount++;
                    }
                }
            }
            if (direction == DIAG_DOWN) {
                for (int i = 0; i < boardLength; i++) {
                    int row = (boardLength - 1) - 3;
                    if ((board.getFigure(row, i) != null) && equalsFigures(row, i, playerFigure)) {
                        playerDiagCount++;
                    }
                }
            }
            if (direction == DIAG_DOWN) {
                for (int i = 0; i < boardLength; i++) {
                    int row = (boardLength - 1) - 4;
                    if ((board.getFigure(row, i) != null) && equalsFigures(row, i, playerFigure)) {
                        playerDiagCount++;
                    }
                }
            }
            if (direction == DIAG_DOWN) {
                for (int i = 0; i < boardLength; i++) {
                    int row = (boardLength - 1) - 0;
                    if ((board.getFigure(row, i) != null) && equalsFigures(row, i, playerFigure)) {
                        playerDiagCount++;
                    }
                }
            }


        } catch (InvalidPointException e) {
            e.printStackTrace();
        }
        return playerDiagCount;
    }

    /**
     * Checks players or figures in the columns
     *
     * @param column columns
     * @param playerFigure X or O
     * @return If players figure is within the column or else it will throw an exception
     */
    private int getPlayerForColumn(final int column, final String playerFigure) {
        int playerColumnCount = 0;
        for (int i = 0; i < boardLength; i++) {
            try {
                if ((board.getFigure(i, column) != null) && equalsFigures(i, column, playerFigure)) {
                    playerColumnCount++;
                }
            } catch (InvalidPointException e) {
                e.printStackTrace();
            }
        }
        return playerColumnCount;
    }

    /**
     * Gets Players Turns
     *
     * @return player X if player O just played and viceversa
     */
    private int[] getPlayersTurns() {
        int[] playersTurns = new int[players.length];
        for (int playerNum = 0; playerNum < players.length; playerNum++) {
            playersTurns[playerNum] = getPlayerForBoard(players[playerNum].getFigure().toString());
        }
        return playersTurns;
    }

    private int getPlayerForBoard(final String playerFigure) {
        int playerBoardCount = 0;
        for (int i = 0; i < boardLength; i++) {
            playerBoardCount += getPlayerForLine(i, playerFigure);
        }
        return playerBoardCount;
    }

    /**
     * Checks players or figures in the rows
     *
     * @param row Rows
     * @param playerFigure X or O
     * @return If players figure is within the row or else it will throw an exception
     */
    private int getPlayerForLine(final int row, final String playerFigure) {
        int playerRowCount = 0;
        for (int i = 0; i < board.getRowLength(row); i++) {
            try {
                if ((board.getFigure(row, i) != null) && equalsFigures(row, i, playerFigure)) {
                    playerRowCount++;
                }
            } catch (InvalidPointException e) {
                e.printStackTrace();
            }
        }
        return playerRowCount;
    }

    private int getFirstPlayerNum(final Player firstPlayer) {
        int firstPlayerPositionNum = 0;
        for (int num = 0; num < players.length; num++) {
            if (players[num] == firstPlayer) {
                firstPlayerPositionNum = num;
            }
        }
        return firstPlayerPositionNum;
    }

    /**
     * @param x Coordinate
     * @param y Coordinate
     * @param playerFigure X or O
     * @return
     * @throws InvalidPointException
     */
    private boolean equalsFigures(final int x, final int y, final String playerFigure) throws InvalidPointException {
        return board.getFigure(x, y).toString().equals(playerFigure);
    }
}







