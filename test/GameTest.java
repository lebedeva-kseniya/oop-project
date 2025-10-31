import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

    public class GameTest {

        @Test
        public void testGameStart() {
            Game game = new Game();
            String result = game.game_start();
            assertTrue(result.contains("Игра началась"));
        }

        @Test
        public void testNormalised() {
            Game game = new Game();
            String result = game.game_play("тест");
            assertNotNull(result);
        }

        @Test
        public void testIsGameEndInitiallyFalse() {
            Game game = new Game();
            assertFalse(game.is_game_end());
        }

        @Test
        public void testInvalidWordLength() {
            Game game = new Game();
            game.game_start(); // начать игру
            String result = game.game_play("abc");
            assertTrue(result.contains("Ошибка"));
        }
    }

