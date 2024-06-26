package aga.repository;

import aga.service.domain.Shop;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class ShopRepositoryImpl implements ShopRepository{

    private final MapperShop mapperShop;
    private final ShopJpa shopJpa;

    @Override
    @Transactional
    public Shop save(Shop shop) {
        ShopEntity shopEntity = mapperShop.mapToShopEntity(shop);
        return mapperShop.mapToShop(shopJpa.save(shopEntity));
    }

    @Override
    public Double getPriceOfGamesWithSpecialOfferForGivenCategory(Boolean specialOffer, String category) {
        return shopJpa.getPriceOfGamesWithSpecialOfferForGivenCategory(specialOffer,category);
    }
}
