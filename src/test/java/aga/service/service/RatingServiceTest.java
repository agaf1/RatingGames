package aga.service.service;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql("/clean-db.sql")
class RatingServiceTest {

    @Autowired
    private ImportService importService;
    @Autowired
    private RatingService ratingService;

    @Test
    public void should_get_gameName_with_the_highest_number_of_ratings() throws FileNotFoundException {
        //given
        importService.importGame("src/test/resources/gry_test.txt");
        importService.importPlayer("src/test/resources/gracze_test.txt");
        importService.importRating("src/test/resources/oceny_test.txt");
        //when
        String result = ratingService.getGameNameWithTheHighestNumberOfRatings();
        //then
        assertThat(result).isEqualTo("Pandemia");
    }

    @Test
    public void should_get_average_rating_for_given_category_of_game() throws FileNotFoundException {
        //given
        importService.importGame("src/test/resources/gry_test.txt");
        importService.importPlayer("src/test/resources/gracze_test.txt");
        importService.importRating("src/test/resources/oceny_test.txt");
        String category1 = "familijna";
        String category2 = "kooperacyjna";
        //when
        double result1 = ratingService.getAverageRatingForGivenCategoryOfGame(category1);
        double result2 = ratingService.getAverageRatingForGivenCategoryOfGame(category2);
        //then
        assertThat(result1).isEqualTo(8.00);
        assertThat(result2).isEqualTo(5.50);
    }

    @Test
    public void should_get_number_of_players_who_have_given_at_least_one_rating_and_do_not_have_given_game_state() throws FileNotFoundException {
        //given
        importService.importGame("src/test/resources/gry_test.txt");
        importService.importPlayer("src/test/resources/gracze_test.txt");
        importService.importRating("src/test/resources/oceny_test.txt");
        String state = "posiada";
        //when
        int result = ratingService.getNumberOfPlayersWhoHaveGivenAtLeastOneRatingAndDoNotHaveGivenGameState(state);
        //then
        assertThat(result).isEqualTo(2);
    }

    @Test
    public void should_get_list_of_game_name_with_the_highest_number_of_ratings_in_age_category() throws FileNotFoundException {
        //given
        importService.importGame("src/test/resources/gry_test.txt");
        importService.importPlayer("src/test/resources/gracze_test.txt");
        importService.importRating("src/test/resources/oceny_test.txt");
        Map<RatingService.AgeCategory, List<String>> mapAnswer = Map.of(RatingService.AgeCategory.JUNIOR, List.of()
                , RatingService.AgeCategory.SENIOR, List.of("Pandemia")
                , RatingService.AgeCategory.VETERAN, List.of());
        //when
        Map<RatingService.AgeCategory, List<String>> result = ratingService.getListOfGameNameWithTheHighestNumberOfRatingsInAgeCategory();
        //then
        assertThat(result).isEqualTo(mapAnswer);
    }


}