import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LogicBotTest {
    private LogicBot logicBot;
    private UserStat userStat;

    @BeforeEach
    void setUp() {
        userStat = new UserStat();
        logicBot = new LogicBot(userStat);
    }

    @Test
    void testCanStartGame() {
        assertTrue(logicBot.canStartGame("/play"));
        assertTrue(logicBot.canStartGame("хочу играть /play сейчас"));
        assertFalse(logicBot.canStartGame("/help"));
    }

    @Test
    void testHandleHelp() {
        String response = logicBot.handleUserAnswer("/help");
        assertTrue(response.contains("УГАДАЙ СЛОВО"));
        assertTrue(response.contains("/play - начать"));
    }

    @Test
    void testUnknownCommand() {
        String response = logicBot.handleUserAnswer("какое-то сообщение");
        assertEquals("неизвестная команда", response);
    }

    @Test
    void testSeeStatRouting() {
        String response = logicBot.handleUserAnswer("/seeStat5");
        assertNotEquals("неизвестная команда", response);

        String responseAll = logicBot.handleUserAnswer("/seeStatAll");
        assertNotEquals("неизвестная команда", responseAll);
    }

    @Test
    void testResetStatRouting() {
        String response = logicBot.handleUserAnswer("/resetStat7");
        assertNotEquals("неизвестная команда", response);
    }
}