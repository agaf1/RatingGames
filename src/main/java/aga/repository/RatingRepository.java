package aga.repository;

import aga.service.domain.Rating;

import java.util.List;

public interface RatingRepository {

    Rating save (Rating rating);

    Rating findByIdOrThrow(RatingId id);

    String getGameNameWithTheHighestNumberOfRatings();

    double getAverageRatingForGivenCategoryOfGame(String category);

    int getNumberOfPlayersWhoHaveGivenAtLeastOneRatingAndDoNotHaveGivenGameState(String state);

     List<String> getListOfGameNameWithTheHighestNumberOfRatingsInAgeCategory(int ageStart, int ageEnd);
}
