public class CmdListRoles extends RecordedCommand {

	@Override
	public void execute(String[] cmdParts) {
		try {
			if (cmdParts.length < 2)
				throw new ExInsufficientArguments();

			Company co = Company.getInstance();
			Employee e = co.searchEmployee(cmdParts[1]);

			e.listRoles();
		} catch (ExInsufficientArguments | ExEmployeeNotFound e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void undoMe() {
	}

	@Override
	public void redoMe() {
	}
}
