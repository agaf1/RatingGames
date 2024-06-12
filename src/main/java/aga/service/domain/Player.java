package aga.service.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Player {

    private Integer id;
    private String firstName;
    private String lastName;
    private int age;


}
