package model;
public class CurrentAccount extends Account {
    private long overdraftLimit = 10000;
    public CurrentAccount(String owner, long opening) { super(owner, opening); }
    @Override public double interestRate() { return 0.0; }
    @Override protected boolean canWithdraw(long amount) { return (balance - amount) >= -overdraftLimit; }
}
