package com.beges.bomjGame.service.Impl.model;

import com.beges.bomjGame.model.DTO.ChatUserDTO;
import com.beges.bomjGame.model.Enemy;
import com.beges.bomjGame.model.Mappers.UserMapper;
import com.beges.bomjGame.model.User;
import com.beges.bomjGame.service.abstracts.model.ServiceBattleGrounds;
import com.beges.bomjGame.service.abstracts.model.ServiceButtons;
import com.beges.bomjGame.service.abstracts.model.ServiceMessage;
import com.beges.bomjGame.service.abstracts.model.UserService;
import lombok.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@RequiredArgsConstructor
@Service
public class ServiceMessageImpl implements ServiceMessage {
    private final ServiceButtons serviceButtons;
    private final UserService userService;
    private final ServiceBattleGrounds serviceBattleGrounds;

    private ChatUserDTO userDto;

    private final UserMapper userMapper;


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

        userDto = ChatUserDTO.builder()
                .id(update.getMessage().getChat().getId())
                .firstName(update.getMessage().getChat().getFirstName())
                .userName(update.getMessage().getChat().getUserName())
                .isPremium(false)
                .build();

        serviceButtons.mainMenu(message);

        action(message, update.getMessage().getText());
    }

    public void registerUser(ChatUserDTO userDTO) {

        User user = userMapper.toUser(userDTO);

        userService.save(user);
    }

    @Transactional
    @Override
    public void action(SendMessage message, String textMessage) {

        switch (textMessage) {
            case "Бродить по улице" -> message.setText("Вы бездумно бродите по ночному городу");
            case "Начать игру" -> {
                if (checkUserRegister(userDto.getId())) {
                    serviceButtons.fromStreet(message);
                    message.setText("Вы вышли на улицу");
                } else {
                    registerUser(userDto);
                    message.setText("Вы успешно зарегистрированны");
                    serviceButtons.fromStreet(message);
                }
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
                        Злейший враг: Rat
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
        int enemyHealth = enemy.getHp();
        int enemyAttack = enemy.getStrength();

        // Инициализация игрока с некоторым здоровьем и силой атаки
        int playerHealth = 20;
        int playerAttack = 20;

        StringBuilder battleLog = new StringBuilder();
        battleLog.append(String.format("Вы начинаете бой с %s\n", enemy.getName()));

        boolean isPlayerTurn = true; // Определяет, чей ход сейчас

        while (playerHealth > 0 && enemyHealth > 0) {
            // Определяем атакующего и защищающегося
            int attackerAttack = isPlayerTurn ? playerAttack : enemyAttack;
            int defenderHealth = isPlayerTurn ? enemyHealth : playerHealth;

            // Вычисляем урон
            int damage = calculateDamage(attackerAttack, 2);
            if (damage == 0) {
                battleLog.append("Уклонение!\n");
            } else {
                battleLog.append(String.format("%s атакует и наносит %d урона!\n", isPlayerTurn ? "Вы" : enemy.getName(), damage));
                if (isPlayerTurn) {
                    enemyHealth -= damage;
                } else {
                    playerHealth -= damage;
                }
            }

            // Смена хода
            isPlayerTurn = !isPlayerTurn;
        }

        boolean isWin = playerHealth > 0;
        battleLog.append(isWin ? "Вы победили!" : "Вы проиграли...");
        battleLog.append(isWin ? "Вы получили " + enemy.getExp() + " опыта" : "");

        sendMessage.setText(battleLog.toString());
    }

    private int calculateDamage(int attack, int defense) {
        Random random = new Random();
        int minDamage = (int) Math.floor(attack * 0.5);
        int maxDamage = (int) Math.floor(attack * 1.5);
        int damage = random.nextInt(maxDamage - minDamage + 1) + minDamage;
        int finalDamage = damage - defense;
        if (finalDamage < 0) {
            finalDamage = 0;
        }
        // 10% вероятности уклонения
        if (random.nextInt(100) < 10) {
            finalDamage = 0;
        }
        return finalDamage;
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
