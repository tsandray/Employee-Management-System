public class ExInsufficientBalance extends Exception {
	public ExInsufficientBalance(int days) {
		super("Insufficient balance. " + days + " days left only!");
	}

	public ExInsufficientBalance(String message) {
		super(message);
	}

}
