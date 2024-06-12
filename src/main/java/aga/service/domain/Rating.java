package aga.service.domain;

import aga.repository.RatingId;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Rating {

    private String state;
    private int rating;

    private int gameId;
    private int playerId;


}
