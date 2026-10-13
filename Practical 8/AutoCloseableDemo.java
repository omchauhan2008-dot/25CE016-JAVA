public class AutoCloseableDemo {
    public static void main(String[] args) {
        try (DatabaseConnection db = new DatabaseConnection("ProdDB")) {
            db.executeQuery("SELECT * FROM users WHERE status = 'FAIL'");
        } catch (Exception e) {
            System.out.println("Caught Exception in catch block: " + e.getMessage());
        }
    }
}