package aga.repository;

import aga.service.domain.Rating;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
        GameEntity gameEntity = gameJpa.findById(rating.getGameId()).orElseThrow();
        PlayerEntity playerEntity = playerJpa.findById(rating.getPlayerId()).orElseThrow();
        RatingEntity ratingEntity = mapperRating.mapToRatingEntity(rating);
        ratingEntity.setGameAndPlayer(gameEntity, playerEntity);
        RatingEntity savedRatingEntity = ratingJpa.save(ratingEntity);
        return mapperRating.mapToRating(savedRatingEntity);
    }

    @Override
    public Rating findByIdOrThrow(RatingId id) {
        RatingEntity ratingEntity = ratingJpa.findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("The rating with gameId=%d and playerId=%d does not exist",id.getGameId(),id.getPlayerId())));
        return mapperRating.mapToRating(ratingEntity);
    }
}
