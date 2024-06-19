package aga.repository;

import aga.service.domain.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Sql("/clean-db.sql")
class PlayerRepositoryImplTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void should_save_player_to_DB(){
        //given
        Player player = Player.builder().firstName("firstname").lastName("lastname").age(12).build();
        //when
        Player savedPlayer = playerRepository.save(player);
        //then
        assertThat(savedPlayer.getId()).isNotNull();
        assertThat(savedPlayer).isEqualTo(playerRepository.findByIdOrThrow(savedPlayer.getId()));
    }

    @Test
    public void should_not_find_player_for_given_id(){
        //given
        Integer playerId = 15;
        //when
        //then
        assertThatThrownBy(() -> playerRepository.findByIdOrThrow(playerId))
                .isInstanceOf(NoSuchElementException.class)
                .hasMessage("The player with id=15 does not exist");
    }

}