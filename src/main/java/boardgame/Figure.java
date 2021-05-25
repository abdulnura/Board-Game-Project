package boardgame;

/**
 * This is the figure class used to get and assign figures to the players
 */
public enum Figure {

    /**
     * The X figure.
     */
    X("X"),

    /**
*The O figure.
*/
 O("O");

    private final String figure;

    Figure(final String figureName) {
        figure = figureName;
    }

    @Override
    public String toString() {
        return figure;
    }

}