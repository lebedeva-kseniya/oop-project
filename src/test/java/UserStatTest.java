import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserStatTest {
    private UserStat userStat;

    @BeforeEach
    void setUp() {
        userStat = new UserStat();
    }

    @Test
    void testGameEndUpdatesSpecificAndAll() {
        userStat.gameEnd(5, true, 3);

        String stat5 = userStat.seeStat(5);
        String statAll = userStat.seeStat(0);
        String stat6 = userStat.seeStat(6);

        assertTrue(stat5.contains("кол-во игр всего: 1"));
        assertTrue(stat5.contains("кол-во выиграных игр: 1"));

        assertTrue(statAll.contains("кол-во игр всего: 1"));

        assertTrue(stat6.contains("кол-во игр всего: 0"));
    }

    @Test
    void testResetStat() {
        userStat.gameEnd(7, true, 10);

        String resetMessage = userStat.reset(7);

        assertTrue(resetMessage.contains("статистика по словам из 7 букв сброшена"));

        String stat7 = userStat.seeStat(7);
        assertTrue(stat7.contains("кол-во игр всего: 0"), "После сброса счетчик игр должен быть 0");
    }

    @Test
    void testMultipleGamesStat() {
        userStat.gameEnd(5, true, 5);
        userStat.gameEnd(6, false, 10);

        String allStat = userStat.seeStat(0);

        assertTrue(allStat.contains("кол-во игр всего: 2"));
        assertTrue(allStat.contains("кол-во выиграных игр: 1"));
    }

    @Test
    void testInvalidStatType() {
        String result = userStat.seeStat(99);
        assertEquals("", result, "Для неизвестного типа должна возвращаться пустая строка");
    }
}