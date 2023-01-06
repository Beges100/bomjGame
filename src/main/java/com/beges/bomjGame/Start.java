package com.beges.bomjGame;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
@NoArgsConstructor
public class Start extends TelegramLongPollingBot {

    @Value("${bot.name}")
    private String botName;
    @Value("${bot.token}")
    private String botToken;


    @Override
    public String getBotUsername() {
        return "game_bomj_bot";
    }

    @Override
    public String getBotToken() {
        return "5711682046:AAHYjV5UOd6mSktZqa8vAq5jzsYS7mZj_Jo";
    }

    @Override
    public void onUpdateReceived(Update update) {
        var message = update.getMessage();
        System.out.println(message.getText());
    }

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        try {
            telegramBotsApi.registerBot(this);
        } catch (TelegramApiException e) {
            System.out.println(e.getMessage());
        }
    }
}
