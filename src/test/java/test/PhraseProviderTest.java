package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import main.PhraseProvider;

public class PhraseProviderTest {
    private PhraseProvider provider;

    @BeforeEach
    void setUp() {
        provider = new PhraseProvider();
    }

    @Test
    void testGetRandomGreeting() {
        String greeting = provider.getRandomGreeting();
        assertNotNull(greeting);
        assertTrue(greeting.contains("/play"));
        assertTrue(greeting.contains("/help"));
    }

    @Test
    void testGetRandomWinPhrase() {
        String word = "ТЕСТ";
        int attempts = 5;
        String winPhrase = provider.getRandomWinPhrase(word, attempts);

        assertNotNull(winPhrase);
        assertTrue(winPhrase.contains(word), "Победная фраза должна содержать загаданное слово");

        assertFalse(winPhrase.isEmpty());
    }

    @Test
    void testGetAttemptPhrase() {
        int attemptNum = 3;
        StringBuilder currentWord = new StringBuilder("Т_С_");
        StringBuilder nearby = new StringBuilder("Е");

        String attemptMsg = provider.getAttemptPhrase(attemptNum, currentWord, nearby);

        assertNotNull(attemptMsg);
        assertTrue(attemptMsg.contains("#3"));
        assertTrue(attemptMsg.contains("Т_С_"));
        assertTrue(attemptMsg.contains("Е"));
    }

    @Test
    void testGetRandomEnding() {
        String ending = provider.getRandomEnding();
        assertNotNull(ending);
        assertTrue(ending.contains("/play"));
    }
}