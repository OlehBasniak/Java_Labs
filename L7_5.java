package L7;

import java.util.ArrayList;
import java.util.List;

class ShoppingCart {
    private List<String> items = new ArrayList<>();

    // Метод для додавання товару в кошик
    public synchronized void addItem(String item) {
        items.add(item);
        System.out.println(Thread.currentThread().getName() + " додав товар: " + item);
        printCartContents();
    }

    // Метод для видалення товару з кошика
    public synchronized void removeItem(String item) {
        items.remove(item);
        System.out.println(Thread.currentThread().getName() + " видалив товар: " + item);
        printCartContents();
    }

    // Метод для виведення вмісту кошика
    private void printCartContents() {
        System.out.println(Thread.currentThread().getName() + " Кошик: " + items);
    }
}

class User implements Runnable {
    private String name;
    private ShoppingCart cart;

    public User(String name, ShoppingCart cart) {
        this.name = name;
        this.cart = cart;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            // Спроба додати товар в кошик
            cart.addItem(name + "-item" + i);

            // Очікування перед видаленням товару
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Спроба видалити товар з кошика
            cart.removeItem(name + "-item" + i);
        }
    }
}

public class L7_5 { //OnlineShoppingSystem
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        // Створення потоків користувачів
        Thread user1 = new Thread(new User("User1", cart));
        Thread user2 = new Thread(new User("User2", cart));

        // Запуск потоків користувачів
        user1.start();
        user2.start();
    }
}