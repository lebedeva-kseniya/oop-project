package test;

import main.Bot;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BotTest {

    @Test
    public void testConstructor() {
        Bot bot = new Bot();
        assertNotNull(bot);
    }
}
