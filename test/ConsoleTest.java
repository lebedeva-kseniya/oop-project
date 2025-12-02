import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

    public class ConsoleTest {

        @Test
        public void testDataOut() {
            Console console = new Console();
            // метод выполняется без ошибок
            assertDoesNotThrow(() -> console.data_out("Тест"));
        }

        @Test
        public void testDataInput() {
            Console console = new Console();
            // Проверяем, что метод существует и возвращает строку
            assertNotNull(console.data_input());
        }
    }

