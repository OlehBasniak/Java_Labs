package L7;

import java.util.ArrayList;
import java.util.List;

class ChatUser implements Runnable {
    private String userName;
    private List<String> messages;
    private List<ChatUser> chatUsers;

    public ChatUser(String userName, List<ChatUser> chatUsers) {
        this.userName = userName;
        this.messages = new ArrayList<>();
        this.chatUsers = chatUsers;
    }

    public void sendMessage(String message) {
        synchronized (messages) {
            messages.add(message);
        }
    }

    public void run() {
        while (true) {
            try {
                // Чекаємо трошки перед тим, як читати повідомлення
                Thread.sleep(1000);

                // Виводимо отримані повідомлення
                synchronized (messages) {
                    for (String message : messages) {
                        System.out.println(userName + " received: " + message);
                    }
                    messages.clear();
                }

                // Випадково вибираємо користувача для відправки повідомлення
                ChatUser targetUser = chatUsers.get((int) (Math.random() * chatUsers.size()));

                // Відправляємо випадкове повідомлення
                String randomMessage = "Hello, it's " + userName + "!";
                targetUser.sendMessage(randomMessage);
                System.out.println(userName + " sent: " + randomMessage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class L7_3 { //ChatApplication
    public static void main(String[] args) {
        List<ChatUser> chatUsers = new ArrayList<>();
        chatUsers.add(new ChatUser("User1", chatUsers));
        chatUsers.add(new ChatUser("User2", chatUsers));
        chatUsers.add(new ChatUser("User3", chatUsers));

        // Запускаємо потоки для кожного користувача
        for (ChatUser user : chatUsers) {
            Thread userThread = new Thread(user);
            userThread.start();
        }
    }
}