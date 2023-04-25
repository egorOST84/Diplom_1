package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class BurgerTest {
    private Burger burger;
    private static Bun bun;
    private static Ingredient filling;
    private static Ingredient sauce;
    private String receipt;

    @BeforeClass
    public static void setUp() {
        // Создаем булочку для тестирования
        bun = new Bun("Bun with seeds", 1.5f);
        // Создаем ингредиенты для тестирования
        filling = new Ingredient(IngredientType.FILLING, "Filling", 0.5f);
        sauce = new Ingredient(IngredientType.SAUCE, "Sauce", 0.25f);
    }

    @Before
    public void createBurger() {
        burger = new Burger();
    }

    @Test
    public void testSetBunsSetsBun() {
        // Добавляем булочку в бургер
        burger.setBuns(bun);
        // Проверяем, что поле bun типа Bun в бургере равно созданному тестовому объекту bun
        Assert.assertEquals(bun, burger.bun);
    }

    @Test
    public void testAddIngredientAddsMockIngredient() {
        // Добавляем ингредиент
        burger.addIngredient(filling);
        // Проверяем, что ингридиент добавлен в список ингридиентов бургера
        Assert.assertEquals(filling, burger.ingredients.get(0));
    }

    @Test
    public void testRemoveIngredientFromListOfIngredientsByIndex() {
        // Добавляем ингредиенты
        burger.addIngredient(filling);
        burger.addIngredient(sauce);
        // Вызываем метод removeIngredient, передавая ему индекс удаляемого ингредиента
        burger.removeIngredient(0);
        // Проверяем, что ингридиент удален из списка ингридиентов бургера
        assertFalse(burger.ingredients.contains(filling));
        // Проверяем, что размер списка равен 1 после удаления
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void testMoveIngredientToAnotherPlaceInIngredientsList() {
        // Добавляем ингредиенты
        burger.addIngredient(filling);
        burger.addIngredient(sauce);
        // Вызываем метод moveIngredient, передавая ему индекс удаляемого ингредиента
        burger.moveIngredient(0, 1);
        // / Проверяем, что метод изменился индекс ингридиента с 0 на 1 в списке ингридиентов бургера
        assertEquals(1, burger.ingredients.indexOf(filling));
    }

    @Test
    public void testGetPriceReturnsBunDoubledPricePlusAllIngredientsPrice() {
        // Добавляем булочку в бургер
        burger.setBuns(bun);
        // Добавляем ингредиенты
        burger.addIngredient(filling);
        burger.addIngredient(sauce);
        // Проверяем, что метод getPrice() возвращает (bun.getPrice() * 2) + сумма всех ингридиентов
        assertEquals(3.75f, burger.getPrice(), 0.001f);
    }

    @Test
    public void testGetReceiptContainsBunName() {
        // Добавляем булочку в бургер
        burger.setBuns(bun);
        // Получаем рецепт бургера и сохраняем в переменную
        receipt = burger.getReceipt();
        // Проверяем, что в рецепте есть название булочки
        assertTrue(receipt.contains(String.format("(==== %s ====)%n", bun.getName())));
    }

    @Test
    public void testGetReceiptContainsPrice() {
        // Добавляем булочку в бургер
        burger.setBuns(bun);
        // Добавляем ингредиенты
        burger.addIngredient(filling);
        burger.addIngredient(sauce);
        // Получаем рецепт бургера и сохраняем в переменную
        receipt = burger.getReceipt();
        // Проверяем, что в рецепте есть стоимость бургера (булочка с ингридиентами)
        assertTrue(receipt.contains(String.format("%nPrice: %f%n", burger.getPrice())));
    }
}