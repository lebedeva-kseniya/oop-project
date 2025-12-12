package test;

import main.LogicBot;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LogicBotTest {

    @Test
    public void testCanStartGame() {
        LogicBot logicBot = new LogicBot();

        assertTrue(logicBot.canStartGame("/play"));
        assertTrue(logicBot.canStartGame("текст /play текст"));
        assertFalse(logicBot.canStartGame("/help"));
        assertFalse(logicBot.canStartGame("просто текст"));
    }

    @Test
    public void testHandleUserAnswerHelp() {
        LogicBot logicBot = new LogicBot();
        String result = logicBot.handleUserAnswer("/help");

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertTrue(result.contains("УГАДАЙ СЛОВО"));
        assertTrue(result.contains("/play"));
        assertTrue(result.contains("/endgame"));
        assertTrue(result.contains("/help"));
    }

    @Test
    public void testHandleUnknownCommand() {
        LogicBot logicBot = new LogicBot();
        String result = logicBot.handleUserAnswer("неизвестная команда");

        assertEquals("неизвестная команда", result);
    }
}
