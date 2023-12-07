package L7;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class TicketBookingSystem {
    private static final int TOTAL_SEATS = 10;
    private static int availableSeats = TOTAL_SEATS;
    private static Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Runnable bookingTask = () -> {
            String userName = Thread.currentThread().getName();
            int requestedSeats = 2; // Припустимо, користувач бажає забронювати 2 місця

            // Спроба забронювати квитки
            boolean booked = bookTickets(userName, requestedSeats);

            // Повідомлення про результат бронювання
            if (booked) {
                System.out.println(userName + " successfully booked " + requestedSeats + " seat(s).");
            } else {
                System.out.println(userName + " failed to book " + requestedSeats + " seat(s).");
            }
        };

        // Створення потоків користувачів
        Thread user1 = new Thread(bookingTask, "User 1");
        Thread user2 = new Thread(bookingTask, "User 2");

        // Запуск потоків
        user1.start();
        user2.start();
    }

    private static boolean bookTickets(String userName, int requestedSeats) {
        lock.lock(); // Заблокувати доступ до критичної секції

        try {
            if (availableSeats >= requestedSeats) {
                // Якщо є достатньо вільних місць, забронювати їх
                System.out.println(userName + " is reserving " + requestedSeats + " seat(s).");
                availableSeats -= requestedSeats;
                return true;
            } else {
                // Якщо вільних місць недостатньо
                System.out.println(userName + " couldn't reserve " + requestedSeats + " seat(s). Not enough seats available.");
                return false;
            }
        } finally {
            lock.unlock(); // Розблокувати доступ до критичної секції
        }
    }
}