package test;

import main.StorageOfWords;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StorageOfWordsTest {

    @Test
    public void testConstants() {
        assertEquals(12, StorageOfWords.countWords);
        assertNotNull(StorageOfWords.WORDS);
        assertEquals(12, StorageOfWords.WORDS.length);
    }

    @Test
    public void testWordsContent() {
        assertTrue(containsWord("КНИГА"));
        assertTrue(containsWord("РЕЧКА"));
        assertTrue(containsWord("СОКОЛ"));

        for (String word : StorageOfWords.WORDS) {
            assertEquals(word, word.toUpperCase());
        }
    }

    private boolean containsWord(String target) {
        for (String word : StorageOfWords.WORDS) {
            if (word.equals(target)) {
                return true;
            }
        }
        return false;
    }
}