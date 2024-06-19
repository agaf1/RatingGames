package aga.repository;

import aga.service.domain.Game;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MapperGame {

    Game mapToGame(GameEntity gameEntity);

    GameEntity mapToGameEntity(Game game);
}
