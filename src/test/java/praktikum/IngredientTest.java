package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTest {

    private final String name;
    private final float price;
    private Ingredient ingredient;

    public IngredientTest(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "{index}: {0},{1}")
    public static Object[][] getData() {
        return new Object[][]{
                {"Cheese", .75f},
                {"Beacon", .6f},
                {"Egg", .2f},
                {"Ketchup", .05f}
        };
    }

    @Mock
    IngredientType type;
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ingredient = new Ingredient(type, name, price);
    }

    @Test
    public void getPrice() {
        assertEquals(name, ingredient.getName());
    }

    @Test
    public void testGetNameReturnsExpectedName() {
        assertEquals(name, ingredient.getName());
    }

    @Test
    public void getType() {
        assertEquals(type, ingredient.getType());
    }
}