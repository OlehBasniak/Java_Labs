package L4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class L4_9 { //CollectionExample

    public static void main(String[] args) {
        // Створення ArrayList для зберігання різних типів даних
        ArrayList<Object> mixedList = new ArrayList<>();

        // Додавання елементів до ArrayList
        mixedList.add("Строка");
        mixedList.add(123);
        mixedList.add(45.67);
        mixedList.add(true);

        // Виведення всіх елементів ArrayList
        System.out.println("ArrayList з різними типами даних: " + mixedList);

        // Зміна значення елемента в ArrayList
        mixedList.set(2, "Нове значення");

        // Отримання та виведення значення елемента з ArrayList
        Object elementAtIndex2 = mixedList.get(2);
        System.out.println("Елемент на індексі 2: " + elementAtIndex2);

        // Створення HashMap для зберігання пар "ключ-значення" різних типів даних
        Map<String, Object> mixedMap = new HashMap<>();

        // Додавання пар "ключ-значення" до HashMap
        mixedMap.put("Ключ1", "Значення1");
        mixedMap.put("Ключ2", 456);
        mixedMap.put("Ключ3", 78.90);
        mixedMap.put("Ключ4", false);

        // Виведення всіх пар "ключ-значення" з HashMap
        System.out.println("HashMap з різними типами даних: " + mixedMap);

        // Зміна значення за ключем в HashMap
        mixedMap.put("Ключ2", "Нове значення");

        // Отримання та виведення значення за ключем з HashMap
        Object valueForKey2 = mixedMap.get("Ключ2");
        System.out.println("Значення для Ключ2: " + valueForKey2);
    }
}