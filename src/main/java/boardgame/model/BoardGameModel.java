package boardgame.model;

import boardgame.*;
import boardgame.exceptions.PointOccupiedException;
import boardgame.View.View;

public class BoardGameModel {
    final private View view;
    final private BoardGameController gameController;

    public BoardGameModel(View view) {
        this.view = view;
        this.gameController = view.getBoardGameController();
    }

    public void theBoardGameModel() throws Exception {
        while (gameController.getNextTurn()) {
            Point point = view.startTurn();
            try {
                Player currentPlayer = gameController.getCurrentPlayer(gameController.getPlayers()[0]);
                gameController.move(point.getX(), point.getY(), currentPlayer);
            } catch (PointOccupiedException e) {
                view.showPointOccupied();
            }
            view.showBoard();
        }
        if (gameController.getWinner() != null) {
            view.showWinner();
            view.anotherGame();
        } else {
            view.showDraw();
            view.anotherGame();
        }
    }

}
