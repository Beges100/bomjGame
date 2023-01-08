package com.beges.bomjGame.webapp.config;



import com.beges.bomjGame.model.User;
import com.beges.bomjGame.service.abstracts.model.ServiceButtons;
import com.beges.bomjGame.service.abstracts.model.ServiceMessage;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


@Component
@NoArgsConstructor
public class InitBot extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botName;
    @Value("${bot.token}")
    private String botToken;
    private ServiceMessage serviceMessage;

    @Autowired
    public InitBot(ServiceMessage serviceMessage) {
        this.serviceMessage = serviceMessage;
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    @SneakyThrows
    public void onUpdateReceived(Update update) {
        if(update.hasMessage())
            sendMessage(update);
    }


    @EventListener(ContextRefreshedEvent.class)
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }

    public void sendMessage(Update update) throws TelegramApiException {
        SendMessage message = new SendMessage();
        serviceMessage.takeMessage(update,message);

        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        System.out.println(update.getMessage().getText());
    }
}
