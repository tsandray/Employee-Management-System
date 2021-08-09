public class CmdListEmployees extends RecordedCommand {

	@Override
	public void execute(String[] cmdParts) {
		Company company = Company.getInstance();
		company.listEmployee();
	}

	@Override
	public void undoMe() {
	}

	@Override
	public void redoMe() {
	}
}
