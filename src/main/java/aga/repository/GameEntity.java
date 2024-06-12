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
    private Integer id;
    private String name;
    private String category;
    @OneToMany(mappedBy = "game",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<RatingEntity> ratings = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameEntity game)) return false;
        return id != null && Objects.equals(id, game.id);
    }

    @Override
    public int hashCode() {
        return 17;
    }
}
