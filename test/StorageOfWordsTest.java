import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StorageOfWordsTest {

    @Test
    public void testWordsArrayExists() {
        assertNotNull(StorageOfWords.WORDS);
    }

    @Test
    public void testWordsCount() {
        assertEquals(12, StorageOfWords.count_words);
    }

    @Test
    public void testFirstWord() {
        assertEquals("КНИГА", StorageOfWords.WORDS[0]);
    }
}