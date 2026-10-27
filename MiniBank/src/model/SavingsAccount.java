package model;
public class SavingsAccount extends Account {
    private long minBalance = 500;
    public SavingsAccount(String owner, long opening) { super(owner, opening); }
    @Override public double interestRate() { return 4.0; }
    @Override protected boolean canWithdraw(long amount) { return (balance - amount) >= minBalance; }
}
