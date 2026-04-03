package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import main.Hint;

public class HintTest {
    private Hint hint;

    @BeforeEach
    void setUp() {
        hint = new Hint();
    }

    @Test
    void testHintInfo() {
        String info = hint.hint();
        assertNotNull(info);
        assertTrue(info.contains("/hint.random"));
        assertTrue(info.contains("/hint.letter"));
    }

    @Test
    void testHintRandomFindsHiddenLetter() {
        String word = "КНИГА";
        int length = 5;

        boolean[] correctLetters = {false, true, true, true, true};

        String result = hint.hintRandom(correctLetters, word, length);

        assertEquals("К____", result, "Должна открыться единственная закрытая буква");
    }

    @Test
    void testHintRandomAllOpened() {
        String word = "КНИГА";
        boolean[] correctLetters = {true, true, true, true, true};

        String result = hint.hintRandom(correctLetters, word, 5);

        assertEquals("Буквы на всех позициях были отгаданы", result);
    }

    @Test
    void testHintLetterFormat() {
        String word = "РЕЧКА";
        String result = hint.hintLetter(word, 5);

        assertNotNull(result);
        assertTrue(result.startsWith("В слове есть буквы: "));
        assertTrue(result.contains(","));
    }

    @Test
    void testHintLetterDifferentIndices() {
        String word = "ОКО";
        for (int i = 0; i < 10; i++) {
            String result = hint.hintLetter(word, 3);
            String lettersPart = result.substring(result.indexOf(":") + 2);
            String[] parts = lettersPart.split(",");

            assertEquals(2, parts.length, "Должно быть ровно две буквы");
        }
    }
}