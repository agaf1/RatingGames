package aga.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ShopJpa extends CrudRepository<ShopEntity, Integer> {


    @Query(value = """
            Select sum(price)
            from shop join games on shop.game_id = games.game_id
            where games.category = :category AND shop.special_offer = :specialOffer;
            """
            , nativeQuery = true)
    Double getPriceOfGamesWithSpecialOfferForGivenCategory(Boolean specialOffer, String category);
}
