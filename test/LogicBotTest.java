package test;

import main.LogicBot;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LogicBotTest {

    @Test
    void testCanStartGame() {
        LogicBot logicBot = new LogicBot();

        assertTrue(logicBot.canStartGame("/play"));
        assertTrue(logicBot.canStartGame("начать /play сейчас"));
        assertTrue(logicBot.canStartGame("давай /play игру"));

        assertFalse(logicBot.canStartGame("/help"));
        assertFalse(logicBot.canStartGame("/hint"));
        assertFalse(logicBot.canStartGame("просто текст"));
    }

    @Test
    void testHandleUserAnswerHelp() {
        LogicBot logicBot = new LogicBot();
        String result = logicBot.handleUserAnswer("/help");

        assertTrue(result.contains("УГАДАЙ СЛОВО"));
        assertTrue(result.contains("КОМАНДЫ:"));
        assertTrue(result.contains("/play"));
        assertTrue(result.contains("/hint"));
    }

    @Test
    void testHandleUserAnswerUnknown() {
        LogicBot logicBot = new LogicBot();
        String result = logicBot.handleUserAnswer("неизвестная команда");

        assertEquals("неизвестная команда", result);
    }
}
