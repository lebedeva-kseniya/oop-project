package test;

import main.Hint;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HintTest {

    @Test
    void testHintCreation() {
        Hint hint = new Hint();
        assertNotNull(hint);
    }

    @Test
    void testHintInfo() {
        Hint hint = new Hint();
        String info = hint.hint();

        assertNotNull(info);
        assertTrue(info.contains("Подсказки"));
        assertTrue(info.contains("/hint.random"));
        assertTrue(info.contains("/hint.letter"));
    }

    @Test
    void testHintLetter() {
        Hint hint = new Hint();
        String result = hint.hintLetter("ПРИВЕТ", 6);

        assertNotNull(result);
        assertTrue(result.contains("В слове есть буквы:"));
        assertTrue(result.contains(","));

        String lettersPart = result.substring(result.indexOf(": ") + 2);
        String[] letters = lettersPart.split(",");
        assertEquals(2, letters.length);
        assertEquals(1, letters[0].trim().length());
        assertEquals(1, letters[1].trim().length());
    }

    @Test
    void testHintRandom() {
        Hint hint = new Hint();
        String word = "ПРИВЕТ";
        boolean[] hidden = new boolean[7];

        String result = hint.hintRandom(hidden, word, 6);

        assertNotNull(result);
        assertTrue(result.contains("_"));

        int letterCount = 0;
        for (char c : result.toCharArray()) {
            if (c != '_') {
                letterCount++;
                assertTrue(word.contains(String.valueOf(c)));
            }
        }
        assertEquals(1, letterCount);
    }

    @Test
    void testHintRandomAllShown() {
        Hint hint = new Hint();
        String word = "ПРИВЕТ";
        boolean[] shown = new boolean[7];

        // Все буквы уже показаны
        for (int i = 0; i < 6; i++) {
            shown[i] = true;
        }

        String result = hint.hintRandom(shown, word, 6);
        assertEquals("Буквы на всех позициях были отгаданы", result);
    }
}