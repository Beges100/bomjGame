package com.beges.bomjGame.service.Impl.model;

import com.beges.bomjGame.model.User;
import com.beges.bomjGame.service.abstracts.model.ServiceButtons;
import com.beges.bomjGame.service.abstracts.model.ServiceMessage;
import com.beges.bomjGame.service.abstracts.model.UserService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Service
@NoArgsConstructor
@Getter
@Setter
public class ServiceMessageImpl implements ServiceMessage {

    private ServiceButtons serviceButtons;
    private UserService userService;

    @Autowired
    public ServiceMessageImpl(ServiceButtons serviceButtons,UserService userService) {
        this.serviceButtons = serviceButtons;
        this.userService = userService;
    }

    @Override
    public void answer() {

    }

    @Override
    public boolean checkUserRegister(User user) {
        return userService.checkUserById(user.getChat_id());
    }

    @Override
    public void takeMessage(Update update, SendMessage message) {
        message.enableMarkdown(true);
        message.setChatId(update.getMessage().getChatId());
        User user = User.builder()
                .chat_id(update.getMessage().getChat().getId())
                .nickName(update.getMessage().getChat().getUserName())
                .name(update.getMessage().getChat().getFirstName())
                .premium(false)
                .fatigue(100L)
                .build();

        if (checkUserRegister(user)) {
            serviceButtons.mainMenu(message);
        } else {
            userService.save(user);
        }
        serviceButtons.mainMenu(message);
        action(message,update.getMessage().getText());
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
            case "Напасть на крысу" -> message.setText("Вы яростно сражались с крысой, она победила");
            case "На главную" -> {
                message.setText("Что-то забыли?");
                serviceButtons.mainMenu(message);
            }
            case "Статистика" -> {
                message.setText("""
                        Вот ваша статистика:
                        Побед: 0
                        Поражений: 0
                        Злейший враг: Крыса
                        ХП: 100 EXP: 0
                        """);
                serviceButtons.statistic(message);
            }
            default -> message.setText("Нет такой комманды");
        }

    }
}
