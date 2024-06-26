package aga.repository;

import aga.service.domain.Rating;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MapperRating {

    @Mapping(source = "game", target = "gameId")
    @Mapping(source = "player", target = "playerId")
    Rating mapToRating(RatingEntity ratingEntity);

    default Integer mapToGameId(GameEntity gameEntity) {
        return gameEntity.getGameId();
    }

    default Integer mapToPlayerId(PlayerEntity playerEntity) {
        return playerEntity.getId();
    }

    @Mapping(source ="gameId", target = "game")
    @Mapping(source ="playerId", target = "player")
    @Mapping(target="id",expression = "java(createRatingId(rating))")
    RatingEntity mapToRatingEntity(Rating rating);

    default RatingId createRatingId(Rating rating) {
        return new RatingId(rating.getGameId(),rating.getPlayerId());
    }
    default GameEntity gameIdToGameEntity(Integer gameId){
        GameEntity gameEntity = new GameEntity();
        gameEntity.setGameId(gameId);
        return gameEntity;
    }
    default PlayerEntity playerIdToPlayerEntity(Integer playerId){
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setId(playerId);
        return playerEntity;
    }
}
