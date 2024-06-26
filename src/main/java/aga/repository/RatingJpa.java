package aga.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RatingJpa extends CrudRepository<RatingEntity, RatingId> {


    @Query(value =
            """
                    Select games.name
                    from ratings 
                    join games on games.game_id = ratings.game_game_id
                    group by games.name
                    order by count(ratings.game_game_id) DESC
                    limit 1;
                                        """
            , nativeQuery = true
    )
    String getGameNameWithTheHighestNumberOfRatings();

    @Query(value = """
            Select avg(ratings.rating)
            from ratings 
            join games on ratings.game_game_id = games.game_id
            where games.category = :category;
            """
            , nativeQuery = true)
    double getAverageRatingForGivenCategoryOfGame(String category);

    @Query(value = """
            select count(*) from(
            Select players.id
            from players 
            join ratings on players.id = ratings.player_id
            where ratings.state = :state
            group by players.id)c;
            """
            , nativeQuery = true)
    int getNumberOfPlayersWhoHaveGivenAtLeastOneRatingAndDoNotHaveGivenGameState(String state);

    @Query(value = """
            select name from(
            select games.game_id, games.name as name, count(*) as num
            from games join ratings on games.game_id = ratings.game_game_id
            join players on players.id = ratings.player_id
            where players.age between :ageStart and :ageEnd
            group by game_id
            having num = (
            select max(num)
            from(
            select games.game_id, games.name, count(*) as num
            from games join ratings on games.game_id = ratings.game_game_id
            join players on players.id = ratings.player_id
            where players.age between :ageStart and :ageEnd
            group by game_id)c))a;
            """
            , nativeQuery = true)
    List<String> getListOfGameNameWithTheHighestNumberOfRatingsInAgeCategory(int ageStart, int ageEnd);
}
