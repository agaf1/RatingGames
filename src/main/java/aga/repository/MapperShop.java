package aga.repository;

import aga.service.domain.Shop;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MapperShop {

    @Mapping(target = "gameId" , source = "game_id")
    Shop mapToShop(ShopEntity shopEntity);

    @Mapping(target = "game_id" , source = "gameId")
    ShopEntity mapToShopEntity(Shop shop);
}
