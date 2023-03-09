package com.beges.bomjGame.service.abstracts.model;


import com.beges.bomjGame.dao.Impl.model.EnemyDaoImpl;
import com.beges.bomjGame.dao.abstracts.model.EnemyDao;
import com.beges.bomjGame.dao.abstracts.model.UserDao;
import com.beges.bomjGame.model.Enemy;
import com.beges.bomjGame.model.Items;
import com.beges.bomjGame.model.User;
import com.beges.bomjGame.service.abstracts.repository.ReadOnlyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ServiceBattleGroundsImpl implements ServiceBattleGrounds {


    private List<Enemy> list = new ArrayList<>();

    private final UserDao userDao;
    private final EnemyDao enemyDao;

    @Override
    public User getUserById(Long userId) {
        return userDao.getUserById(userId);
    }
    @Override
    public Enemy getEnemy() {
//        enemyDao.getBytes();
//        list.add(new Enemy(1L, "Vlad", 10, 10, 10,10));
        Enemy enemy = enemyDao.getEnemyById(1L);
//                list.stream().findFirst().get();
        return enemy;
    }

    @Override
    public int attack(User user) {
        return user.getLevel();
    }

    @Override
    public int parry(User user) {
        return user.getLevel();
    }

    //битва здесь?
    //сравнить все характеристики и по ним уже провести битву
    //но должен присутствоть рандом
    //сравнение оружия?
    //HashMap<User, List<Weapons>>????
    @Override
    public boolean isWin(Long userId) {
        User user = getUserById(userId);
        Enemy enemy = list.stream().findFirst().get();
        return user.getLevel() > enemy.getLvl();
    }

    @Override
    public String escape(User user) {
//        user.setHP(-10L);
        return "Вы сбежали";
    }

    @Override
    public String endingMessage() {
        return "Vy proigraly loshara";
    }
}
