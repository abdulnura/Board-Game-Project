package boardgame.database;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

/**
 * DAO class for the {@link Game} entity.
 */
@RegisterBeanMapper(Game.class)
public interface GameDao {
    /**
     * Inserts {@link Game} instance into the database.
     *
     * @param game {@link Game} instance to be inserted.
     * @return {@code 1} if insertion was successful, {@code 0} if there was an error.
     */
    @SqlUpdate("INSERT INTO game(first_player_name,second_player_name,start_time, date, steps, winner, winner_score) VALUES (:firstPlayerName,:secondPlayerName,:startTime, :date, :steps, :winner, :winnerScore)")
    int insert(@BindBean Game game);

    /**
     * Returns the list of 5 best results with respect to the score
     * for solving the game.
     *
     * @return the list of 5 best results with respect to the score
     * for solving the game.
     */
    @SqlQuery("SELECT * FROM game ORDER BY winner_score DESC limit 5")
    List<Game> getTopFiveGames();

    @SqlQuery("SELECT * from game where winner = :winner")
    Game checkIfPlayerExist(@Bind("winner") String winner);

    @SqlUpdate("update game set winner_score = :newScore where winner = :winnerName")
    void updatePlayerScore(@Bind("winnerName") String id, @Bind("newScore") int newScore);
}
