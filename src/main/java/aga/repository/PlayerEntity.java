package aga.repository;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "players")
@Data
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private int age;

    @OneToMany(mappedBy = "player",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<RatingEntity> ratings = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerEntity player)) return false;
        return id != null && Objects.equals(id, player.id);
    }

    @Override
    public int hashCode() {
        return 15;
    }
}
