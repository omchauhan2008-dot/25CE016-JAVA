import java.util.ArrayList;
import java.util.List;

class User {
    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

class UserService {
    private List<User> users = new ArrayList<>();

    public void addUser(User user) {
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("User name cannot be empty");
        }
        users.add(user);
    }

    public int getUserCount() {
        return users.size();
    }
}

public class Main {
    public static void main(String[] args) {
        UserService service = new UserService();
        service.addUser(new User("U101", "Alice"));
        service.addUser(new User("U102", "Bob"));

        System.out.println("Total Users: " + service.getUserCount());

        try {
            service.addUser(new User("U103", ""));
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected error: " + e.getMessage());
        }
    }
}