public class ExNoActingHead extends Exception {
	public ExNoActingHead(Team t) {
		super("Please name a member to be the acting head of " + t.getName());
	}

	public ExNoActingHead(String message) {
		super(message);
	}
}
