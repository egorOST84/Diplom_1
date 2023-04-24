package praktikum;

import com.github.javafaker.Faker;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class BunTest {
    private static Faker faker;

    @BeforeClass
    public static void setup() {
        faker = new Faker();
    }
    @Test
    public void checkGetNameReturnsName() {
        Bun bun = createRandomBun();
        String expectedName = bun.getName();
        assertEquals(expectedName, bun.getName());
    }

    @Test
    public void checkGetPriceReturnsPrice() {
        Bun bun = createRandomBun();
        float expectedPrice = bun.getPrice();
        assertEquals(expectedPrice, bun.getPrice(), 0.01);
    }

    public Bun createRandomBun() {
        float price = new Random().nextFloat();
        String name = "Bun with " + faker.food().fruit().toLowerCase();
        return new Bun(name, price);
    }
}