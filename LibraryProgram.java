package L2;

import java.time.LocalDate;
import java.util.*;

abstract class Literature {
    private String title;
    private String author;
    private LocalDate publicationDate;
    private String type;

    public Literature(String title, String author, LocalDate publicationDate, String type) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
        this.type = type;
    }

    // Гетери і сетери для полів класу
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public String getType() {
        return type;
    }

    // Абстрактний метод для отримання опису літератури
    public abstract String getDescription();
}

class Book extends Literature {
    public Book(String title, String author, LocalDate publicationDate) {
        super(title, author, publicationDate, "Book");
    }

    @Override
    public String getDescription() {
        return String.format("Book: %s by %s, published on %s", getTitle(), getAuthor(), getPublicationDate());
    }
}

class Article extends Literature {
    public Article(String title, String author, LocalDate publicationDate) {
        super(title, author, publicationDate, "Article");
    }

    @Override
    public String getDescription() {
        return String.format("Article: %s by %s, published on %s", getTitle(), getAuthor(), getPublicationDate());
    }
}

// Основні класи для персон
class Person {
    private String name;
    private LocalDate dateOfBirth;

    public Person(String name, LocalDate dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}

class Librarian extends Person {
    public Librarian(String name, LocalDate dateOfBirth) {
        super(name, dateOfBirth);
    }
}

class Client extends Person {
    private List<Literature> literatureList;
    private Map<Literature, LocalDate[]> borrowingRecords;

    public Client(String name, LocalDate dateOfBirth) {
        super(name, dateOfBirth);
        this.literatureList = new ArrayList<>();
        this.borrowingRecords = new HashMap<>();
    }

    public void borrowLiterature(Literature literature, LocalDate borrowDate, LocalDate returnDate) {
        literatureList.add(literature);
        borrowingRecords.put(literature, new LocalDate[]{borrowDate, returnDate});
    }

    public List<Literature> getLiteratureList() {
        return literatureList;
    }

    public Map<Literature, LocalDate[]> getBorrowingRecords() {
        return borrowingRecords;
    }

    public int getReadLiteratureCount() {
        return literatureList.size();
    }
}

// Клас для роботи з колекцією літератури
class Library {
    private List<Literature> literatureList;
    private Librarian librarian;
    private List<Client> clients;

    public Library(Librarian librarian) {
        this.librarian = librarian;
        this.literatureList = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    public void addLiterature(Literature literature) {
        literatureList.add(literature);
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public List<Literature> getLiteratureList() {
        return literatureList;
    }

    public List<Client> getClients() {
        return clients;
    }

    // Методи для сортування
    public void sortLiteratureByAuthor() {
        literatureList.sort(Comparator.comparing(Literature::getAuthor));
    }

    public void sortLiteratureByType() {
        literatureList.sort(Comparator.comparing(Literature::getType));
    }

    public void sortLiteratureByPublicationDate() {
        literatureList.sort(Comparator.comparing(Literature::getPublicationDate));
    }

    public void sortClientsByReadLiteratureCount() {
        clients.sort(Comparator.comparingInt(Client::getReadLiteratureCount).reversed());
    }

    public Client findMostLiteratureReadClient() {
        return Collections.max(clients, Comparator.comparingInt(Client::getReadLiteratureCount));
    }

    // Виведення інформації
    public void printLiteratureList() {
        for (Literature literature : literatureList) {
            System.out.println(literature.getDescription());
        }
    }

    public void printClients() {
        for (Client client : clients) {
            System.out.printf("%s has read %d pieces of literature.\n", client.getName(), client.getReadLiteratureCount());
        }
    }
}

// Клас для демонстрації використання бібліотеки
public class LibraryProgram {
    public static void main(String[] args) {
        // Створення завідувача
        Librarian librarian = new Librarian("Іван Іванович Іванов", LocalDate.of(1970, 1, 1));
        Library library = new Library(librarian);

        // Додавання літератури до бібліотеки
        library.addLiterature(new Book("Війна і мир", "Лев Толстой", LocalDate.of(1869, 1, 1)));
        library.addLiterature(new Article("Філософія Java", "Еккель Брюс", LocalDate.of(2002, 5, 20)));

        // Створення та додавання клієнтів
        Client client1 = new Client("Петро Петрович Петров", LocalDate.of(1985, 5, 20));
        Client client2 = new Client("Олександра Олександрівна Ковальчук", LocalDate.of(1992, 8, 15));
        library.addClient(client1);
        library.addClient(client2);

        // Клієнти беруть літературу
        client1.borrowLiterature(library.getLiteratureList().get(0), LocalDate.now(), LocalDate.now().plusWeeks(2));
        client2.borrowLiterature(library.getLiteratureList().get(1), LocalDate.now(), LocalDate.now().plusDays(30));

        // Сортування та виведення списків
        System.out.println("Література, відсортована за автором:");
        library.sortLiteratureByAuthor();
        library.printLiteratureList();

        System.out.println("\nЛітература, відсортована за типом:");
        library.sortLiteratureByType();
        library.printLiteratureList();

        System.out.println("\nЛітература, відсортована за датою публікації:");
        library.sortLiteratureByPublicationDate();
        library.printLiteratureList();

        System.out.println("\nЧитачі, відсортовані за кількістю прочитаної літератури:");
        library.sortClientsByReadLiteratureCount();
        library.printClients();

        // Знайти читача з найбільшою кількістю прочитаної літератури
        Client mostActiveReader = library.findMostLiteratureReadClient();
        System.out.printf("\nЧитач з найбільшою кількістю прочитаної літератури: %s, прочитано %d книг.\n",
                mostActiveReader.getName(), mostActiveReader.getReadLiteratureCount());
    }
}
