package aga.repository;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class RatingId implements Serializable {

    @Column(name = "game_id")
    private Integer gameId;

    @Column(name = "player_id")
    private Integer playerId;

    private RatingId(){}
    public RatingId(Integer gameId, Integer playerId){
        this.gameId = gameId;
        this.playerId = playerId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        RatingId that = (RatingId) o;
        return Objects.equals(gameId, that.gameId) &&
                Objects.equals(playerId, that.playerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameId, playerId);
    }
}
