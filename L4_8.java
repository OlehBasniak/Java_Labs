package L4;

// Загальний інтерфейс валідатора
interface Validator<T> {
    boolean validate(T item);
}

// Клас валідатора для рядків
class StringValidator implements Validator<String> {
    @Override
    public boolean validate(String item) {
        // Ваша логіка перевірки для рядків
        return item != null && !item.isEmpty();
    }
}

// Клас валідатора для цілих чисел
class IntegerValidator implements Validator<Integer> {
    @Override
    public boolean validate(Integer item) {
        // Ваша логіка перевірки для цілих чисел
        return item != null && item >= 0;
    }
}

public class L4_8 {
    public static void main(String[] args) {
        // Використання валідаторів
        StringValidator stringValidator = new StringValidator();
        IntegerValidator integerValidator = new IntegerValidator();

        // Приклад використання для рядків
        String inputString = "Hello, World!";
        if (stringValidator.validate(inputString)) {
            System.out.println("Рядок валідний.");
        } else {
            System.out.println("Рядок не валідний.");
        }

        // Приклад використання для цілих чисел
        Integer inputInteger = 42;
        if (integerValidator.validate(inputInteger)) {
            System.out.println("Ціле число валідне.");
        } else {
            System.out.println("Ціле число не валідне.");
        }
    }
}