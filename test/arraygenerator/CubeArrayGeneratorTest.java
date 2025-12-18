package arraygenerator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CubeArrayGeneratorTest {

    @Test
    void testGenerateValidLength() {
        CubeArrayGenerator generator = new CubeArrayGenerator();
        Integer[] result = generator.generate(10);

        assertEquals(10, result.length);
        for (Integer value : result) {
            assertTrue(value >= 1 && value <= 6,
                    "Значение должно быть между 1 и 6, получено: " + value);
        }
    }

    @Test
    void testGenerateZeroLength() {
        CubeArrayGenerator generator = new CubeArrayGenerator();
        Integer[] result = generator.generate(0);

        assertEquals(0, result.length);
    }

    @Test
    void testGenerateLargeArray() {
        CubeArrayGenerator generator = new CubeArrayGenerator();
        Integer[] result = generator.generate(1000);

        assertEquals(1000, result.length);
        for (Integer value : result) {
            assertTrue(value >= 1 && value <= 6);
        }
    }

    @Test
    void testGenerateDistribution() {
        CubeArrayGenerator generator = new CubeArrayGenerator();
        Integer[] result = generator.generate(10000);

        int[] counts = new int[7];
        for (Integer value : result) {
            counts[value]++;
        }

        for (int i = 1; i <= 6; i++) {
            assertTrue(counts[i] > 0,
                    "Значение " + i + " должно встречаться хотя бы раз");
        }
    }
}