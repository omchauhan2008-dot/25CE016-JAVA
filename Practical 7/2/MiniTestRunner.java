import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

// Sample class containing annotated and non-annotated methods
class TestSuite {

    @Run
    public void testLogin() {
        System.out.println("  [PASS] testLogin executed");
    }

    public void helperMethod() {
        System.out.println("  [SKIP] helperMethod executed");
    }

    @Run
    public void testPayment() {
        System.out.println("  [PASS] testPayment executed");
    }

    @Run
    private void testDatabaseConnection() {
        System.out.println("  [PASS] testDatabaseConnection executed (private)");
    }

    public void testLogout() {
        System.out.println("  [SKIP] testLogout executed");
    }
}

public class MiniTestRunner {
    public static void main(String[] args) {
        Class<TestSuite> testClass = TestSuite.class;
        TestSuite suiteInstance;

        try {
            // Instantiate the target class
            suiteInstance = testClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.err.println("Failed to instantiate test suite: " + e.getMessage());
            return;
        }

        System.out.println("Discovering and running tests in: " + testClass.getSimpleName());
        System.out.println("--------------------------------------------------");

        int runCount = 0;

        // Retrieve all declared methods (including private ones)
        Method[] methods = testClass.getDeclaredMethods();

        for (Method method : methods) {
            // Check if the method is marked with @Run
            if (method.isAnnotationPresent(Run.class)) {
                try {
                    // Allow invocation even if the method has private access
                    method.setAccessible(true);

                    // Invoke the no-arg method on the instance
                    method.invoke(suiteInstance);
                    runCount++;
                } catch (IllegalAccessException | InvocationTargetException e) {
                    System.err.println("  [FAIL] Error executing " + method.getName() + ": " + e.getMessage());
                }
            }
        }

        System.out.println("--------------------------------------------------");
        System.out.println("Execution Summary: " + runCount + " test(s) ran successfully.");
    }
}