package test;

import main.Bot;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BotTest {

    @Test
    void testBotCreation() {
        Bot bot = new Bot();
        assertNotNull(bot);
    }

    @Test
    void testBotClassStructure() {
        try {
            Class<?> botClass = Class.forName("main.Bot");
            assertNotNull(botClass);

            var constructor = botClass.getConstructor();
            assertNotNull(constructor);

            var method = botClass.getMethod("botStart");
            assertNotNull(method);

        } catch (Exception e) {
            fail("Проблема с классом Bot: " + e.getMessage());
        }
    }
}
