package com.beges.bomjGame.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;

@Embeddable
public class CharactericticHero {


    private Integer hp;
    private Integer luck;
    private Integer strength;

}
