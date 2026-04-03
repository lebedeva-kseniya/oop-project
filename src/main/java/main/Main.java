package main;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        System.setProperty("console.encoding", "UTF-8");

        if (args.length > 0 && args[0].equals("console")) {
            BotConsole botConsole = new BotConsole();
            botConsole.botStart();
        } else {
            String token = System.getenv("TELEGRAM_BOT_TOKEN");
            String username = System.getenv("TELEGRAM_BOT_USERNAME");

            if (token == null || username == null) {
                Properties props = new Properties();
                try (InputStream input = new FileInputStream("config.properties")) {
                    props.load(input);
                    token = props.getProperty("bot.token");
                    username = props.getProperty("bot.username");
                    if (username == null) {
                        username = "WordGameBot";
                    }
                } catch (IOException e) {
                    System.err.println("Не удалось загрузить конфигурацию. " +
                            "Установите переменные окружения TELEGRAM_BOT_TOKEN и TELEGRAM_BOT_USERNAME " +
                            "или создайте файл config.properties");
                    System.exit(1);
                }
            }

            if (token == null || token.isEmpty()) {
                System.err.println("Токен бота не найден!");
                System.exit(1);
            }

            try {
                TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
                Bot bot = new Bot(token, username);
                botsApi.registerBot(bot);
                System.out.println("Бот успешно запущен!");
                System.out.println("Имя бота: @" + username);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}