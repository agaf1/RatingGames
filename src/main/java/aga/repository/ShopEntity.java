package aga.repository;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Objects;

@Entity
@Table(name = "shop")
@Data
public class ShopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="shop_id")
    private Integer shopId;

    private Integer game_id;
    private double price;
    private Boolean specialOffer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    @MapsId("game_id")
    private GameEntity game;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShopEntity that)) return false;
        return shopId != null && Objects.equals(shopId, that.shopId);
    }

    @Override
    public int hashCode() {
        return 13;
    }
}
