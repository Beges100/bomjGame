package com.beges.bomjGame.service.Impl.model;

import com.beges.bomjGame.model.User;
import com.beges.bomjGame.service.abstracts.model.ServiceBattleGrounds;
import com.beges.bomjGame.service.abstracts.model.ServiceButtons;
import com.beges.bomjGame.service.abstracts.model.ServiceMessage;
import com.beges.bomjGame.service.abstracts.model.UserService;
import lombok.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service
public class ServiceMessageImpl implements ServiceMessage {

    private final ServiceButtons serviceButtons;
    private final UserService userService;
    private final ServiceBattleGrounds serviceBattleGrounds;

    private Long userId;

    @Override
    public void answer() {

    }
    @Override
    public boolean checkUserRegister(Long chatId) {
        return userService.checkUserById(chatId);
    }


    //Кэшировать? А потом отправлять в БД?
    @Override
    public void takeMessage(Update update, SendMessage message) {
        message.enableMarkdown(true);
        message.setChatId(update.getMessage().getChatId());
        Long id = update.getMessage().getChat().getId();
        userId = id;
        if (checkUserRegister(id)) {
            serviceButtons.mainMenu(message);
        } else {
            registerUser(update.getMessage().getChat().getId(),
                    update.getMessage().getChat().getUserName(),
                    update.getMessage().getChat().getFirstName(),
                    false);
            serviceButtons.mainMenu(message);
        }

        action(message, update.getMessage().getText());
    }

    public void registerUser(Long chatId, String userName, String lastName, boolean isPremium) {
        User user = User.builder()
                .chat_id(chatId)
                .nickName(userName)
                .name(lastName)
                .premium(isPremium)
                .fatigue(100L)
                .level(1)
                .build();
        userService.save(user);
    }

    @Override
    public void action(SendMessage message, String textMessage) {

        switch (textMessage) {
            case "Бродить по улице" -> message.setText("Вы бездумно бродите по ночному городу");
            case "Начать игру" -> {
                serviceButtons.fromStreet(message);
                message.setText("Вы вышли на улицу");
            }
            case "Настройки профиля" -> message.setText("Раздел в разработке");
            case "Покопаться в помойке" -> message.setText("Вы находите гнилое яблоко");
            case "В бой" -> {
                message.setText("Выберите противника");
                serviceButtons.fightMenu(message);
            }
            case "Напасть на крысу" -> {
                message.setText(goToBattle());
                //isWin
                serviceButtons.afterFightMenu(message, false);
        }
            case "На главную" -> {
                message.setText("Что-то забыли?");
                serviceButtons.mainMenu(message);
            }
            case "Статистика" -> {
                message.setText("""
                        Вот ваша статистика:
                        Побед: 0
                        Поражений: 0
                        Злейший враг: Влад
                        ХП: 100 EXP: 0
                        """);
                serviceButtons.statistic(message);
            }
            default -> message.setText("Нет такой комманды");
        }
    }


    //TODO Вынести в контроллер
    public String goToBattle() {
        String enemy = serviceBattleGrounds.getEnemy("Влад");
        //user.getAttackPower
        serviceBattleGrounds.attack(10);
        //user.getAgility
        String level = "10";
        serviceBattleGrounds.parry(10);
        boolean isWin = serviceBattleGrounds.isWin(userId);
        String win = "Выйграли";
        String lose = "Проиграли";
        //Коллекция айтемов
        List<String> inventoryItems = new ArrayList<>();
        inventoryItems.add("Бутылка");
        return String.format("Враг: %s, c уровнем " +
                        "%s попался вам и вы %s"  +
                " вы потеряли %s",
                enemy, level,
                isWin ? win : lose,
                inventoryItems.stream().findFirst().get());

    }
}
