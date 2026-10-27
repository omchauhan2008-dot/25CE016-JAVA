package model;
import model.annotation.*;
import exception.*;
import java.io.Serializable;
import java.util.Objects;

// Lab 5: Abstract class
public abstract class Account implements Transactable, InterestBearing, Serializable, Comparable<Account> {
    @Id
    private final String accountNumber;
    private String ownerName;
    
    @Positive
    protected long balance;
    protected boolean active;
    private static long accCounter = 1000; // Auto-generates IDs like AC1001

    public Account(String ownerName, long openingBalance) {
        this.accountNumber = "AC" + (++accCounter);
        this.ownerName = ownerName;
        this.balance = openingBalance;
        this.active = true;
    }

    // Lab 9: Synchronized methods to prevent race conditions
    @Override
    public synchronized void deposit(long amount) throws BankException {
        if (amount <= 0) throw new InvalidAmountException("Deposit must be positive");
        this.balance += amount;
    }

    @Override
    public synchronized boolean withdraw(long amount) throws BankException {
        if (amount <= 0) throw new InvalidAmountException("Withdrawal must be positive");
        if (!canWithdraw(amount)) {
            throw new InsufficientFundsException(amount - balance);
        }
        this.balance -= amount;
        return true;
    }

    protected abstract boolean canWithdraw(long amount);

    public String getAccountNumber() { return accountNumber; }
    public long getBalance() { return balance; }
    public String getOwnerName() { return ownerName; }

    @Override
    public String toString() {
        return "Account: " + accountNumber + " | Owner: " + ownerName + " | Balance: Rs." + balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return accountNumber.equals(account.accountNumber);
    }

    @Override
    public int hashCode() { return Objects.hash(accountNumber); }

    // Lab 12: Comparable
    @Override
    public int compareTo(Account other) {
        return this.accountNumber.compareTo(other.accountNumber);
    }
}
