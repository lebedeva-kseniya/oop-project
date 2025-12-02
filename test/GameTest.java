package test;

import main.Game;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    @Test
    public void testGameConstructor() {
        Game game = new Game();
        assertFalse(game.isGameEnd());
        assertEquals(0, game.level());
    }

    @Test
    public void testSetDifficulty() {
        Game game = new Game();

        assertEquals("Сложность выбрана", game.setDifficult("5"));
        assertEquals(5, game.level());

        assertEquals("Сложность выбрана", game.setDifficult("6"));
        assertEquals(6, game.level());

        assertEquals("Сложность выбрана", game.setDifficult("7"));
        assertEquals(7, game.level());

        assertEquals("Введите корректную длину слова", game.setDifficult("8"));
    }

    @Test
    public void testGameStart() {
        Game game = new Game();
        game.setDifficult("5");
        String result = game.gameStart();

        assertTrue(result.equals("Игра началась!") || result.contains("не может"));
        assertFalse(game.isGameEnd());
    }
}
