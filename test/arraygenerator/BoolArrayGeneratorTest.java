package arraygenerator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoolArrayGeneratorTest {

    @Test
    void testGenerateValidLength() {
        BoolArrayGenerator generator = new BoolArrayGenerator();
        Boolean[] result = generator.generate(15);

        assertEquals(15, result.length);
        for (Boolean value : result) {
            assertNotNull(value);
        }
    }

    @Test
    void testGenerateZeroLength() {
        BoolArrayGenerator generator = new BoolArrayGenerator();
        Boolean[] result = generator.generate(0);

        assertEquals(0, result.length);
    }

    @Test
    void testGenerateAllValuesAreBoolean() {
        BoolArrayGenerator generator = new BoolArrayGenerator();
        Boolean[] result = generator.generate(50);

        for (Boolean value : result) {
            assertTrue(value == Boolean.TRUE || value == Boolean.FALSE);
        }
    }

    @Test
    void testGenerateDistribution() {
        BoolArrayGenerator generator = new BoolArrayGenerator();
        Boolean[] result = generator.generate(1000);

        int trueCount = 0;
        int falseCount = 0;

        for (Boolean value : result) {
            if (value) {
                trueCount++;
            } else {
                falseCount++;
            }
        }

        assertTrue(trueCount > 0, "Должны быть true значения");
        assertTrue(falseCount > 0, "Должны быть false значения");

        double ratio = (double) trueCount / (trueCount + falseCount);
        assertTrue(ratio > 0.2 && ratio < 0.8,
                "Соотношение должно быть примерно 50/50, получено: " + ratio);
    }
}