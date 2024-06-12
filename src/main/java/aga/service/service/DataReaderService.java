package aga.service.service;

import aga.service.domain.Player;

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
                Player player = new Player();
                player.setId(Integer.valueOf(dataForPlayer[0]));
                player.setFirstName(dataForPlayer[1]);
                player.setLastName(dataForPlayer[2]);
                player.setAge(Integer.parseInt(dataForPlayer[3]));
                players.add(player);
            }
            return players;
        }


//        List<Game> toGames() {
//            return null;
//        }
    }

}
