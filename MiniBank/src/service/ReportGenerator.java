package service;
import java.nio.file.*;
import java.io.BufferedReader;
import java.io.IOException;

// Lab 11: File attributes and NIO
public class ReportGenerator {
    public static void generate() {
        Path logPath = Path.of("transactions.log");
        if (!Files.exists(logPath)) {
            System.out.println("No transactions to report.");
            return;
        }
        
        long totalDep = 0;
        long totalWith = 0;
        
        try (BufferedReader reader = Files.newBufferedReader(logPath)) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("DEPOSIT")) {
                    String[] parts = line.split(" ");
                    totalDep += Long.parseLong(parts[2]);
                } else if (line.startsWith("WITHDRAW")) {
                    String[] parts = line.split(" ");
                    totalWith += Long.parseLong(parts[2]);
                }
            }
            
            System.out.println("=== End of Day Report ===");
            System.out.println("Log File Size: " + Files.size(logPath) + " bytes");
            System.out.println("Total Deposits: Rs." + totalDep);
            System.out.println("Total Withdrawals: Rs." + totalWith);
            System.out.println("Net Flow: Rs." + (totalDep - totalWith));
            System.out.println("=========================");
        } catch (IOException e) {
            System.out.println("Error reading report.");
        }
    }
}
