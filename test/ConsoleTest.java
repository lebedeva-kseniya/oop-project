package test;

import main.Console;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import static org.junit.jupiter.api.Assertions.*;

public class ConsoleTest {

    @Test
    public void testDataInput() {
        String input = "тестовый ввод";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Console console = new Console();
        String result = console.dataInput();

        assertEquals("тестовый ввод", result);
    }

    @Test
    public void testDataOutput() {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outputStream));

        Console console = new Console();
        console.dataOut("тестовый вывод");

        System.setOut(originalOut);
        String output = outputStream.toString().trim();

        assertTrue(output.contains("тестовый вывод"));
    }
}