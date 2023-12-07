package L3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class L3_10 {

    public static void main(String[] args) {
        List<String> sentences = Arrays.asList(
                "Hello world and everyone in it",
                "Java is a powerful programming language",
                "Streams in Java are very efficient"
        );

        System.out.println("Речення:");
        sentences.forEach(System.out::println);

        Set<String> uniqueWords = processSentences(sentences);

        System.out.println("\nУнікальні слова:");
        uniqueWords.forEach(System.out::println);
    }

    private static Set<String> processSentences(List<String> sentences) {
        Set<String> stopWords = new HashSet<>(Arrays.asList("the", "and", "is"));

        return sentences.stream()
                .flatMap(sentence -> Arrays.stream(sentence.toLowerCase().split("\\s+")))
                .filter(word -> !stopWords.contains(word))
                .collect(Collectors.toSet());
    }
}