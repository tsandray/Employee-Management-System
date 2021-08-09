public class CmdSetupTeam extends RecordedCommand {

	Team t;
	Employee e;
	Company co = Company.getInstance();

	@Override
	public void execute(String[] cmdParts) {
		try {
			if (cmdParts.length < 3)
				throw new ExInsufficientArguments();

			t = co.createTeam(cmdParts[1], cmdParts[2]);
			e = co.searchEmployee(cmdParts[2]);

			addUndoCommand(this);
			clearRedoList();

			System.out.println("Done.");
		} catch (ExInsufficientArguments | ExTeamAlreadyExists | ExEmployeeNotFound | ExEmployeeAlreadyInTeam e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void undoMe() {
		co.removeTeam(t);

		addRedoCommand(this);
	}

	@Override
	public void redoMe() {
		co.addTeam(t);
		e.addTeam(t);

		addUndoCommand(this);
	}
}
