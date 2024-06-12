package aga.service.service;

import aga.service.domain.Game;
import aga.service.domain.Player;
import aga.service.domain.Rating;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class DataReaderService {
    public Mapper read(String path) throws FileNotFoundException {

        File txtFile = new File(path);
        Scanner scan = new Scanner(txtFile);

        List<String> data = new ArrayList<>();

        while (scan.hasNext()) {
            data.add(scan.nextLine());
        }
        data.remove(0);

        Mapper mapper = new Mapper();

        mapper.data = data.stream()
                .map(line -> line.split("\\s+"))
                .collect(Collectors.toList());

        return mapper;
    }


    static class Mapper {
        List<String[]> data;

        List<Player> toPlayers() {

            List<Player> players = new ArrayList<>();
            for (String[] dataForPlayer : data) {
                Player player = Player.builder()
                        .id(Integer.valueOf(dataForPlayer[0]))
                        .firstName(dataForPlayer[1])
                        .lastName(dataForPlayer[2])
                        .age(Integer.parseInt(dataForPlayer[3]))
                        .build();
                players.add(player);
            }
            return players;
        }

        List<Game> toGames() {
            List<Game> games = new ArrayList<>();
            for (String[] dataForGame : data) {
                Game game = Game.builder()
                        .id(Integer.valueOf(dataForGame[0]))
                        .name(dataForGame[1])
                        .category(dataForGame[2])
                        .build();
                games.add(game);
            }
            return games;
        }

        List<Rating> toRating() {
            List<Rating> ratings = new ArrayList<>();
            for (String[] dataForRating : data) {
                Rating rating = Rating.builder()
                        .gameId(Integer.parseInt(dataForRating[0]))
                        .playerId(Integer.parseInt(dataForRating[1]))
                        .state(dataForRating[2])
                        .rating(Integer.parseInt(dataForRating[3]))
                        .build();
                ratings.add(rating);
            }
            return ratings;
        }
    }

}
