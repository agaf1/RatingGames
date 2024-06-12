package aga.service.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class Game {

    private Integer id;
    private String name;
    private String category;
}
