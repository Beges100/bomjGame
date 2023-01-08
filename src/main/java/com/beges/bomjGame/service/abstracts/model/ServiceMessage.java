package com.beges.bomjGame.service.abstracts.model;

import com.beges.bomjGame.model.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface ServiceMessage {

    void answer();

    boolean checkUserRegister(User user);

    void takeMessage(Update update, SendMessage message);

    void action(SendMessage message, String textMessage);
}
