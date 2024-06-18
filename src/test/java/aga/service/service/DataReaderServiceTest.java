package aga.service.service;

import aga.service.domain.Game;
import aga.service.domain.Player;
import aga.service.domain.Rating;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
class DataReaderServiceTest {

    private DataReaderService dataReaderService = new DataReaderService();
    private DataReaderService.Mapper mapper = new DataReaderService.Mapper();

    @Test
    public void should_import_file_with_players_and_return_list_of_players() throws FileNotFoundException {
        //given
        URL path = this.getClass().getClassLoader().getResource("gracze_test.txt");

        //when
        DataReaderService.Mapper result = dataReaderService.read(path.getPath());

        List<Player> players = result.toPlayers();

        //then
        int expectedSize = 3;

        Player player1 = Player.builder().id(1).firstName("Jozef").lastName("Gorecki").age(29).build();
        Player player2 = Player.builder().id(2).firstName("Przemyslaw").lastName("Mazurek").age(68).build();
        Player player3 = Player.builder().id(3).firstName("Cezary").lastName("Kaczmarczyk").age(41).build();

        assertThat(result.data.size()).isEqualTo(expectedSize);

        assertThat(players.get(0)).isEqualTo(player1);
        assertThat(players.get(1)).isEqualTo(player2);
        assertThat(players.get(2)).isEqualTo(player3);
    }
    @Test
    public void should_import_file_with_games_and_return_list_of_games() throws FileNotFoundException {
        //given
        URL path = this.getClass().getClassLoader().getResource("gry_test.txt");

        //when
        DataReaderService.Mapper result = dataReaderService.read(path.getPath());

        List<Game> games = result.toGames();

        //then
        int expectedSize = 3;

        Game game1 = Game.builder().id(1).name("Wsiasc do Pociagu: Europa").category("familijna").build();
        Game game2 = Game.builder().id(2).name("Pandemia").category("kooperacyjna").build();
        Game game3 = Game.builder().id(3).name("Na skrzydlach").category("familijna").build();

        assertThat(result.data.size()).isEqualTo(expectedSize);

        assertThat(games.get(0)).isEqualTo(game1);
        assertThat(games.get(1)).isEqualTo(game2);
        assertThat(games.get(2)).isEqualTo(game3);
    }

    @Test
    public void should_import_file_with_rating_and_return_list_of_ratings() throws FileNotFoundException {
        //given
        URL path = this.getClass().getClassLoader().getResource("oceny_test.txt");

        //when
        DataReaderService.Mapper result = dataReaderService.read(path.getPath());

        List<Rating> ratings = result.toRating();

        //then
        int expectedSize = 3;

        Rating rating1 = Rating.builder().gameId(66).playerId(1).state("posiada").rating(8).build();
        Rating rating2 = Rating.builder().gameId(72).playerId(1).state("chce kupic").rating(3).build();
        Rating rating3 = Rating.builder().gameId(79).playerId(1).state("sprzedal").rating(8).build();

        assertThat(result.data.size()).isEqualTo(expectedSize);

        assertThat(ratings.get(0)).isEqualTo(rating1);
        assertThat(ratings.get(1)).isEqualTo(rating2);
        assertThat(ratings.get(2)).isEqualTo(rating3);
    }
}