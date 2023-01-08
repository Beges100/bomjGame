package com.beges.bomjGame.service.abstracts.model;


public interface ServiceBattleGrounds {

    String getEnemy(String name);
    void attack(int attackPower);
    void parry(int agility);
    boolean isWin(Long userId);
    void escape();
    String endingMessage();
}
