package L7;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Restaurant {
    private static final int NUM_CLIENTS = 5;
    private static final int NUM_WAITERS = 2;

    private static final Lock orderLock = new ReentrantLock();
    private static final Lock paymentLock = new ReentrantLock();

    static class Client extends Thread {
        private int id;

        public Client(int id) {
            this.id = id;
        }

        public void run() {
            makeOrder();
            eat();
            makePayment();
        }

        private void makeOrder() {
            orderLock.lock();
            try {
                System.out.println("Client " + id + " is placing an order.");
                Thread.sleep(1000); // Simulating order processing time
                System.out.println("Client " + id + " has placed the order.");
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
            serveClients();
        }

        private void serveClients() {
            while (true) {
                orderLock.lock();
                try {
                    System.out.println("Waiter " + id + " is serving clients.");
                    Thread.sleep(1500); // Simulating order serving time
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    orderLock.unlock();
                }
            }
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