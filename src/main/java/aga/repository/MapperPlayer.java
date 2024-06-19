package aga.repository;

import aga.service.domain.Player;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MapperPlayer {

    Player mapToPlayer(PlayerEntity playerEntity);

    PlayerEntity mapToPlayerEntity(Player player);
}
