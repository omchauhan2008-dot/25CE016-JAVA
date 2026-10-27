package util;
import model.Account;
public class StatementFormatter {
    public static String buildStatement(Account account) {
        StringBuilder sb = new StringBuilder();
        sb.append("=== Account Statement ===\n");
        sb.append("A/C No: ").append(account.getAccountNumber()).append("\n");
        sb.append("Owner: ").append(account.getOwnerName()).append("\n");
        sb.append("Current Balance: Rs.").append(account.getBalance()).append("\n");
        sb.append("=========================");
        return sb.toString();
    }
}
