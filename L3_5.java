package L3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class L3_5 {

    public static void main(String[] args) {
        List<Integer> numbers = generateRandomNumbers(20, 100);
        System.out.println("Згенеровані числа: " + numbers);

        long count = findUniquePrimeCount(numbers);
        System.out.println("Кількість різних простих чисел: " + count);
    }

    private static List<Integer> generateRandomNumbers(int size, int bound) {
        Random random = new Random();
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            numbers.add(random.nextInt(bound));
        }
        return numbers;
    }

    private static long findUniquePrimeCount(List<Integer> numbers) {
        return numbers.stream()
                .filter(L3_5::isPrime)
                .distinct()
                .count();
    }

    private static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
