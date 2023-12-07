package L4;

import java.util.List;

public class L4_3 {
    // Загальний метод, який приймає список будь-яких типів об'єктів
    public static <T> void printList(List<T> list) {
        for (T element : list) {
            System.out.println(element);
        }
    }

    public static void main(String[] args) {
        // Створення списку різних типів об'єктів
        List<Integer> integerList = List.of(1, 2, 3, 4, 5);
        List<String> stringList = List.of("One", "Two", "Three", "Four", "Five");
        List<Double> doubleList = List.of(1.1, 2.2, 3.3, 4.4, 5.5);

        // Виклик методу для різних списків
        System.out.println("Integer List:");
        printList(integerList);

        System.out.println("\nString List:");
        printList(stringList);

        System.out.println("\nDouble List:");
        printList(doubleList);
    }
}