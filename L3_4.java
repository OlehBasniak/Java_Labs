package L3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class Item {
    private String name;
    private double price;

    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Товар - " + name + ", ціна = " + price;
    }
}

class Order {
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        items.add(item);
    }

    public List<Item> getItems() {
        return items;
    }
}

public class L3_4 {
    public static void main(String[] args) {
        List<Order> orders = new ArrayList<>();

        // Створення замовлень та елементів
        Order order1 = new Order();
        order1.addItem(new Item("Книга", 30));
        order1.addItem(new Item("Годинник", 120));

        Order order2 = new Order();
        order2.addItem(new Item("Іграшка", 45));
        order2.addItem(new Item("Телефон", 200));

        orders.add(order1);
        orders.add(order2);

        System.out.println("Початкові замовлення:");
        orders.forEach(order -> System.out.println(order.getItems()));

        // Використання flatMap та фільтрування
        List<Item> filteredItems = orders.stream()
                .flatMap(order -> order.getItems().stream())
                .filter(item -> item.getPrice() > 50)
                .collect(Collectors.toList());

        System.out.println(" ");
        System.out.println("Товари з ціною понад 50 грн:");
        System.out.println(filteredItems);
    }
}