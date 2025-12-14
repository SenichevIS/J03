package arraygenerator;

import java.util.Random;

public class CubeArrayGenerator implements ArrayGenerator<Integer> {
    @Override
    public Integer[] generate(int length) {
        Random rnd = new Random();
        Integer[] array = new Integer[length];
        for (int i = 0; i < array.length; i++) {
            array[i] = rnd.nextInt(1, 7);
        }
        return array;
    }
}
