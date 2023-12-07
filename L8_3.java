package L8;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;

class ChatUser implements Runnable {
    private String userName;
    private List<String> messages = new ArrayList<>();
    private List<ChatUser> chatUsers;
    private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private Semaphore semaphore = new Semaphore(3); // Обмежуємо кількість одночасних відправлень повідомлень
    private ReentrantLock messageLock = new ReentrantLock();
    private Condition newMessageCondition = messageLock.newCondition();

    public ChatUser(String userName, List<ChatUser> chatUsers) {
        this.userName = userName;
        this.chatUsers = chatUsers;
    }

    public void sendMessage(String message) {
        rwLock.writeLock().lock();
        try {
            messages.add(message);
            messageLock.lock();
            try {
                newMessageCondition.signalAll(); // Повідомляємо про нове повідомлення
            } finally {
                messageLock.unlock();
            }
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void run() {
        while (true) {
            try {
                messageLock.lockInterruptibly();
                try {
                    newMessageCondition.await(); // Очікуємо на нові повідомлення

                    rwLock.readLock().lock();
                    try {
                        for (String message : messages) {
                            System.out.println(userName + " received: " + message);
                        }
                        messages.clear();
                    } finally {
                        rwLock.readLock().unlock();
                    }
                } finally {
                    messageLock.unlock();
                }

                // Відправка повідомлення
                if (semaphore.tryAcquire()) {
                    try {
                        ChatUser targetUser = chatUsers.get((int) (Math.random() * chatUsers.size()));
                        String randomMessage = "Hello, it's " + userName + "!";
                        targetUser.sendMessage(randomMessage);
                        System.out.println(userName + " sent: " + randomMessage);
                    } finally {
                        semaphore.release();
                    }
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break; // Вихід з циклу у разі переривання
            }
        }
    }
}

public class L8_3 {
    public static void main(String[] args) {
        List<ChatUser> chatUsers = new ArrayList<>();
        chatUsers.add(new ChatUser("User1", chatUsers));
        chatUsers.add(new ChatUser("User2", chatUsers));
        chatUsers.add(new ChatUser("User3", chatUsers));

        for (ChatUser user : chatUsers) {
            Thread userThread = new Thread(user);
            userThread.start();
        }
    }
}