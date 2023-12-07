package L3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class L3_1 {
    public static void main(String[] args) {
        // Створюємо список рядків
        List<String> strings = new ArrayList<>();
        strings.add("Java");
        strings.add("Python");
        strings.add("JavaScript");
        strings.add("C++");
        strings.add("Ruby");
        strings.add("C#");

        // Виводимо вихідний список
        System.out.println("Вихідний список рядків:");
        strings.forEach(System.out::println);

        // Фільтруємо та перетворюємо рядки та зберігаємо їх у новий список
        List<String> filteredAndMappedStrings = strings.stream()
                .filter(s -> s.contains("a"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        // Виводимо результат у консоль
        System.out.println("\nРезультат після фільтрації та мапування:");
        filteredAndMappedStrings.forEach(System.out::println);
    }
}
