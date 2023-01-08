package com.beges.bomjGame.service.abstracts.model;


import com.beges.bomjGame.dao.abstracts.model.UserDao;
import com.beges.bomjGame.model.Enemy;
import com.beges.bomjGame.model.User;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service

public class ServiceBattleGroundsImpl implements ServiceBattleGrounds {

    private List<Enemy> list = new ArrayList<>();

    private final UserDao userDao;

    @Override
    public String getEnemy(String name) {
        list.add(new Enemy(1L, "Vlad", 10, 10, 10,10));
        Enemy enemy = list.stream().findFirst().get();
        return enemy.getName();
    }

    @Override
    public void attack(int attackPower) {
//        User user = userDao.getUser();
//        user.getAttackPower();
        System.out.println(attackPower);
    }

    @Override
    public void parry(int agility) {
        System.out.println("agility: " + agility);
    }

    @Override
    public boolean isWin(Long userId) {
        User user = userDao.getUserById(userId);
        Enemy enemy = list.stream().findFirst().get();
        return user.getLevel() > enemy.getLvl();
    }

    @Override
    public void escape() {

    }

    @Override
    public String endingMessage() {
        return "Vy proigraly loshara";
    }
}
