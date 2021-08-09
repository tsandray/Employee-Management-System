public class CmdListTeamMembers extends RecordedCommand {

	@Override
	public void execute(String[] cmdParts) {
		Company co = Company.getInstance();
		co.listTeamMembers();
	}

	@Override
	public void undoMe() {
	}

	@Override
	public void redoMe() {
	}
}
