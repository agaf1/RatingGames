package aga.repository;

import jakarta.persistence.*;
import lombok.Data;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "games")
@Data
public class GameEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="game_id")
    private Integer gameId;
    private String name;
    private String category;


    @OneToMany(mappedBy = "game",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<RatingEntity> ratings = new HashSet<>();

    @OneToMany(mappedBy = "game",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<ShopEntity> shops = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameEntity game)) return false;
        return gameId != null && Objects.equals(gameId, game.gameId);
    }

    @Override
    public int hashCode() {
        return 17;
    }
}
