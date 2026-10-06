@FunctionalInterface
interface Notifier {
    void send(String message);
}

interface Urgent {
}

class UrgentSmsNotifier implements Notifier, Urgent {
    public void send(String message) {
        System.out.println("SMS: " + message);
    }
}

public class NotificationDriver {
    public static void main(String[] args) {
        Notifier emailNotifier = message -> System.out.println("Email: " + message);
        Notifier smsNotifier = new UrgentSmsNotifier();

        Notifier[] senders = { emailNotifier, smsNotifier };
        String message = "System update scheduled at midnight.";

        for (Notifier sender : senders) {
            sender.send(message);
            if (sender instanceof Urgent) {
                sender.send(message);
            }
        }
    }
}