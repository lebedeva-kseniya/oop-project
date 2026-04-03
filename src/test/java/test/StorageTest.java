package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import main.Storage;

public class StorageTest {
    private Storage storage;

    @BeforeEach
    void setUp() {
        storage = new Storage();
    }

    @Test
    void testGetWord5() {

        for (int i = 0; i < 10; i++) {
            String word = storage.getWord5();
            assertNotNull(word, "Слово из локального хранилища не должно быть null");
            assertEquals(5, word.length(), "Длина слова должна быть 5");

            assertTrue(word.matches("[А-ЯЁ]+"), "Слово должно содержать только русские буквы в верхнем регистре");
        }
    }

    @Test
    void testGetWordFromWebFormat() {

        String word6 = storage.getWordFromWeb(6);

        if (word6 != null) {
            assertEquals(6, word6.length(), "Слово из веба должно быть длиной 6");
            assertTrue(word6.matches("[А-ЯЁ]+"), "Слово из веба должно быть в верхнем регистре и на кириллице");
        } else {
            System.out.println("Warning: Web word (6) returned null. Check internet connection.");
        }
    }

    @Test
    void testGetWord7FromWeb() {
        String word7 = storage.getWordFromWeb(7);

        if (word7 != null) {
            assertEquals(7, word7.length(), "Слово из веба должно быть длиной 7");
        }
    }

    @Test
    void testInvalidLengthInWeb() {

        String result = storage.getWordFromWeb(100);

    }
}