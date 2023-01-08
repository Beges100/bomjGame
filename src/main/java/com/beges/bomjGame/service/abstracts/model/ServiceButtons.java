package com.beges.bomjGame.service.abstracts.model;

import com.beges.bomjGame.model.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface ServiceButtons {


    void mainMenu(SendMessage sendMessage);

    void fightMenu(SendMessage sendMessage);

    void backPackMenu(SendMessage sendMessage);

    void equipMenu(SendMessage sendMessage);

    void shopMenu(SendMessage sendMessage);

    void trashMenu(SendMessage sendMessage);

    void configUser(SendMessage sendMessage);

    void fromStreet(SendMessage sendMessage);

    void statistic(SendMessage sendMessage);
}
