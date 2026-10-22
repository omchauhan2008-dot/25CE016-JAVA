import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class DirectoryWalkerReport {
    public static void main(String[] args) throws IOException {
        Path startDir = Paths.get(".");
        Path reportFile = Paths.get("directory_report.txt");

        try (BufferedWriter writer = Files.newBufferedWriter(reportFile)) {
            Files.walkFileTree(startDir, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    String info = String.format("File: %s | Size: %d bytes | Last Modified: %s%n",
                            file.getFileName(), attrs.size(), attrs.lastModifiedTime());
                    writer.write(info);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) {
                    return FileVisitResult.CONTINUE;
                }
            });
        }

        System.out.println("Report generated successfully at " + reportFile.toAbsolutePath());
    }
}