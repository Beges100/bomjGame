package com.beges.bomjGame.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="user_table")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    private Long chat_id;

    @Column(name = "nick_name")
    private String nickName;

    private String name;

    private Boolean premium;
    //Усталость
    private Long fatigue;

    private Integer level;
}
