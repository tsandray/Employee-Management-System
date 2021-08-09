public class CmdAddTeamMember extends RecordedCommand {

	Company co = Company.getInstance();
	Employee e;
	Team t;

	@Override
	public void execute(String[] cmdParts) {
		try {
			if (cmdParts.length < 3)
				throw new ExInsufficientArguments();

			t = co.searchTeam(cmdParts[1]);
			e = co.searchEmployee(cmdParts[2]);

			t.addTeamMember(e);

			addUndoCommand(this);
			clearRedoList();

			System.out.println("Done.");
		} catch (ExInsufficientArguments | ExEmployeeNotFound | ExEmployeeAlreadyInTeam | ExTeamNotFound e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void undoMe() {
		t.removeTeamMember(e);
		addRedoCommand(this);
	}

	@Override
	public void redoMe() {
		try {
			t.addTeamMember(e);
			addUndoCommand(this);
		} catch (ExEmployeeAlreadyInTeam e) {
			System.out.println(e.getMessage());
		}
	}
}