public class ExEmployeeAlreadyInTeam extends Exception {
	public ExEmployeeAlreadyInTeam() {
		super("Employee has already joined the team!");
	}

	public ExEmployeeAlreadyInTeam(String message) {
		super(message);
	}

}
