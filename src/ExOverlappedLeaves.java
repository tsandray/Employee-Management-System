public class ExOverlappedLeaves extends Exception {
	public ExOverlappedLeaves(LeaveRecord r) {
		super("Overlap with leave from " + r.toString() + "!");
	}

	public ExOverlappedLeaves(String message) {
		super(message);
	}

}
