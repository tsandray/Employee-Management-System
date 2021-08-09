public class ExTeamMemberOnActing extends Exception {
	public ExTeamMemberOnActing(LeaveRecord r, String name, String tName) {
		super("Cannot take leave. " + name + " is the acting head of " + tName + " during " + r.toString() + "!");
	}

	public ExTeamMemberOnActing(String message) {
		super(message);
	}

}
