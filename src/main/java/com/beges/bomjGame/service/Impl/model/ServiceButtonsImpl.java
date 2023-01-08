package com.beges.bomjGame.service.Impl.model;

import com.beges.bomjGame.dao.abstracts.model.UserDao;
import com.beges.bomjGame.model.User;
import com.beges.bomjGame.service.abstracts.model.ServiceButtons;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class ServiceButtonsImpl implements ServiceButtons {
    private ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    private List<KeyboardRow> keyboard = new ArrayList<>();

    private UserDao userDao;
    @Override
    public void mainMenu(SendMessage sendMessage) {
            // Создаем клавиуатуру

            sendMessage.setReplyMarkup(replyKeyboardMarkup);
            replyKeyboardMarkup.setSelective(true);
            replyKeyboardMarkup.setResizeKeyboard(true);
            replyKeyboardMarkup.setOneTimeKeyboard(false);
            keyboard.clear();
            // Создаем список строк клавиатуры

            // Первая строчка клавиатуры
            KeyboardRow keyboardFirstRow = new KeyboardRow();
            // Добавляем кнопки в первую строчку клавиатуры
            keyboardFirstRow.add(new KeyboardButton("Настройки профиля"));

            // Вторая строчка клавиатуры
            KeyboardRow keyboardSecondRow = new KeyboardRow();
            // Добавляем кнопки во вторую строчку клавиатуры
            keyboardSecondRow.add(new KeyboardButton("Начать игру"));

            // Добавляем все строчки клавиатуры в список
            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecondRow);
            // и устанваливаем этот список нашей клавиатуре
            replyKeyboardMarkup.setKeyboard(keyboard);
    }




    @Override
    public void fightMenu(SendMessage sendMessage) {

        keyboard.clear();
        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add(new KeyboardButton("Напасть на крысу"));

        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add(new KeyboardButton("На главную"));


        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
    }



    @Override
    public void fromStreet(SendMessage sendMessage) {


        keyboard.clear();
        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add(new KeyboardButton("Бродить по улице"));

        // Вторая строчка клавиатуры
        KeyboardRow keyboardSecondRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardSecondRow.add(new KeyboardButton("В бой"));

        // Вторая строчка клавиатуры
        KeyboardRow keyboardFourRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardFourRow.add(new KeyboardButton("Статистика"));

        // Вторая строчка клавиатуры
        KeyboardRow keyboardThreeRow = new KeyboardRow();
        // Добавляем кнопки во вторую строчку клавиатуры
        keyboardThreeRow.add(new KeyboardButton("На главную"));

        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);
        keyboard.add(keyboardSecondRow);
        keyboard.add(keyboardThreeRow);
        keyboard.add(keyboardFourRow);
        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    @Override
    public void statistic(SendMessage sendMessage) {
        keyboard.clear();
        // Первая строчка клавиатуры
        KeyboardRow keyboardFirstRow = new KeyboardRow();
        // Добавляем кнопки в первую строчку клавиатуры
        keyboardFirstRow.add(new KeyboardButton("На главную"));


        // Добавляем все строчки клавиатуры в список
        keyboard.add(keyboardFirstRow);

        // и устанваливаем этот список нашей клавиатуре
        replyKeyboardMarkup.setKeyboard(keyboard);
    }

    @Override
    public void afterFightMenu(SendMessage sendMessage,boolean isWin) {
        keyboard.clear();
        if (isWin) {
            KeyboardRow keyboardFirstRow = new KeyboardRow();
            KeyboardRow keyboardSecond = new KeyboardRow();
            // Добавляем кнопки в первую строчку клавиатуры
            keyboardFirstRow.add(new KeyboardButton("Пойти копаться в мусорке"));
            keyboardSecond.add(new KeyboardButton("На главную"));
            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecond);
        } else {
            KeyboardRow keyboardFirstRow = new KeyboardRow();
            KeyboardRow keyboardSecond = new KeyboardRow();
            // Добавляем кнопки в первую строчку клавиатуры
            keyboardFirstRow.add(new KeyboardButton("Покушоть"));
            keyboardSecond.add(new KeyboardButton("На главную"));
            keyboard.add(keyboardFirstRow);
            keyboard.add(keyboardSecond);
        }

    }

    @Override
    public void backPackMenu(SendMessage sendMessage) {

    }

    @Override
    public void equipMenu(SendMessage sendMessage) {

    }

    @Override
    public void shopMenu(SendMessage sendMessage) {

    }

    @Override
    public void trashMenu(SendMessage sendMessage) {

    }

    @Override
    public void configUser(SendMessage sendMessage) {

    }
}
