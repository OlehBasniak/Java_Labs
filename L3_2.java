package L3;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.stream.Collectors;

public class L3_2 {

    public static void main(String[] args) {
        // Створення списку книг
        List<Book> books = new ArrayList<>();
        books.add(new Book("Книга 1", 2001));
        books.add(new Book("Книга 2", 1998));
        books.add(new Book("Книга 3", 2005));
        books.add(new Book("Книга 4", 1986));
        books.add(new Book("Книга 5", 1995));

        System.out.println("Початковий список книг:");
        books.forEach(book -> System.out.println(book.getName() + " (" + book.getYear() + ")"));

        // Фільтрація та сортування
        List<Book> filteredBooks = filterAndSortBooks(books);

        System.out.println("\nТри найстаріші книги:");
        filteredBooks.forEach(book -> System.out.println(book.getName() + " (" + book.getYear() + ")"));
    }

    private static List<Book> filterAndSortBooks(List<Book> books) {
        return books.stream()
                .sorted((b1, b2) -> Integer.compare(b1.getYear(), b2.getYear()))
                .limit(3)
                .collect(Collectors.toList());
    }

    static class Book {
        private String name;
        private int year;

        public Book(String name, int year) {
            this.name = name;
            this.year = year;
        }

        public String getName() {
            return name;
        }

        public int getYear() {
            return year;
        }
    }
}