package com.beges.bomjGame.model;

import lombok.Getter;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

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
