package com.beges.bomjGame.model;

import lombok.Getter;
import javax.persistence.*;
import java.util.List;

@Entity
@Getter
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany
    private List<ItemCharacteristics> itemCharacteristics;

}
