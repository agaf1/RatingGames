package aga.service.service;

import aga.repository.GameRepository;
import aga.repository.PlayerRepository;
import aga.repository.RatingRepository;
import aga.service.domain.Game;
import aga.service.domain.Player;
import aga.service.domain.Rating;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ImportServiceTest {

    @Mock
    private DataReaderService dataReader;
    @Mock
    private PlayerRepository playerRepository;
    @Mock
    private GameRepository gameRepository;
    @Mock
    private RatingRepository ratingRepository;
    @InjectMocks
    private ImportService importService;


    @Test
    public void should_create_players_and_save_to_DB() throws FileNotFoundException {
        //given
        String path = "src/main/resources/gracze.txt";

        Player player1 = Player.builder().id(1).firstName("Ala").lastName("Alanska").age(36).build();
        Player player2 = Player.builder().id(2).firstName("Tom").lastName("Dick").age(45).build();

        List<Player> data = List.of(player1, player2);

        //when
        Mockito.when(dataReader.read(path)).thenReturn(Mockito.mock(DataReaderService.Mapper.class));
        Mockito.when(dataReader.read(path).toPlayers()).thenReturn(data);

        importService.importPlayer(path);

        //then
        Mockito.verify(playerRepository, Mockito.atLeastOnce()).save(player1);
    }

    @Test
    public void should_create_games_and_save_to_DB() throws FileNotFoundException {
        //given
        String path = "games.txt";

        Game game1 = Game.builder().id(1).name("duck").category("family").build();
        Game game2 = Game.builder().id(1).name("lala").category("family").build();

        List<Game> data = List.of(game1, game2);

        //when
        Mockito.when(dataReader.read(path)).thenReturn(Mockito.mock(DataReaderService.Mapper.class));
        Mockito.when(dataReader.read(path).toGames()).thenReturn(data);

        importService.importGame(path);

        //then
        Mockito.verify(gameRepository, Mockito.atLeastOnce()).save(game1);
    }

    @Test
    public void should_create_ratings_and_save_to_DB() throws FileNotFoundException {
        //given
        String path = "ratings.txt";

        Rating rating1 = Rating.builder().gameId(1).playerId(1).rating(3).state("buy").build();
        Rating rating2 = Rating.builder().gameId(2).playerId(1).rating(5).state("to buy").build();

        List<Rating> data = List.of(rating1, rating2);

        //when
        Mockito.when(dataReader.read(path)).thenReturn(Mockito.mock(DataReaderService.Mapper.class));
        Mockito.when(dataReader.read(path).toRating()).thenReturn(data);

        importService.importRating(path);

        //then
        Mockito.verify(ratingRepository, Mockito.atLeastOnce()).save(rating1);
    }


}