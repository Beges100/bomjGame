package com.beges.bomjGame.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Enemy {

    @Id
    private Long id;
    private String name;
    private Integer hp;
    private Integer lvl;
    private Integer strength;
    private Integer exp;
}
