package arraygenerator;

import java.util.Random;

public class CarPlatesArrayGenerator implements ArrayGenerator<String> {
    private char[] letters = {'A', 'B', 'C', 'E', 'O', 'P', 'X', 'K', 'M', 'T', 'Y'};

    @Override
    public String[] generate(int length) {
        Random rnd = new Random();
        String[] array = new String[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = letters[rnd.nextInt(0, letters.length)] +
                    String.format("%03d", rnd.nextInt(1000)) +
                    letters[rnd.nextInt(0, letters.length)] +
                    letters[rnd.nextInt(0, letters.length)] +
                    String.format("%02d", rnd.nextInt(100));
        }
        return array;
    }
}
