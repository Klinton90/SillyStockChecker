package com.example.sillystockchecker.system;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.request.GetUpdates;
import com.pengrad.telegrambot.request.SendMessage;
import com.pengrad.telegrambot.response.GetUpdatesResponse;
import com.pengrad.telegrambot.response.SendResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
public class BotService {

    private final TelegramBot bot = new TelegramBot("1748128490:AAHsnxa-m_Rzf_QyGM2O3bUXZkTwcOS93qQ");

    private final Set<Object> chats = new HashSet<>();

    public void sendMessages() {
        try {
            log.info("Start sending messages");
            chats.forEach(this::sendMessage);
            log.info("Finish sending messages");
        } catch (Exception ex) {
            log.info("Exception in BotService. Trying to get some details:" + ex.getMessage());
        }
    }

    private void sendMessage(Object chatId) {
        SendMessage request = new SendMessage(chatId, "Alarm! Looks like bike is available");
        SendResponse response = bot.execute(request);
        boolean ok = response.isOk();
        Message message = response.message();
        log.info("Message to bot is sent. IsOk=" + ok + ". Resulting message is:" + message);
    }

    @Scheduled(fixedDelay = 1000*60*60)
    public void getUpdates() {
        GetUpdates getUpdates = new GetUpdates()
                .limit(100)
                .offset(0)
                .timeout(0);

        bot.execute(getUpdates)
                .updates()
                .forEach(update -> {
                    Long chatId = update.message().chat().id();
                    log.info("Added new chat listener:" + chatId);
                    chats.add(chatId);
                });
    }
}
