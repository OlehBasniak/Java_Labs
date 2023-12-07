package L4;

public class findMax {

    public static <T extends Comparable<T>> T findMax(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }

        T max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(max) > 0) {
                max = array[i];
            }
        }

        return max;
    }

    public static void main(String[] args) {
        // Приклад використання методу findMax з різними типами масивів

        // Цілі числа
        Integer[] intArray = {5, 2, 9, 1, 7};
        Integer maxInt = findMax(intArray);
        System.out.println("Максимальний елемент у масиві цілих чисел: " + maxInt);

        // Подвійні числа
        Double[] doubleArray = {3.5, 8.2, 1.7, 6.4};
        Double maxDouble = findMax(doubleArray);
        System.out.println("Максимальний елемент у масиві подвійних чисел: " + maxDouble);

        // Рядки
        String[] stringArray = {"apple", "banana", "orange", "kiwi"};
        String maxString = findMax(stringArray);
        System.out.println("Максимальний елемент у масиві рядків: " + maxString);
    }
}