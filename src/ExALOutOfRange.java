public class ExALOutOfRange extends Exception {
	public ExALOutOfRange() {
		super("Annual leaves out of range (0-300)!");
	}

	public ExALOutOfRange(String message) {
		super(message);
	}

}
