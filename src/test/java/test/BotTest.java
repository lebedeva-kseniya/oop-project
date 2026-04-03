package test;

import main.Bot;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class BotTest {

    private Bot bot;

    @BeforeEach
    void setUp() {
        // Теперь Bot требует токен и username
        bot = new Bot("test-token", "test-bot");
    }

    @Test
    void testBotCreation() {
        assertNotNull(bot);
        assertEquals("test-bot", bot.getBotUsername());
        assertEquals("test-token", bot.getBotToken());
    }
}