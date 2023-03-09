package com.beges.bomjGame.service.abstracts.model;


import com.beges.bomjGame.model.Enemy;
import com.beges.bomjGame.model.User;

public interface ServiceBattleGrounds {


    Enemy getEnemy();

    int attack(User user);

    int parry(User user);

    boolean isWin(Long userId);

     User getUserById(Long userId);

    String escape(User user);

    String endingMessage();
}
