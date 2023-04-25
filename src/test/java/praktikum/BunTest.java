package praktikum;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class BunTest {

    private static Bun bun;
    @BeforeClass
    public static void setup() {
        bun = new Bun("Bun with seeds", 1.5f);
    }
    @Test
    public void testGetNameReturnsExpectedName() {
        assertEquals("Bun with seeds", bun.getName());
    }

    @Test
    public void testGetPriceReturnsExpectedPrice() {
        assertEquals(1.5f, bun.getPrice(), 0.001f);
    }
}