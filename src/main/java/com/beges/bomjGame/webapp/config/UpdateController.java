package com.beges.bomjGame.webapp.config;

import com.beges.bomjGame.service.abstracts.model.ServiceMessage;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
@RequiredArgsConstructor
public class UpdateController {

    private final ServiceMessage serviceMessage;
    private InitBot initBot;

    public void registerBot(InitBot initBot) {
        this.initBot = initBot;
    }

    public void processUpdate(Update update) throws TelegramApiException {
        if(update.hasMessage()) {
            sendMessage(update);
        }
    }

    public void sendMessage(Update update) throws TelegramApiException {
        SendMessage message = new SendMessage();
        serviceMessage.takeMessage(update, message);
        setView(message);
    }

    public void setView(SendMessage sendMessage) {
        initBot.sendAnswerMessage(sendMessage);
    }

}
