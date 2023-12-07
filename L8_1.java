package L8;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.Semaphore;

class BankAccount {
    private int balance;
    private Lock lock = new ReentrantLock();
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private Semaphore semaphore = new Semaphore(1);

    public BankAccount(int initialBalance) {
        this.balance = initialBalance;
    }

    public int getBalance() {
        rwLock.readLock().lock();
        try {
            return balance;
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public void deposit(int amount) {
        rwLock.writeLock().lock();
        try {
            balance += amount;
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void withdraw(int amount) {
        rwLock.writeLock().lock();
        try {
            balance -= amount;
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void transfer(BankAccount toAccount, int amount) {
        if (lock.tryLock()) {
            try {
                semaphore.acquire();
                if (this.balance >= amount) {
                    this.withdraw(amount);
                    toAccount.deposit(amount);
                    System.out.println("Transfer successful: " + amount + " transferred from "
                            + Thread.currentThread().getName() + " to " + toAccount);
                } else {
                    System.out.println("Insufficient funds for transfer from " + Thread.currentThread().getName());
                }
                semaphore.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                lock.unlock();
            }
        }
    }
}

class Client implements Runnable {
    private String name;
    private BankAccount[] accounts;

    public Client(String name, BankAccount[] accounts) {
        this.name = name;
        this.accounts = accounts;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            int fromAccountIdx = (int) (Math.random() * accounts.length);
            int toAccountIdx = (int) (Math.random() * accounts.length);
            int amount = (int) (Math.random() * 100);
            accounts[fromAccountIdx].transfer(accounts[toAccountIdx], amount);
            try {
                // Застосуйте Thread.sleep() для імітації інтервалу між транзакціями
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class L8_1 {
    public static void main(String[] args) {
        int numClients = 5;
        int numAccounts = 3;
        BankAccount[] accounts = new BankAccount[numAccounts];

        for (int i = 0; i < numAccounts; i++) {
            accounts[i] = new BankAccount(1000);
        }

        Thread[] threads = new Thread[numClients];
        for (int i = 0; i < numClients; i++) {
            threads[i] = new Thread(new Client("Client " + (i + 1), accounts));
            threads[i].start();
        }

        // Зачекайте, поки всі потоки завершаться
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Виведіть кінцевий баланс рахунків
        for (int i = 0; i < numAccounts; i++) {
            System.out.println("Account " + i + " Balance: " + accounts[i].getBalance());
        }
    }
}