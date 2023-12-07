package L8;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;

class TicketBookingSystem {
    private static final int TOTAL_SEATS = 10;
    private static int availableSeats = TOTAL_SEATS;
    private static Lock lock = new ReentrantLock();
    private static Semaphore semaphore = new Semaphore(5); // Максимум 5 потоків можуть бронювати одночасно
    private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private static Condition seatsAvailable = lock.newCondition();

    public static void main(String[] args) {
        Runnable bookingTask = () -> {
            String userName = Thread.currentThread().getName();
            int requestedSeats = 2;

            if (semaphore.tryAcquire()) {
                boolean booked = bookTickets(userName, requestedSeats);
                if (booked) {
                    System.out.println(userName + " successfully booked " + requestedSeats + " seat(s).");
                } else {
                    System.out.println(userName + " failed to book " + requestedSeats + " seat(s).");
                }
                semaphore.release();
            } else {
                System.out.println(userName + " couldn't acquire a permit to book seats.");
            }
        };

        Thread user1 = new Thread(bookingTask, "User 1");
        Thread user2 = new Thread(bookingTask, "User 2");
        user1.start();
        user2.start();
    }

    private static boolean bookTickets(String userName, int requestedSeats) {
        lock.lock();
        try {
            while (availableSeats < requestedSeats) {
                seatsAvailable.await();
            }
            rwLock.writeLock().lock();
            try {
                if (availableSeats >= requestedSeats) {
                    System.out.println(userName + " is reserving " + requestedSeats + " seat(s).");
                    availableSeats -= requestedSeats;
                    return true;
                } else {
                    System.out.println(userName + " couldn't reserve " + requestedSeats + " seat(s). Not enough seats available.");
                    return false;
                }
            } finally {
                rwLock.writeLock().unlock();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } finally {
            lock.unlock();
        }
    }

    // Метод для повернення місць, який може використовуватися для імітації повернення квитків
    private static void returnSeats(int seats) {
        lock.lock();
        try {
            availableSeats += seats;
            seatsAvailable.signalAll();
        } finally {
            lock.unlock();
        }
    }
}