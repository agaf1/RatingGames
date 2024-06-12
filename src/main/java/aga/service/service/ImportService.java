package aga.service.service;

import aga.repository.GameRepository;
import aga.repository.PlayerRepository;
import aga.repository.RatingRepository;
import aga.service.domain.Game;
import aga.service.domain.Player;
import aga.service.domain.Rating;
import lombok.RequiredArgsConstructor;

import java.io.FileNotFoundException;
import java.util.List;

@RequiredArgsConstructor
public class ImportService {

    private final DataReaderService dataReader;
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final RatingRepository ratingRepository;

    public void importTxtToPlayer(String path) throws FileNotFoundException {

        List<Player> players = dataReader.read(path).toPlayers();

        for(Player player: players){
            playerRepository.save(player);
        }
    }
    public void importTxtToGame(String path) throws FileNotFoundException {

        List<Game> games = dataReader.read(path).toGames();

        for(Game game: games){
           gameRepository.save(game);
        }
    }

    public void importTxtToRating(String path) throws FileNotFoundException{

        List<Rating> ratings = dataReader.read(path).toRating();

        for(Rating rating: ratings){
            ratingRepository.save(rating);
        }
    }

}
