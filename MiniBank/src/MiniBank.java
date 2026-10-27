import java.util.Scanner;
import model.*;
import service.*;
import util.*;

public class MiniBank {
    public static void main(String[] args) {
        BankService service = new BankService();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        
        BankInfo bank = new BankInfo("CHARUSAT MiniBank", "Main Campus");

        while (running) {
            System.out.println("\n=== " + bank.name() + " (" + bank.branch() + ") ===");
            System.out.println("1. Open Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. View Statement");
            System.out.println("6. End of Day Report");
            System.out.println("7. Exit");
            System.out.print("Select an option: ");
            
            int choice = scanner.nextInt();
            
            // Lab 1: Switch Expression
            MenuOption option = switch (choice) {
                case 1 -> MenuOption.OPEN_ACCOUNT;
                case 2 -> MenuOption.DEPOSIT;
                case 3 -> MenuOption.WITHDRAW;
                case 4 -> MenuOption.TRANSFER;
                case 5 -> MenuOption.STATEMENT;
                case 6 -> MenuOption.REPORT;
                case 7 -> MenuOption.EXIT;
                default -> null;
            };

            if (option == null) {
                System.out.println("Invalid option, please try again.");
                continue;
            }

            try {
                switch (option) {
                    case OPEN_ACCOUNT -> {
                        System.out.print("Enter Owner Name: ");
                        String name = scanner.next();
                        System.out.print("Enter Opening Balance: ");
                        long bal = scanner.nextLong();
                        Account newAcc = service.openAccount(name, bal);
                        
                       
                        System.out.println("\n>>> SUCCESS! Your new Account Number is: " + newAcc.getAccountNumber() + " <<<\n");
                    }
                    case DEPOSIT -> {
                        System.out.print("Enter Account Number (e.g. AC1001): ");
                        String acc = scanner.next();
                        System.out.print("Enter Amount to Deposit: ");
                        long amt = scanner.nextLong();
                        service.deposit(acc, amt);
                        System.out.println("Deposit request submitted.");
                    }
                    case WITHDRAW -> {
                        System.out.print("Enter Account Number: ");
                        String acc = scanner.next();
                        System.out.print("Enter Amount to Withdraw: ");
                        long amt = scanner.nextLong();
                        service.withdraw(acc, amt);
                        System.out.println("Withdrawal successful.");
                    }
                    case TRANSFER -> {
                        System.out.print("Enter FROM Account: ");
                        String from = scanner.next();
                        System.out.print("Enter TO Account: ");
                        String to = scanner.next();
                        System.out.print("Enter Transfer Amount: ");
                        long amt = scanner.nextLong();
                        service.transfer(from, to, amt);
                        System.out.println("Transfer successful.");
                    }
                    case STATEMENT -> {
                        System.out.print("Enter Account Number: ");
                        String acc = scanner.next();
                        Account found = service.accountRepo.findById(acc);
                        if (found != null) {
                            System.out.println(StatementFormatter.buildStatement(found));
                        } else {
                            System.out.println("Account Not Found.");
                        }
                    }
                    case REPORT -> {
                        ReportGenerator.generate();
                    }
                    case EXIT -> {
                        running = false;
                        service.shutdown();
                        System.out.println("Goodbye! State saved to accounts.dat.");
                    }
                }
            } catch (Exception e) {
                System.out.println("ERROR: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
