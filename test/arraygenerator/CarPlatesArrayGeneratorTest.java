package arraygenerator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarPlatesArrayGeneratorTest {

    private static final String VALID_LETTERS = "ABCEOPXKMTY";

    @Test
    void testGenerateValidLength() {
        CarPlatesArrayGenerator generator = new CarPlatesArrayGenerator();
        String[] result = generator.generate(5);

        assertEquals(5, result.length);

        for (String plate : result) {
            assertNotNull(plate);
            assertEquals(8, plate.length(), "Номер должен состоять из 8 символов");
        }
    }

    @Test
    void testGenerateZeroLength() {
        CarPlatesArrayGenerator generator = new CarPlatesArrayGenerator();
        String[] result = generator.generate(0);

        assertEquals(0, result.length);
    }

    @Test
    void testGenerateFormat() {
        CarPlatesArrayGenerator generator = new CarPlatesArrayGenerator();
        String[] result = generator.generate(10);

        for (String plate : result) {
            assertEquals(8, plate.length());

            char firstChar = plate.charAt(0);
            assertTrue(isValidLetter(firstChar),
                    "Первая буква должна быть из набора: " + VALID_LETTERS);

            for (int i = 1; i <= 3; i++) {
                char digitChar = plate.charAt(i);
                assertTrue(Character.isDigit(digitChar),
                        "Символ в позиции " + i + " должен быть цифрой");
            }

            char letter1 = plate.charAt(4);
            char letter2 = plate.charAt(5);
            assertTrue(isValidLetter(letter1) && isValidLetter(letter2),
                    "Буквы в позициях 4 и 5 должны быть из набора: " + VALID_LETTERS);

            for (int i = 6; i <= 7; i++) {
                char digitChar = plate.charAt(i);
                assertTrue(Character.isDigit(digitChar),
                        "Символ в позиции " + i + " должен быть цифрой");
            }
        }
    }

    @Test
    void testGenerateValidNumbersRange() {
        CarPlatesArrayGenerator generator = new CarPlatesArrayGenerator();
        String[] result = generator.generate(50);

        for (String plate : result) {
            String middleNumberStr = plate.substring(1, 4);
            int middleNumber = Integer.parseInt(middleNumberStr);
            assertTrue(middleNumber >= 0 && middleNumber <= 999,
                    "Трехзначное число должно быть в диапазоне 000-999");

            String lastNumberStr = plate.substring(6);
            int lastNumber = Integer.parseInt(lastNumberStr);
            assertTrue(lastNumber >= 0 && lastNumber <= 99,
                    "Двухзначное число должно быть в диапазоне 00-99");
        }
    }

    @Test
    void testGenerateNoEmptyStrings() {
        CarPlatesArrayGenerator generator = new CarPlatesArrayGenerator();
        String[] result = generator.generate(20);

        for (String plate : result) {
            assertFalse(plate.trim().isEmpty(), "Номер не должен быть пустой строкой");
            assertFalse(plate.contains(" "), "Номер не должен содержать пробелы");
        }
    }

    @Test
    void testGenerateAllUppercase() {
        CarPlatesArrayGenerator generator = new CarPlatesArrayGenerator();
        String[] result = generator.generate(15);

        for (String plate : result) {
            for (int i = 0; i < plate.length(); i++) {
                char c = plate.charAt(i);
                if (Character.isLetter(c)) {
                    assertTrue(Character.isUpperCase(c),
                            "Все буквы должны быть заглавными");
                }
            }
        }
    }

    @Test
    void testGenerateDifferentResults() {
        CarPlatesArrayGenerator generator = new CarPlatesArrayGenerator();
        String[] result1 = generator.generate(5);
        String[] result2 = generator.generate(5);

        assertEquals(result1.length, result2.length);

        for (String plate : result1) {
            assertEquals(8, plate.length());
        }
        for (String plate : result2) {
            assertEquals(8, plate.length());
        }
    }

    private boolean isValidLetter(char c) {
        return VALID_LETTERS.indexOf(c) >= 0;
    }
}