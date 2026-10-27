package service;
import java.io.IOException;
import java.nio.file.*;

public class TransactionLog {
    public static void append(String line) {
        try {
            Files.writeString(Path.of("transactions.log"), line + System.lineSeparator(), 
                StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) { e.printStackTrace(); }
    }
}
