package L4;

import java.util.List;

public class L4_5{

    public static <T extends Number> double sumWithUpperBound(List<? extends T> numbers, T upperBound) {
        double sum = 0.0;

        for (T number : numbers) {
            if (number.doubleValue() <= upperBound.doubleValue()) {
                sum += number.doubleValue();
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        // Приклад використання методу
        List<Integer> integerList = List.of(1, 2, 3, 4, 5);
        int upperBoundInt = 3;
        double sumInt = sumWithUpperBound(integerList, upperBoundInt);
        System.out.println("Сума цілих чисел до " + upperBoundInt + ": " + sumInt);

        List<Double> doubleList = List.of(1.5, 2.5, 3.5, 4.5, 5.5);
        double upperBoundDouble = 4.0;
        double sumDouble = sumWithUpperBound(doubleList, upperBoundDouble);
        System.out.println("Сума дійсних чисел до " + upperBoundDouble + ": " + sumDouble);
    }
}