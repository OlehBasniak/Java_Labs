package L4;

public class Box<T> {
    private T content;

    // Конструктор, який створює пустий ящик
    public Box() {
        this.content = null;
    }

    // Метод для поміщення предмету в коробку
    public void putItem(T item) {
        this.content = item;
    }

    // Метод для вилучення предмету із коробки
    public T getItem() {
        T item = this.content;
        this.content = null;
        return item;
    }

    public static void main(String[] args) {
        // Приклад використання з різними типами даних
        Box<Integer> intBox = new Box<>();
        intBox.putItem(42);
        System.out.println("Значення в коробці (ціле): " + intBox.getItem());

        Box<String> strBox = new Box<>();
        strBox.putItem("Hello, Generics!");
        System.out.println("Значення в коробці (рядок): " + strBox.getItem());

        Box<Double> doubleBox = new Box<>();
        doubleBox.putItem(3.14);
        System.out.println("Значення в коробці (дробове): " + doubleBox.getItem());
    }
}