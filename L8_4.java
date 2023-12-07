package L8;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;

class Restaurant {
    private static final int NUM_CLIENTS = 5;
    private static final int NUM_WAITERS = 2;

    private static final Lock orderLock = new ReentrantLock();
    private static final Lock paymentLock = new ReentrantLock();
    private static final Semaphore orderSemaphore = new Semaphore(2); // Дозволяємо до 2 замовлень одночасно
    private static final Semaphore paymentSemaphore = new Semaphore(2); // Дозволяємо до 2 оплат одночасно
    private static final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static final Condition orderCondition = orderLock.newCondition();

    static class Client extends Thread {
        private int id;

        public Client(int id) {
            this.id = id;
        }

        public void run() {
            if (orderSemaphore.tryAcquire()) {
                makeOrder();
                orderSemaphore.release();
            }
            eat();
            if (paymentSemaphore.tryAcquire()) {
                makePayment();
                paymentSemaphore.release();
            }
        }

        private void makeOrder() {
            orderLock.lock();
            try {
                System.out.println("Client " + id + " is placing an order.");
                Thread.sleep(1000); // Simulating order processing time
                System.out.println("Client " + id + " has placed the order.");
                orderCondition.signalAll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                orderLock.unlock();
            }
        }

        private void eat() {
            System.out.println("Client " + id + " is eating.");
        }

        private void makePayment() {
            paymentLock.lock();
            try {
                System.out.println("Client " + id + " is making the payment.");
                Thread.sleep(500); // Simulating payment processing time
                System.out.println("Client " + id + " has made the payment.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                paymentLock.unlock();
            }
        }
    }

    static class Waiter extends Thread {
        private int id;

        public Waiter(int id) {
            this.id = id;
        }

        public void run() {
            while (true) {
                try {
                    orderLock.lockInterruptibly();
                    try {
                        while (!orderCondition.await(1500, TimeUnit.MILLISECONDS)) {
                            System.out.println("Waiter " + id + " is checking for new orders.");
                        }
                        serveClients();
                    } finally {
                        orderLock.unlock();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void serveClients() {
            System.out.println("Waiter " + id + " is serving clients.");
        }
    }

    public static void main(String[] args) {
        for (int i = 1; i <= NUM_WAITERS; i++) {
            new Waiter(i).start();
        }

        for (int i = 1; i <= NUM_CLIENTS; i++) {
            new Client(i).start();
        }
    }
}