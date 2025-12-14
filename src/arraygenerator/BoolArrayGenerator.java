package arraygenerator;

import java.util.Random;

public class BoolArrayGenerator implements ArrayGenerator<Boolean> {
    @Override
    public Boolean[] generate(int length) {
        Random rnd = new Random();
        Boolean[] array = new Boolean[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = rnd.nextBoolean();
        }
        return array;
    }
}
