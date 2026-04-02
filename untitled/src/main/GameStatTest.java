package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import main.GameStat;

public class GameStatTest {
    private GameStat gameStat;

    @BeforeEach
    void setUp() {
        gameStat = new GameStat();
    }

    @Test
    void testInitialValues() {
        assertEquals(0, gameStat.getCountGame());
        assertEquals(0, gameStat.getCountWinGame());
        assertEquals(Integer.MAX_VALUE, gameStat.getRecord());
    }

    @Test
    void testChangeStatWin() {
        // Первая игра: победа за 10 ходов
        gameStat.changeStat(true, 10);
        assertEquals(1, gameStat.getCountGame());
        assertEquals(1, gameStat.getCountWinGame());
        assertEquals(10, gameStat.getRecord());

        gameStat.changeStat(true, 5);
        assertEquals(5, gameStat.getRecord(), "Рекорд должен обновиться на меньшее число");
    }

    @Test
    void testChangeStatLoss() {
        // Поражение
        gameStat.changeStat(false, 20);
        assertEquals(1, gameStat.getCountGame());
        assertEquals(0, gameStat.getCountWinGame());
        assertEquals(20, gameStat.getRecord());
    }

    @Test
    void testSubtractionLogic() {
        gameStat.changeStat(true, 5);

        gameStat.changeStat(1, 1, 5);

        assertEquals(0, gameStat.getCountGame());
        assertEquals(0, gameStat.getCountWinGame());
        assertEquals(Integer.MAX_VALUE, gameStat.getRecord(), "Рекорд должен сброситься, если вычитаемый рекорд равен текущему");
    }

    @Test
    void testResetStat() {
        gameStat.changeStat(true, 5);
        gameStat.resetStat();

        assertEquals(0, gameStat.getCountGame());
        assertEquals(Integer.MAX_VALUE, gameStat.getRecord());
    }

    @Test
    void testGetStatFormat() {
        gameStat.changeStat(true, 3);
        String output = gameStat.getStat();

        assertTrue(output.contains("кол-во игр всего: 1"));
        assertTrue(output.contains("рекорд:3"));
    }
}