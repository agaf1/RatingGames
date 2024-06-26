package aga.service.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Shop {

    private Integer shopId;
    private Integer gameId;
    private double price;
    private Boolean specialOffer;
}
