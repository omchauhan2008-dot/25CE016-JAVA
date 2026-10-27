package service;
import model.*;
import exception.*;
import java.util.List;

// Lab 14: Wiring everything together
public class BankService {
    public Repository<Account> accountRepo = new Repository<>();
    private TransactionProcessor processor = new TransactionProcessor();

    public BankService() {
        // Load data on startup
        List<Account> saved = StatePersister.load("accounts.dat");
        accountRepo.loadAll(saved);
    }

    public Account openAccount(String name, long balance) {
        Account newAcc = new SavingsAccount(name, balance);
        accountRepo.save(newAcc);
        return newAcc;
    }

    public void deposit(String accNo, long amount) throws BankException {
        Account acc = accountRepo.findById(accNo);
        if (acc == null) throw new AccountNotFoundException(accNo);
        
        processor.submit(() -> {
            try {
                acc.deposit(amount);
                TransactionLog.append("DEPOSIT " + accNo + " " + amount);
            } catch (Exception e) {}
        });
    }

    public void withdraw(String accNo, long amount) throws BankException {
        Account acc = accountRepo.findById(accNo);
        if (acc == null) throw new AccountNotFoundException(accNo);
        
        acc.withdraw(amount);
        TransactionLog.append("WITHDRAW " + accNo + " " + amount);
    }

    // Lab 10: Deadlock prevention via lock ordering
    public void transfer(String fromAcc, String toAcc, long amount) throws BankException {
        Account a1 = accountRepo.findById(fromAcc);
        Account a2 = accountRepo.findById(toAcc);
        if (a1 == null || a2 == null) throw new BankException("Account(s) not found for transfer");

        Account first = (a1.compareTo(a2) < 0) ? a1 : a2;
        Account second = (a1.compareTo(a2) < 0) ? a2 : a1;

        synchronized (first) {
            synchronized (second) {
                a1.withdraw(amount);
                a2.deposit(amount);
                TransactionLog.append("TRANSFER " + fromAcc + "->" + toAcc + " " + amount);
            }
        }
    }

    public void shutdown() {
        processor.stop();
        StatePersister.save(accountRepo.findAll(), "accounts.dat");
    }
}
