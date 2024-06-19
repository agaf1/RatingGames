package aga.repository;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Table(name = "ratings")
@NoArgsConstructor
@Getter
@Setter
public class RatingEntity {

    @EmbeddedId
    private RatingId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("gameId")
    private GameEntity game;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("playerId")
    private PlayerEntity player;

    private String state;
    private int rating;

//    public RatingEntity(GameEntity game, PlayerEntity player){
//        this.game = game;
//        this.player = player;
//        this.id = new RatingId(game.getId(),player.getId());
//    }

    void setGameAndPlayer(GameEntity gameEntity, PlayerEntity playerEntity) {
        this.game = gameEntity;
        this.player = playerEntity;
        this.id =  new RatingId(gameEntity.getId(), playerEntity.getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        RatingEntity that = (RatingEntity) o;
        return Objects.equals(game, that.game) &&
                Objects.equals(player, that.player);
    }

    @Override
    public int hashCode() {
        return 15;
    }

}
