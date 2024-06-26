package aga.service.service;

import aga.repository.ShopRepository;
import aga.service.domain.Shop;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopRepository shopRepository;

    public Shop addNewRecordToShop(Shop shop) {
        return shopRepository.save(shop);
    }

    public Double getPriceOfGamesWithSpecialOfferForGivenCategory(Boolean specialOffer, String category) {
        return shopRepository.getPriceOfGamesWithSpecialOfferForGivenCategory(specialOffer,category);
    }
}
