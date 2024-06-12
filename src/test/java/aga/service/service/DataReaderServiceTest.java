package aga.service.service;

import aga.service.domain.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileNotFoundException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DataReaderServiceTest {

    private DataReaderService dataReaderService = new DataReaderService();
    private DataReaderService.Mapper mapper = new DataReaderService.Mapper();

    @Test
    public void should_import_file_and_return_list_of_stringArray() throws FileNotFoundException {
        //given
        String path = "src/main/resources/gracze.txt";

        //when
        DataReaderService.Mapper result = dataReaderService.read(path);

        //then
        int expectedSize = 1041;
        int expectedLength = 4;

        assertThat(result.data.size()).isEqualTo(expectedSize);
        assertThat(result.data.get(1).length).isEqualTo(expectedLength);
    }

    @Test
    public void should_create_object_player_from_array_of_String() {
        //given
        String[] tab1 = {"1", "ala", "dada", "3"};
        String[] tab2 = {"2", "tom", "dick", "34"};

        List<String[]> data = List.of(tab1, tab2);
        mapper.data = data;

        //when
        List<Player> result = mapper.toPlayers();

        //then
        Player player = new Player();
        player.setId(1);
        player.setFirstName("ala");
        player.setLastName("dada");
        player.setAge(3);

        assertThat(result.get(0)).isEqualTo(player);
    }


}