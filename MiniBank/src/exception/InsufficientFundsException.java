package exception;
public class InsufficientFundsException extends BankException {
    public final long shortfall;
    public InsufficientFundsException(long shortfall) {
        super("Insufficient funds. Shortfall: " + shortfall);
        this.shortfall = shortfall;
    }
}
