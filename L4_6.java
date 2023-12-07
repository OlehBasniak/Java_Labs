package L4;

import java.util.ArrayList;
import java.util.List;

public class L4_6 {

    // Метод, який додає ціле число до кожного елемента у списку
    public static <T extends Number> List<Double> addToEachElement(List<T> list, int numberToAdd) {
        List<Double> result = new ArrayList<>();

        for (T element : list) {
            double value = element.doubleValue() + numberToAdd;
            result.add(value);
        }

        return result;
    }

    public static void main(String[] args) {
        // Приклад використання методу
        List<Integer> integerList = List.of(1, 2, 3, 4, 5);
        List<Double> result = addToEachElement(integerList, 10);

        // Виведення результату
        System.out.println("Оригінальний список: " + integerList);
        System.out.println("Результат додавання числа 10 до кожного елемента: " + result);
    }
}