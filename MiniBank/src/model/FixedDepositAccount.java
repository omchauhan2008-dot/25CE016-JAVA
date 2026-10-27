package model;
public class FixedDepositAccount extends Account {
    public FixedDepositAccount(String owner, long opening) { super(owner, opening); }
    @Override public double interestRate() { return 7.0; }
    @Override protected boolean canWithdraw(long amount) { return false; } // Cannot withdraw early
}
