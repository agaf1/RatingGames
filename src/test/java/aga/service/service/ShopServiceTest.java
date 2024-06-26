package aga.service.service;

import aga.repository.GameRepository;
import aga.service.domain.Game;
import aga.service.domain.Shop;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql("/clean-db.sql")
class ShopServiceTest {

    @Autowired
    private ShopService shopService;
    @Autowired
    private GameRepository gameRepository;

    @Test
    public void should_save_new_record_to_shop_table(){
        //given
        Game savedGame = gameRepository.save(Game.builder().name("name").category("category").build());
        Shop shop = Shop.builder().price(12.50).specialOffer(true).gameId(savedGame.getId()).build();
        //when
        Shop savedShop = shopService.addNewRecordToShop(shop);
        //then
        assertThat(savedShop.getShopId()).isNotNull();
        assertThat(savedShop.getGameId()).isEqualTo(savedGame.getId());
    }

    @Test
    public void should_get_sum_of_price_from_games(){
        //given
        Game savedGame1 = gameRepository.save(Game.builder().name("name1").category("logiczna").build());
        Game savedGame2 = gameRepository.save(Game.builder().name("name2").category("logiczna").build());
        Game savedGame3 = gameRepository.save(Game.builder().name("name3").category("familijna").build());
        Shop shop1 = Shop.builder().price(12.50).specialOffer(true).gameId(savedGame1.getId()).build();
        Shop shop2 = Shop.builder().price(15.0).specialOffer(false).gameId(savedGame1.getId()).build();
        Shop shop3 = Shop.builder().price(5.50).specialOffer(true).gameId(savedGame2.getId()).build();
        Shop shop4 = Shop.builder().price(10.0).specialOffer(true).gameId(savedGame3.getId()).build();
        shopService.addNewRecordToShop(shop1);
        shopService.addNewRecordToShop(shop2);
        shopService.addNewRecordToShop(shop3);
        shopService.addNewRecordToShop(shop4);
        //when
        Double result = shopService.getPriceOfGamesWithSpecialOfferForGivenCategory(true,"logiczna");
        //then
        assertThat(result).isEqualTo(18.0);
    }

}