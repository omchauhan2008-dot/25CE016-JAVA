package util;
public class CommandParser {
    public static Command parse(String line) throws Exception {
        String[] parts = line.split(" ");
        if (parts.length < 3) throw new Exception("Invalid command format");
        return new Command(TransactionType.valueOf(parts[0].toUpperCase()), parts[1], Long.parseLong(parts[2]));
    }
}
