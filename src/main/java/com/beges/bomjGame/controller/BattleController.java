package com.beges.bomjGame.controller;

import com.beges.bomjGame.model.Enemy;
import com.beges.bomjGame.model.User;
import com.beges.bomjGame.service.abstracts.model.ServiceBattleGrounds;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BattleController {



    private final ServiceBattleGrounds serviceBattleGrounds;


    //TODO Вынести в контроллер
    public String goToBattle() {
        Enemy enemy = serviceBattleGrounds.getEnemy();
        //user.getAttackPower
        serviceBattleGrounds.attack(new User());
        //user.getAgility
        String level = "10";
        serviceBattleGrounds.parry(new User());
//        boolean isWin = serviceBattleGrounds.isWin(userId);
        String win = "Выйграли";
        String lose = "Проиграли";
        //Коллекция айтемов
        List<String> inventoryItems = new ArrayList<>();
        inventoryItems.add("Бутылка");
        return String.format("Враг: %s, c уровнем " +
                        "%s попался вам и вы %s"  +
                        " вы потеряли %s",
                enemy, level,
//                isWin ? win : lose,
                inventoryItems.stream().findFirst().get());

    }


}
