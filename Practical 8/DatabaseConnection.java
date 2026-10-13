public class DatabaseConnection implements AutoCloseable {
    private final String connectionName;

    public DatabaseConnection(String connectionName) {
        this.connectionName = connectionName;
        System.out.println("Opened database connection: " + connectionName);
    }

    public void executeQuery(String query) throws Exception {
        System.out.println("Executing query: " + query);
        if (query.contains("FAIL")) {
            throw new RuntimeException("Original Error: Query execution failed!");
        }
    }

    @Override
    public void close() {
        System.out.println("Closed database connection: " + connectionName);
    }
}