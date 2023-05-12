package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class IngredientTypeTest {

    @Test
    public void testIngredientTypeEnumSize() {
        // Проверяем количество элементов в перечислении:
        assertEquals(2, IngredientType.values().length);
    }

    @Test
    public void testIngredientTypeValues() {
        IngredientType[] values = IngredientType.values();
        // Проверяем корректность значений перечисления и их порядок:
        assertSame(values[0], IngredientType.SAUCE);
        assertSame(values[1], IngredientType.FILLING);
    }

    @Test
    public void testEnumContains() {
        // Проверяем наличие определенных констант перечисления:
        assertSame(IngredientType.valueOf("SAUCE"), IngredientType.SAUCE);
        assertSame(IngredientType.valueOf("FILLING"), IngredientType.FILLING);
    }
}