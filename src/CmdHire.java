public class CmdHire extends RecordedCommand {

	Employee e;
	Company co = Company.getInstance();
	int annualLeaves;

	@Override
	public void execute(String[] cmdParts) {
		try {
			if (cmdParts.length < 3)
				throw new ExInsufficientArguments();

			annualLeaves = Integer.parseInt(cmdParts[2]);
			if (annualLeaves < 0 || annualLeaves > 300)
				throw new ExALOutOfRange();

			e = co.createEmployee(cmdParts[1], annualLeaves);

			addUndoCommand(this);
			clearRedoList();

			System.out.println("Done.");
		} catch (ExInsufficientArguments | ExEmployeeAlreadyExists | ExALOutOfRange e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void undoMe() {
		co.removeEmployee(e);
		addRedoCommand(this);
	}

	@Override
	public void redoMe() {
		co.addEmployee(e);
		addUndoCommand(this);
	}
}
