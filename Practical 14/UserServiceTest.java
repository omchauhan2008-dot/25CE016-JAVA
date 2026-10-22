// "JUnit JAR Required to Run"
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;

// public class UserServiceTest {
//     private UserService service;

//     @BeforeEach
//     public void setUp() {
//         service = new UserService();
//     }

//     @Test
//     public void testAddUserIncreasesCount() {
//         assertEquals(0, service.getUserCount());
//         service.addUser(new User("U101", "Alice"));
//         assertEquals(1, service.getUserCount());
//     }

//     @Test
//     public void testInvalidUserThrowsException() {
//         assertThrows(IllegalArgumentException.class, () -> {
//             service.addUser(new User("U102", ""));
//         });
//     }
// }
//"Without JUnit JAR"
public class UserServiceTest {
    public static void main(String[] args) {
        UserService service = new UserService();

        // Test 1: Adding user increases count
        service.addUser(new User("U101", "Alice"));
        if (service.getUserCount() != 1) {
            throw new AssertionError("Test 1 Failed: Expected count 1");
        }

        // Test 2: Invalid name throws exception
        boolean exceptionThrown = false;
        try {
            service.addUser(new User("U102", ""));
        } catch (IllegalArgumentException e) {
            exceptionThrown = true;
        }

        if (!exceptionThrown) {
            throw new AssertionError("Test 2 Failed: Expected IllegalArgumentException");
        }

        System.out.println("All tests passed successfully!");
    }
}