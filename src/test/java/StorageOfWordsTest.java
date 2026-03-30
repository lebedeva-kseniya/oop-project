import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StorageOfWordsTest {

    @Test
    void testConstantsNotNull() {
        assertNotNull(StorageOfWords.WORDS, "Массив слов не должен быть null");
    }

    @Test
    void testCountWordsMatchLength() {
        assertEquals(StorageOfWords.WORDS.length, StorageOfWords.countWords,
                "Переменная countWords должна соответствовать длине массива WORDS");
    }

    @Test
    void testWordsContent() {
        boolean foundKniga = false;
        boolean foundSosna = false;

        for (String word : StorageOfWords.WORDS) {
            if ("КНИГА".equals(word)) foundKniga = true;
            if ("СОСНА".equals(word)) foundSosna = true;

            assertEquals(word.toUpperCase(), word, "Все слова в хранилище должны быть в верхнем регистре");
        }

        assertTrue(foundKniga, "Слово КНИГА должно быть в массиве");
        assertTrue(foundSosna, "Слово СОСНА должно быть в массиве");
    }

    @Test
    void testMinimalArraySize() {
        assertTrue(StorageOfWords.countWords >= 10, "В хранилище должно быть как минимум 10 слов");
    }
}