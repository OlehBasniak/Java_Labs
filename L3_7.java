package L3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class L3_7 {

    public static void main(String[] args) {
        // Створення списку слів
        List<String> words = Arrays.asList("apple", "banana", "cherry", "date");

        // Виведення початкових даних
        System.out.println("Original list: " + words);

        // Об'єднання слів
        String concatenatedWords = concatenateWordsWithComma(words);

        // Виведення результату
        System.out.println("Concatenated String: " + concatenatedWords);
    }

    public static String concatenateWordsWithComma(List<String> words) {
        return words.stream()
                .collect(Collectors.joining(", "));
    }
}