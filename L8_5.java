package L8;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;

class ShoppingCart {
    private List<String> items = new ArrayList<>();
    private ReentrantLock lock = new ReentrantLock();
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    Semaphore semaphore = new Semaphore(2); // Дозволяємо до 2 потоків взаємодіяти з кошиком одночасно
    private Condition itemsChanged = lock.newCondition();

    public void addItem(String item) {
        rwLock.writeLock().lock();
        try {
            items.add(item);
            System.out.println(Thread.currentThread().getName() + " додав товар: " + item);
            printCartContents();
            signalItemsChanged();
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void removeItem(String item) {
        rwLock.writeLock().lock();
        try {
            items.remove(item);
            System.out.println(Thread.currentThread().getName() + " видалив товар: " + item);
            printCartContents();
            signalItemsChanged();
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    private void printCartContents() {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + " Кошик: " + items);
        } finally {
            rwLock.readLock().unlock();
        }
    }

    private void signalItemsChanged() {
        lock.lock();
        try {
            itemsChanged.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void waitForItemsChange() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            itemsChanged.await();
        } finally {
            lock.unlock();
        }
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
            try {
                if (cart.semaphore.tryAcquire()) {
                    cart.addItem(name + "-item" + i);
                    cart.semaphore.release();
                }

                Thread.sleep(1000); // Очікування

                if (cart.semaphore.tryAcquire()) {
                    cart.removeItem(name + "-item" + i);
                    cart.semaphore.release();
                }

                cart.waitForItemsChange(); // Чекаємо на зміни в кошику
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class L8_5 {
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        Thread user1 = new Thread(new User("User1", cart));
        Thread user2 = new Thread(new User("User2", cart));
        user1.start();
        user2.start();
    }
}