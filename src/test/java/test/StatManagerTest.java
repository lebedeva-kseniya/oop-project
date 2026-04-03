package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;
import main.StatManager;
import main.UserStat;

public class StatManagerTest {
    private StatManager statManager;
    private final String TEST_FILE = "stat.dat";

    @BeforeEach
    void setUp() {
        statManager = new StatManager();
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @AfterEach
    void tearDown() {
        File file = new File(TEST_FILE);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    void testSaveAndLoad() {
        UserStat originalStat = new UserStat();
        originalStat.finishGame(5, true, 3);

        statManager.save(originalStat);
        assertTrue(statManager.statExist(), "Файл должен существовать после сохранения");

        UserStat loadedStat = statManager.load();
        assertNotNull(loadedStat);

        assertEquals(originalStat.seeStat(5), loadedStat.seeStat(5),
                "Загруженная статистика должна совпадать с сохраненной");
    }

    @Test
    void testLoadWithoutFile() {
        assertFalse(statManager.statExist());
        UserStat newStat = statManager.load();

        assertNotNull(newStat, "Если файла нет, должен возвращаться новый объект UserStat");
        assertTrue(newStat.seeStat(0).contains("кол-во игр всего: 0"),
                "Новая статистика должна быть пустой");
    }

    @Test
    void testStatExist() {
        assertFalse(statManager.statExist());

        statManager.save(new UserStat());
        assertTrue(statManager.statExist());
    }
}
