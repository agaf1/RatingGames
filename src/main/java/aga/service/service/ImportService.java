package aga.service.service;
import aga.repository.PlayerRepository;
import aga.service.domain.Player;
import lombok.RequiredArgsConstructor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
public class ImportService {

    private final DataReaderService dataReader;
    private final PlayerRepository playerRepository;

    public void importTxtToPlayer(String path) throws FileNotFoundException {

        List<Player> players = dataReader.read(path).toPlayers();

        for(Player player: players){
            playerRepository.save(player);
        }
    }

}
