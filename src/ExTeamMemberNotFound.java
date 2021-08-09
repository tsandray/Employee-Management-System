public class ExTeamMemberNotFound extends Exception {
	public ExTeamMemberNotFound(String teamName, String name) {
		super("Employee (" + name + ") not found for " + teamName + "!");
	}

	public ExTeamMemberNotFound(String message) {
		super(message);
	}

}
