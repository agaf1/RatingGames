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
        return gameEntity.getId();
    }

    default Integer mapToPlayerId(PlayerEntity playerEntity) {
        return playerEntity.getId();
    }

    @Mapping(ignore = true, target = "game")
    @Mapping(ignore = true, target = "player")
    RatingEntity mapToRatingEntity(Rating rating);
}
