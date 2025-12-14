package arraygenerator;

import com.google.gson.Gson;

public class GeneratorManager {
    private ArrayGenerator<?> generator;
    private int length;
    private final Gson gson = new Gson();

    public GeneratorManager() {
        this.length = 0;
    }

    public ArrayGenerator<?> getGenerator() {
        return generator;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        if (length > 0) {
            this.length = length;
        } else {
            System.out.println("Длина масиива должна быть положительной!");
        }
    }

    public void selectGenerator(int number) {
        switch (number) {
            case 1:
                generator = new CubeArrayGenerator();
                break;
            case 2:
                generator = new BoolArrayGenerator();
                break;
            case 3:
                generator = new CarPlatesArrayGenerator();
                break;
            default:
                System.out.println("Неверный выбор генератора");
        }
    }

    public String generateArray() {
        if (generator == null) {
            return "Сначала выберете генератор!";
        } else if (length <= 0) {
            return "Длина массива должна быть положительной!";
        } else {
            return gson.toJson(generator.generate(length));
        }
    }
}
