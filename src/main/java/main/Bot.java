package main;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.concurrent.ConcurrentHashMap;

public class Bot extends TelegramLongPollingBot {
    private final String botToken;
    private final String botUsername;
    private final ConcurrentHashMap<Long, GameSession> gameSessions;
    private final Console console;
    private final PhraseProvider provider;

    public Bot(String botToken, String botUsername) {
        this.botToken = botToken;
        this.botUsername = botUsername;
        this.gameSessions = new ConcurrentHashMap<>();
        this.console = new Console();
        this.provider = new PhraseProvider();
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Long chatId = update.getMessage().getChatId();
            String messageText = update.getMessage().getText();

            GameSession session = gameSessions.computeIfAbsent(chatId,
                    k -> new GameSession(new UserStat(), new Console()));

            String response = session.processInput(messageText);
            sendMessage(chatId, response);

            if (session.isGameJustEnded()) {
                session.getUserStat().save();
            }
        }
    }

    private void sendMessage(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId.toString());
        message.setText(text);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}