package test;

import main.PhraseProvider;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PhraseProviderTest {

    @Test
    public void testConstructor() {
        PhraseProvider provider = new PhraseProvider();
        assertNotNull(provider);
    }

    @Test
    public void testGetRandomGreeting() {
        PhraseProvider provider = new PhraseProvider();
        String greeting = provider.getRandomGreeting();

        assertNotNull(greeting);
        assertFalse(greeting.isEmpty());
        assertTrue(greeting.contains("/play"));
    }

    @Test
    public void testGetRandomWinPhrase() {
        PhraseProvider provider = new PhraseProvider();
        String winPhrase = provider.getRandomWinPhrase("ТЕСТ", 3);

        assertNotNull(winPhrase);
        assertTrue(winPhrase.contains("ТЕСТ"));
        assertTrue(winPhrase.contains("3"));
        assertTrue(winPhrase.contains("/play"));
    }

    @Test
    public void testGetRandomEnding() {
        PhraseProvider provider = new PhraseProvider();
        String ending = provider.getRandomEnding();

        assertNotNull(ending);
        assertFalse(ending.isEmpty());
        assertTrue(ending.contains("/play"));
    }

    @Test
    public void testGetAttemptPhrase() {
        PhraseProvider provider = new PhraseProvider();
        StringBuilder word = new StringBuilder("Т__Т");
        StringBuilder letters = new StringBuilder("ЕС");

        String attemptPhrase = provider.getAttemptPhrase(2, word, letters);

        assertNotNull(attemptPhrase);
        assertTrue(attemptPhrase.contains("2"));
        assertTrue(attemptPhrase.contains("Т__Т"));
    }
}