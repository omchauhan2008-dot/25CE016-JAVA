import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class UniqueVisitors {
    public static void main(String[] args) {
        List<String> logins = Arrays.asList("alice", "bob", "alice", "charlie", "bob", "david", "alice");

        Set<String> uniqueUsers = new HashSet<>(logins);
        System.out.println("Unique count: " + uniqueUsers.size());

        Set<String> orderedUsers = new LinkedHashSet<>(logins);
        System.out.println("First three distinct names:");
        orderedUsers.stream().limit(3).forEach(System.out::println);
    }
}