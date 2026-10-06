import java.util.Scanner;

public class ChatFilterDriver {
    public static void main(String[] args) {
        String[] logs = {
            "10:05 alice Hello there",
            "10:06 bob Hi everyone",
            "10:07 malformed_line_example",
            "10:08 charlie hello again"
        };

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter keyword: ");
        String keyword = sc.nextLine();

        String result = ChatFilter.filterLogs(logs, keyword);
        System.out.print(result);

        sc.close();
    }
}