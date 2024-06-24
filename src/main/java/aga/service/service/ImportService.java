package aga.service.service;

import aga.repository.GameRepository;
import aga.repository.PlayerRepository;
import aga.repository.RatingRepository;
import aga.service.domain.Game;
import aga.service.domain.Player;
import aga.service.domain.Rating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ImportService {

    private final DataReaderService dataReader;
    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final RatingRepository ratingRepository;

    public void importPlayer(String path) throws FileNotFoundException {

        List<Player> players = dataReader.read(path).toPlayers();
        //TODO  sprubuj uzyc players.forEach
        for(Player player: players){
            playerRepository.save(player);
        }
    }
    public void importGame(String path) throws FileNotFoundException {

        List<Game> games = dataReader.read(path).toGames();
        //TODO  sprubuj uzyc players.forEach
        for(Game game: games){
           gameRepository.save(game);
        }
    }

    public void importRating(String path) throws FileNotFoundException{

        List<Rating> ratings = dataReader.read(path).toRating();
        //TODO  sprubuj uzyc players.forEach
        for(Rating rating: ratings){
            ratingRepository.save(rating);
        }
    }

}
