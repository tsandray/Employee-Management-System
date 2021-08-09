public class ExTeamMemberOnLeave extends Exception {
	public ExTeamMemberOnLeave(LeaveRecord r, String name) {
		super(name + " is on leave during " + r.toString() + "!");
	}

	public ExTeamMemberOnLeave(String message) {
		super(message);
	}

}
