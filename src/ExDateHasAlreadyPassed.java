public class ExDateHasAlreadyPassed extends Exception {
	public ExDateHasAlreadyPassed(Day currDay) {
		super("Wrong Date. System date is already " + currDay.toString() + "!");
	}

	public ExDateHasAlreadyPassed(String message) {
		super(message);
	}

}
