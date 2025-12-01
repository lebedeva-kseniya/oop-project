import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LogicBotTest {

    @Test
    public void testLogicStartGameWithGameWord() {
        assertTrue(LogicBot.logic_start_game("игра"));
    }

    @Test
    public void testLogicStartGameWithHowToPlay() {
        assertFalse(LogicBot.logic_start_game("как играть"));
    }

    @Test
    public void testLogicWorkWithRules() {
        String result = LogicBot.logic_work("правила");
        assertTrue(result.contains("Компьютер загадывает слово"));
    }

    @Test
    public void testLogicWorkWithUnknownCommand() {
        String result = LogicBot.logic_work("неизвестная команда");
        assertEquals("неизвестная команда", result);
    }

}
