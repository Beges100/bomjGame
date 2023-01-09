package com.beges.bomjGame.service.Impl.model;

import com.beges.bomjGame.model.Enemy;
import com.beges.bomjGame.model.User;
import com.beges.bomjGame.service.abstracts.model.ServiceBattleGrounds;
import com.beges.bomjGame.service.abstracts.model.ServiceButtons;
import com.beges.bomjGame.service.abstracts.model.ServiceMessage;
import com.beges.bomjGame.service.abstracts.model.UserService;
import lombok.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Service()
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
        userId = update.getMessage().getChat().getId();

        if (checkUserRegister(userId)) {
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
                goToBattle(message);
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
            default -> message.setText("Нет такой команды");
        }
    }


    //TODO Вынести в контроллер
    @SneakyThrows
    public void goToBattle(SendMessage sendMessage) {
        Enemy enemy = serviceBattleGrounds.getEnemy();
        boolean isWin = serviceBattleGrounds.isWin(userId);
        //Коллекция айтемов
        List<String> inventoryItems = new ArrayList<>();
        inventoryItems.add("Бутылка");
        String random = "random";
        String result = String.format("Вы начинаете бой с %s" +
                " вы %s %s %s и %s, он в ответ %s , вы %s", enemy.getName(),
                actions().stream().findFirst().get(), place().stream().findFirst().get(),
                item().stream().findFirst().get() , random,
                random, isWin ? "Выйграли" : "Проиграли");
        sendMessage.setText(result);
    }

    public List<String> actions() {
        List<String> action = new ArrayList<>();
        action.add("берете");
        action.add("находите");
        return action;
    }
    public List<String> place() {
        List<String> place = new ArrayList<>();
        place.add("из помойки");
        place.add("в кармане");
        return place;
    }

    public List<String> item() {
        List<String> items = new ArrayList<>();
        items.add("огрызок яблока");
        return items;
    }


}
