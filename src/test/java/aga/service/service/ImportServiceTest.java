package aga.service.service;

import aga.repository.PlayerRepository;
import aga.service.domain.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.FileNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ImportServiceTest {

    @Mock
    private DataReaderService dataReader;
    @Mock
    private PlayerRepository playerRepository;
    @InjectMocks
    private ImportService importService;


    @Test
    public void should_create_players_and_save_to_DB() throws FileNotFoundException {
        //given
        String path = "src/main/resources/gracze.txt";

        Player player1 = new Player();
        player1.setId(1);
        player1.setFirstName("Ala");
        player1.setLastName("Alanska");
        player1.setAge(36);
        Player player2 = new Player();
        player2.setId(2);
        player2.setFirstName("Tom");
        player2.setLastName("Dick");
        player2.setAge(45);

        List<Player> data = List.of(player1,player2);

        //when
        Mockito.when(dataReader.read(path)).thenReturn(Mockito.mock(DataReaderService.Mapper.class));
        Mockito.when(dataReader.read(path).toPlayers()).thenReturn(data);

        importService.importTxtToPlayer(path);

        //then
        Mockito.verify(playerRepository, Mockito.atLeastOnce()).save(player1);
    }

}