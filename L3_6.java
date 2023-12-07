package L3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class L3_6 {
    public static void main(String[] args) {
        // Генерація списку операцій
        List<Operation> operations = new ArrayList<>();
        operations.add(new Operation("Дохід", 1000));
        operations.add(new Operation("Дохід", 1500));
        operations.add(new Operation("Витрати", 500));
        operations.add(new Operation("Витрати", 300));

        System.out.println("Початкові дані:");
        operations.forEach(System.out::println);

        // Розділення на групи і підрахунок сум
        Map<String, Integer> summary = operations.stream()
                .collect(Collectors.groupingBy(Operation::getCategory,
                        Collectors.summingInt(Operation::getAmount)));

        System.out.println("\nРезультати:");
        summary.forEach((category, sum) -> System.out.println(category + ": " + sum));
    }

    static class Operation {
        private String category;
        private int amount;

        public Operation(String category, int amount) {
            this.category = category;
            this.amount = amount;
        }

        public String getCategory() {
            return category;
        }

        public int getAmount() {
            return amount;
        }

        @Override
        public String toString() {
            return "Операція: " + category + ", вартість = " + amount;
        }
    }
}