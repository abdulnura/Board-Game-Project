package boardgame;

public enum Figure {
    X("X"), O("O");

    private final String figure;

    Figure(final String figureName) {
        figure = figureName;
    }

    @Override
    public String toString() {
        return figure;
    }

}