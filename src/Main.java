import arraygenerator.GeneratorManager;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GeneratorManager gm = new GeneratorManager();
        Scanner sc = new Scanner(System.in);
        System.out.println("Выбор генератора");
        System.out.println("1. Генератор массива значений игрального кубика");
        System.out.println("2. Генератор массива значений типа boolean");
        System.out.println("3. Генератор массива автомобильных номеров");
        try {
            while (gm.getGenerator() == null) {
                System.out.print("Введите номер: ");
                gm.selectGenerator(sc.nextInt());
            }
            while (gm.getLength() == 0) {
                System.out.print("Введите длину массива: ");
                gm.setLength(sc.nextInt());
            }
            System.out.println(gm.generateArray());
        } catch (NumberFormatException nfe) {
            System.out.println("Некорректное значение! " + nfe);
        } catch (InputMismatchException ime) {
            System.out.println("Некорректное значение! " + ime);
        } finally {
            sc.close();
        }
    }
}