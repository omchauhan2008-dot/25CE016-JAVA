import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class LogAnalyzer {
    public static void main(String[] args) throws IOException {
        Path dir = Paths.get("logs_temp");
        Files.createDirectories(dir);

        Path log1 = dir.resolve("app1.log");
        Path log2 = dir.resolve("app2.log");

        Files.write(log1, List.of("INFO System started", "ERROR Disk full", "INFO Shutdown"));
        Files.write(log2, List.of("ERROR Out of memory", "INFO Task complete"));

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter keyword: ");
        String keyword = scanner.next().toLowerCase();

        int totalLines = 0;
        int matchCount = 0;

        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir, "*.log")) {
            for (Path entry : stream) {
                System.out.println("File: " + entry.getFileName() + " | Size: " + Files.size(entry) + " bytes");
                List<String> lines = Files.readAllLines(entry);
                totalLines += lines.size();
                for (String line : lines) {
                    if (line.toLowerCase().contains(keyword)) {
                        matchCount++;
                    }
                }
            }
        }

        System.out.println("Total Lines: " + totalLines);
        System.out.println("Matches for '" + keyword + "': " + matchCount);

        Files.deleteIfExists(log1);
        Files.deleteIfExists(log2);
        Files.deleteIfExists(dir);
        scanner.close();
    }
}