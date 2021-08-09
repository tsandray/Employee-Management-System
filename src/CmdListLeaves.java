public class CmdListLeaves extends RecordedCommand {

	Company co = Company.getInstance();

	@Override
	public void execute(String[] cmdParts) {
		if (cmdParts.length > 1) {
			try {
				Employee e = co.searchEmployee(cmdParts[1]);
				e.listAllLeaves();
			} catch (ExEmployeeNotFound e) {
				System.out.println(e.getMessage());
			}
		} else
			co.listLeaves();
	}

	@Override
	public void undoMe() {
	}

	@Override
	public void redoMe() {
	}
}
