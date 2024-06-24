package aga.repository;

import aga.service.domain.Rating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
@RequiredArgsConstructor
public class RatingRepositoryImpl implements RatingRepository {

    private final RatingJpa ratingJpa;
    private final GameJpa gameJpa;
    private final PlayerJpa playerJpa;
    private final MapperRating mapperRating;

    @Override
    @Transactional
    public Rating save(Rating rating) {
        RatingEntity ratingEntity = mapperRating.mapToRatingEntity(rating);
        RatingEntity savedRatingEntity = ratingJpa.save(ratingEntity);
        return mapperRating.mapToRating(savedRatingEntity);
    }

    @Override
    public Rating findByIdOrThrow(RatingId id) {
        RatingEntity ratingEntity = ratingJpa.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("The rating with gameId=%d and playerId=%d does not exist",id.getGameId(),id.getPlayerId())));
        return mapperRating.mapToRating(ratingEntity);
    }

    @Override
    public String getGameNameWithTheHighestNumberOfRatings() {
        return ratingJpa.getGameNameWithTheHighestNumberOfRatings();
    }

    @Override
    public double getAverageRatingForGivenCategoryOfGame(String category) {
        return ratingJpa.getAverageRatingForGivenCategoryOfGame(category);
    }

    @Override
    public int getNumberOfPlayersWhoHaveGivenAtLeastOneRatingAndDoNotHaveGivenGameState(String state) {
        return ratingJpa.getNumberOfPlayersWhoHaveGivenAtLeastOneRatingAndDoNotHaveGivenGameState(state);
    }

    @Override
    public List<String> getListOfGameNameWithTheHighestNumberOfRatingsInAgeCategory(int ageStart, int ageEnd) {
        return ratingJpa.getListOfGameNameWithTheHighestNumberOfRatingsInAgeCategory(ageStart,ageEnd);
    }
}
