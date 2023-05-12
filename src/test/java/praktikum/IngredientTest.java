package praktikum;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class IngredientTest {
    private String name;
    private float price;
    private IngredientType type;
    private Ingredient ingredient;

    @Before
    public void setUpAndInit() {
        type = IngredientType.FILLING;
        name = "Cheese";
        price = .75f;
        ingredient = new Ingredient(type, name, price);
    }

    @Test
    public void testGetPriceReturnsExpectedPrice() {
        assertEquals(price, ingredient.getPrice(), 0.001f);
    }

    @Test
    public void testGetNameReturnsExpectedName() {
        assertEquals(name, ingredient.getName());
    }

    @Test
    public void testGetTypeWithMockedIngredientType() {
        assertEquals(type, ingredient.getType());
    }
}