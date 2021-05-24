package boardgame.model;


import boardgame.exceptions.InvalidBoardSizeException;
import boardgame.exceptions.InvalidPointException;
import boardgame.Figure;

/**
 * the board implementation class,
 */
public class Board {
    private static final int MIN_COORDINATE = 0;

    private static final int DEFAULT_BOARD_SIZE = 5;

    private final Figure[][] figures;

    /**
     * This is the board that shows the size 5x5
     * @param customBoardSize is bonded as the default board size
     * @throws InvalidBoardSizeException if the custom board size is less than 5x5
     */
    public Board(final int customBoardSize) throws InvalidBoardSizeException {
        if (customBoardSize < DEFAULT_BOARD_SIZE) {
            this.figures = new Figure[DEFAULT_BOARD_SIZE][DEFAULT_BOARD_SIZE];
            throw new InvalidBoardSizeException();
        } else {
            this.figures = new Figure[customBoardSize][customBoardSize];
        }
    }

    /**
     * Check the figure in the coordinates
     * @param x coordinate x
     * @param y coordinate y
     * @param figure (X or O)
     * @throws InvalidPointException  if figures are not in the coordinate
     */
    public void setFigure(final int x, final int y, final Figure figure) throws InvalidPointException {
        if (checkCoordinates(x, y)) {
            figures[x][y] = figure;
        } else {
            throw new InvalidPointException();
        }
    }

    /**
     * Gets the figures from Coordinate
     * @param x coordinate on the board
     * @param y coordinate on the board
     * @return figures on the coordinate
     * @throws InvalidPointException if the coordinate is out of bound
     */
    public Figure getFigure(final int x, final int y) throws InvalidPointException {
        if (checkCoordinates(x, y)) {
            return figures[x][y];
        } else {
            throw new InvalidPointException();
        }
    }

    public Figure[][] getFiguresArray() {
        return figures;
    }

    /**
     * Shows row lenght
     * @param row row
     * @return the lenght
     */
    public int getRowLength(final int row) {
        return figures[row].length;
    }

    /**
     * Checks Coordinates
     * @param x x
     * @param y x
     * @return the coordinates x and y
     */
    private boolean checkCoordinates(final int x, final int y) {
        return (checkCoordinate(x) && checkCoordinate(y));
    }

    /**
     * changes teh value of the coordinate
     * @param coordinate .
     * @return the new value
     */
    public boolean checkCoordinate(final int coordinate) {
        return (coordinate >= MIN_COORDINATE && coordinate <= figures.length - 1);
    }


}
