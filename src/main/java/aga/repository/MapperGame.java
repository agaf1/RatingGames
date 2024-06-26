package aga.repository;

import aga.service.domain.Game;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MapperGame {

    @Mapping(source = "gameId", target = "id")
    Game mapToGame(GameEntity gameEntity);

    @Mapping(source = "id", target = "gameId")
    GameEntity mapToGameEntity(Game game);
}
