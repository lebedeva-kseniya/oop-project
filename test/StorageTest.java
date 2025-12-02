package test;

import main.Storage;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    @Test
    public void testGetWord5() {
        Storage storage = new Storage();
        String word = storage.getWord5();

        assertNotNull(word);
        assertEquals(5, word.length());

        // Проверяем, что слово из списка
        boolean found = false;
        for (String expected : main.StorageOfWords.WORDS) {
            if (expected.equals(word)) {
                found = true;
                break;
            }
        }
        assertTrue(found, "Слово должно быть из списка WORDS");
    }

    @Test
    public void testGetWord6And7() {
        Storage storage = new Storage();

        String word6 = storage.getWord6();
        String word7 = storage.getWord7();

        if (word6 != null) {
            assertEquals(6, word6.length());
            assertEquals(word6, word6.toUpperCase());
        }

        if (word7 != null) {
            assertEquals(7, word7.length());
            assertEquals(word7, word7.toUpperCase());
        }
    }
}