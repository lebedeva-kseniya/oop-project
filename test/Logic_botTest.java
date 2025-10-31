import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LogicBotTest {

    @Test
    public void testLogicStartGameWithGameWord() {
        assertTrue(Logic_bot.logic_start_game("игра"));
    }

    @Test
    public void testLogicStartGameWithHowToPlay() {
        assertFalse(Logic_bot.logic_start_game("как играть"));
    }

    @Test
    public void testLogicWorkWithRules() {
        String result = Logic_bot.logic_work("правила");
        assertTrue(result.contains("Компьютер загадывает слово"));
    }

    @Test
    public void testLogicWorkWithUnknownCommand() {
        String result = Logic_bot.logic_work("неизвестная команда");
        assertEquals("неизвестная команда", result);
    }
}