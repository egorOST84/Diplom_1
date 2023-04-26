package praktikum;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@RunWith(Parameterized.class)
public class BunTest {

    private static Bun bun;
    private final String name;
    private final float price;
    private final String message;

    public BunTest(String name, float price, String message) {
        this.name = name;
        this.price = price;
        this.message = message;
    }

    @Parameterized.Parameters(name = "{index}: {0},{1}")
    public static Object[][] getData() {
        return new Object[][]{
                {null, 0.0f, "Name cannot be null or empty"},
                {"Valid name", -1.1f, "Price cannot be negative"}
        };
    }

    @BeforeClass
    public static void init() {
        // Создаем объект класса Bun, который будет использоваться в тестах.
        bun = new Bun("Bun with seeds", 1.5f);
    }
    @Test
    public void testGetNameReturnsExpectedName() {
        // Проверка, что getName() возвращает корректное значение имени у объекта класса Bun
        assertEquals("Bun with seeds", bun.getName());
    }

    @Test
    public void testGetPriceReturnsExpectedPrice() {
        // Проверка, что getPrice() возвращает корректную стоимость у объекта класса Bun
        assertEquals(1.5f, bun.getPrice(), 0.001f);
    }

    @Test()
    @Ignore("Тест не выполнится, так как нет проверки на невалидные значения в конструкторе класса")
    public void testValidationBunFieldsType(){
        // Я бы добавил тест на проверку конструктора класса Bun. Он будет проверять,
        // что при передаче некорректных значений в конструктор, будут выбрасываться соответствующие исключения.
        // Такой проверки в классе Bun нет, поэтому булочка может быть создана с отрицательной
        // стоимостью или без названия, что не мой взгляд неправильно. Если мы разрабатывали через TDD,
        // то я бы добавлял подобную проверку и соответственно тест на нее. Пример:
        /*
        public Bun(String name, float price) throws IllegalArgumentException{
            if (name == null || name.isEmpty()) {
                throw new IllegalArgumentException("Name cannot be null or empty");
            }
            if (price < 0.0f) {
                throw new IllegalArgumentException("Price cannot be negative");
            }

            this.name = name;
            this.price = price;
        }
         */
        //
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Bun(name, price));
        // Проверяем текст сообщения исключения
        assertEquals(message, exception.getMessage());
    }
}