package L4;

import java.util.List;

public class L4_7 {

    public static void printList(List<?> list) {
        for (Object element : list) {
            System.out.print(element + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Приклад виклику методу зі списком рядків
        List<String> stringList = List.of("one", "two", "three");
        System.out.println("String List:");
        printList(stringList);

        // Приклад виклику методу зі списком цілих чисел
        List<Integer> integerList = List.of(1, 2, 3, 4, 5);
        System.out.println("Integer List:");
        printList(integerList);

        // Приклад виклику методу зі списком будь-яких об'єктів
        List<Object> mixedList = List.of("apple", 42, 3.14, true);
        System.out.println("Mixed List:");
        printList(mixedList);
    }
}