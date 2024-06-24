package aga.repository;

import aga.service.domain.Game;
import aga.service.domain.Player;
import aga.service.domain.Rating;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Sql("/clean-db.sql")
class RatingRepositoryImplTest {

    @Autowired
    private RatingRepositoryImpl ratingRepository;
    @Autowired
    private GameRepositoryImpl gameRepository;
    @Autowired
    private PlayerRepositoryImpl playerRepository;

    @Test
    public void should_save_rating_to_DB(){
        //given
        Player player = Player.builder().firstName("first").lastName("last").age(12).build();
        Player savedPlayer = playerRepository.save(player);
        Game game = Game.builder().name("name").category("category").build();
        Game savedGame = gameRepository.save(game);
        Rating rating = Rating.builder().state("state").rating(5)
                .playerId(savedPlayer.getId())
                .gameId(savedGame.getId()).build();
        //when
        Rating savedRating = ratingRepository.save(rating);
        //then
        assertThat(savedRating)
                .isEqualTo(ratingRepository.findByIdOrThrow(new RatingId(savedGame.getId(),savedPlayer.getId())));
        assertThat(savedRating.getPlayerId()).isEqualTo(rating.getPlayerId());
        assertThat(savedRating.getGameId()).isEqualTo(rating.getGameId());
    }

    @Test
    public void should_not_find_rating_for_given_id(){
        //given
        RatingId id = new RatingId(1,2);
        //when
        //then
        assertThatThrownBy(() -> ratingRepository.findByIdOrThrow(id))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("The rating with gameId=1 and playerId=2 does not exist");
    }
}