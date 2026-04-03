package test;

import main.GameStat;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameStatTest {

    private GameStat gameStat;

    @BeforeEach
    void setUp() {
        // GameStat требует имя
        gameStat = new GameStat("тестовым словам");
    }

    @Test
    void testChangeStatWin() {
        gameStat.changeStat(true, 3);
        assertEquals(1, gameStat.getCountGame());
        assertEquals(1, gameStat.getCountWinGame());
        assertEquals(3, gameStat.getRecord());
    }

    @Test
    void testChangeStatLose() {
        gameStat.changeStat(false, 5);
        assertEquals(1, gameStat.getCountGame());
        assertEquals(0, gameStat.getCountWinGame());
        assertEquals(5, gameStat.getRecord());
    }

    @Test
    void testGetStat() {
        gameStat.changeStat(true, 3);
        String stat = gameStat.getStat();
        assertTrue(stat.contains("кол-во игр всего: 1"));
        assertTrue(stat.contains("кол-во выиграных игр: 1"));
        assertTrue(stat.contains("рекорд:3"));
    }

    @Test
    void testResetStat() {
        gameStat.changeStat(true, 3);
        gameStat.resetStat();
        assertEquals(0, gameStat.getCountGame());
        assertEquals(0, gameStat.getCountWinGame());
        assertEquals(Integer.MAX_VALUE, gameStat.getRecord());
    }
}