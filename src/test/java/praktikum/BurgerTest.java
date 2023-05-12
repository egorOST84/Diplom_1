package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private Burger burger;
    @Mock
    private Bun bun;
    @Mock
    private Ingredient ingredient1;
    @Mock
    private Ingredient ingredient2;

    @Before
    public void setUp() {
        burger = new Burger();

        when(ingredient1.getName()).thenReturn("Beef cutlet");
        when(ingredient1.getPrice()).thenReturn(1.25f);
        when(ingredient1.getType()).thenReturn(IngredientType.FILLING);

        when(ingredient2.getName()).thenReturn("Ketchup");
        when(ingredient2.getPrice()).thenReturn(0.3f);
        when(ingredient2.getType()).thenReturn(IngredientType.SAUCE);

        when(bun.getName()).thenReturn("Test bun");
        when(bun.getPrice()).thenReturn(0.75f);
    }

    @Test
    public void testSetBunsSetsBun() {
        Bun bunStubbed = new Bun(bun.getName(), bun.getPrice());
        burger.setBuns(bunStubbed);
        assertEquals(bun.getName(), burger.bun.name);
        assertEquals(bun.getPrice(), burger.bun.price, 0.001f);
    }

    @Test
    public void testAddIngredientAddsIngredient() {
        Ingredient ingredientStubbed = new Ingredient(ingredient1.getType(), ingredient1.getName(), ingredient1.getPrice());
        burger.addIngredient(ingredientStubbed);
        assertEquals(ingredient1.getName(), burger.ingredients.get(0).name);
        assertEquals(ingredient1.getPrice(), burger.ingredients.get(0).price, 0.001f);
    }

    @Test
    public void testRemoveIngredientFromListOfIngredientsByIndex() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.removeIngredient(0);
        assertFalse(burger.ingredients.contains(ingredient1));
        assertThat(burger.ingredients, hasSize(1));
    }

    @Test
    public void testMoveIngredientToAnotherPlaceInIngredientsList() {
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.moveIngredient(0, 1);
        assertEquals(1, burger.ingredients.indexOf(ingredient1));
    }

    @Test
    public void testGetPriceReturnsBunDoubledPricePlusAllIngredientsPrice() {
        float expectedPrice = bun.getPrice() * 2 + ingredient1.getPrice() + ingredient2.getPrice();
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        assertEquals(expectedPrice, burger.getPrice(), 0.001f);
    }

    @Test
    public void testGetReceiptContainsBunName() {
        burger.setBuns(bun);
        assertTrue(burger.getReceipt().contains(String.format("(==== %s ====)%n", bun.getName())));
    }

    @Test
    public void testGetReceiptContainsIngredients() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        String receipt = burger.getReceipt();

        String ingredient1String = String.format("= %s %s =", ingredient1.getType().toString().toLowerCase(), ingredient1.getName());
        String ingredient2String = String.format("= %s %s =", ingredient2.getType().toString().toLowerCase(), ingredient2.getName());

        assertTrue(receipt.contains(ingredient1String) && receipt.contains(ingredient2String));
    }

    @Test
    public void testGetReceiptContainsPrice() {
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        assertTrue(burger.getReceipt().contains(String.format("%nPrice: %f%n", burger.getPrice())));
    }
}