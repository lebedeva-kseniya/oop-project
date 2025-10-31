import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StorageTest {

    @Test
    public void testGetWordReturnsString() {
        String word = Storage.get_word();
        assertNotNull(word);
    }

    @Test
    public void testGetWordLength() {
        String word = Storage.get_word();
        assertEquals(5, word.length());
    }

    @Test
    public void testGetWordIsUppercase() {
        String word = Storage.get_word();
        assertEquals(word, word.toUpperCase());
    }
}