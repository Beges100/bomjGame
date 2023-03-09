package com.beges.bomjGame.model;


import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemCharacteristics {

    @Id
    private Long id;
    private String agility;

}
