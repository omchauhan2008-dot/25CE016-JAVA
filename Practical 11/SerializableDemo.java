import java.io.*;

class UserProfile implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private transient String sessionToken;

    public UserProfile(String username, String sessionToken) {
        this.username = username;
        this.sessionToken = sessionToken;
    }

    @Override
    public String toString() {
        return "UserProfile{username='" + username + "', sessionToken='" + sessionToken + "'}";
    }
}

public class SerializableDemo {
    public static void main(String[] args) {
        String filename = "users.dat";
        UserProfile[] users = {
            new UserProfile("alice", "token_123"),
            new UserProfile("bob", "token_456")
        };

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            UserProfile[] deserializedUsers = (UserProfile[]) ois.readObject();
            for (UserProfile u : deserializedUsers) {
                System.out.println(u);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            new File(filename).delete();
        }
    }
}