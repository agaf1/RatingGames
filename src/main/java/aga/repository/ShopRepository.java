package aga.repository;

import aga.service.domain.Shop;

public interface ShopRepository {

    Shop save(Shop shop);

    Double getPriceOfGamesWithSpecialOfferForGivenCategory(Boolean specialOffer, String category);
}
