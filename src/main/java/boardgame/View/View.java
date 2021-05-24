package boardgame.View;


import boardgame.model.BoardGameController;
import boardgame.model.Point;

public interface View {

    Point startTurn();

    void showGameName();

    void showPlayers();

    void showBoard();

    void showWinner();

    void showDraw();

    void anotherGame() throws Exception;

    void showPointOccupied();

    BoardGameController getBoardGameController();

}