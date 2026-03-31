package test;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import main.Console;

public class ConsoleTest {

    @Test
    void testDataInput() {
        String mockInput = "привет, бот";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(mockInput.getBytes()));

        Console console = new Console();
        String result = console.dataInput();

        System.setIn(originalIn);

        assertEquals("привет, бот", result, "Метод должен возвращать строку из ввода без лишних пробелов");
    }

    @Test
    void testDataOut() {
        String message = "Тестовое сообщение";
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        Console console = new Console();
        console.dataOut(message);

        System.setOut(originalOut);

        assertEquals(message + System.lineSeparator(), outputStream.toString(),
                "Метод должен выводить текст в System.out");
    }
}