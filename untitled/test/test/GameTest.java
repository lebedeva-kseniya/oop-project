package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import main.Game;
import main.UserStat;

public class GameTest{
    private Game game;
    private UserStat userStat;

    @BeforeEach
    void setUp() {
        userStat = new UserStat();
        game = new Game(userStat);
    }

    @Test
    void testDifficultyAndLengthSetup() {
        assertEquals("Сложность выбрана", game.setDifficultWord("5"));
        assertEquals(5, game.levelWord());

        assertEquals("Введите корректную длину слова", game.setDifficultWord("10"));

        assertEquals("Сложность выбрана", game.setDifficult("1"));
        // Судя по коду, для "1" level = 20
    }

    @Test
    void testGameStartAndFlow() {
        game.setDifficultWord("5");
        game.setDifficult("0");
        game.setModeGame("1");
        String startMsg = game.gameStart();
        if (!startMsg.equals("Слово не может быть загружено, попробуйте снова")) {
            assertFalse(game.isGameEnd());
            assertNotNull(game.Play("СЛОВО"));
        }
    }

    @Test
    void testNormalisation() {
        game.setDifficultWord("5");
        game.setDifficult("0");
        game.gameStart();

        String result = game.gamePlayWord("книга");
        assertFalse(result.contains("Ошибка! Введите слово из 5 русских букв"));
    }

    @Test
    void testHintLimits() {
        game.setDifficultWord("5");
        game.setDifficult("2");
        game.setModeGame("1");
        game.gameStart();

        String hint1 = game.Play("/hint.random");
        assertFalse(hint1.contains("К сожалению подсказки закончились"));

        String hint2 = game.Play("/hint.random");
        assertEquals("К сожалению подсказки закончились", hint2);
    }

    @Test
    void testEndGameCommand() {
        game.setDifficultWord("5");
        game.setDifficult("0");
        game.setModeGame("1");
        game.gameStart();

        String result = game.Play("/endgame");
        assertTrue(game.isGameEnd());
        assertTrue(result.contains("загадано было"));
    }

    @Test
    void testSetModeGame(){
        assertEquals("режим игры выбран", game.setModeGame("1"));
        assertEquals(1, game.getMode());
        assertEquals("Введите корректный режим игры", game.setModeGame("452"));
    }

    @Test
    void testGameWithTime() throws InterruptedException {
        game.setModeGame("2");
        game.gameStart();
        assertFalse(game.timeIsEnd());
    }
}