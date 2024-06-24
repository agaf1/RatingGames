package aga.service.service;

import aga.repository.RatingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RatingService {

    private final RatingRepository ratingRepository;


    public String getGameNameWithTheHighestNumberOfRatings() {
        return ratingRepository.getGameNameWithTheHighestNumberOfRatings();
    }

    public double getAverageRatingForGivenCategoryOfGame(String category) {
        double result = ratingRepository.getAverageRatingForGivenCategoryOfGame(category);
        result = Math.round(result * 100);
        result /= 100;
        return result;
    }

    public int getNumberOfPlayersWhoHaveGivenAtLeastOneRatingAndDoNotHaveGivenGameState(String state) {
        return ratingRepository.getNumberOfPlayersWhoHaveGivenAtLeastOneRatingAndDoNotHaveGivenGameState(state);
    }

    public Map<AgeCategory, List<String>> getListOfGameNameWithTheHighestNumberOfRatingsInAgeCategory() {
        Map<AgeCategory, List<String>> result = new HashMap<>();

        putToResultMap(result,AgeCategory.JUNIOR,10,19);
        putToResultMap(result,AgeCategory.SENIOR,20,49);
        putToResultMap(result,AgeCategory.VETERAN,50,99);

        return result;
    }

    private void putToResultMap(Map<AgeCategory, List<String>> result, AgeCategory ageCategory, int ageStart, int ageEnd){
        List<String> names = ratingRepository
                .getListOfGameNameWithTheHighestNumberOfRatingsInAgeCategory(ageStart, ageEnd);
        result.put(ageCategory, names);
    }

    enum AgeCategory {JUNIOR, SENIOR, VETERAN}
}
