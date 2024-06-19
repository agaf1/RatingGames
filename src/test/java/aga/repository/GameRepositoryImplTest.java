package aga.repository;

import aga.service.domain.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Sql("/clean-db.sql")
class GameRepositoryImplTest {

    @Autowired
    private GameRepositoryImpl gameRepository;

    @Test
    public void should_save_game_to_DB(){
        //given
        Game game = Game.builder().name("game").category("category").build();
        //when
        Game savedGame = gameRepository.save(game);
        //then
        assertThat(savedGame.getId()).isNotNull();
        assertThat(savedGame).isEqualTo(gameRepository.findByIdOrThrow(savedGame.getId()));
    }

    @Test
    public void should_not_find_game_with_given_id(){
        //given
        Integer gameId = 10;
        //when
        //then
        assertThatThrownBy(() -> gameRepository.findByIdOrThrow(gameId))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("The game with id=10 does not exist");
    }

}