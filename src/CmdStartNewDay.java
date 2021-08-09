public class CmdStartNewDay extends RecordedCommand {

	Day oDay, nDay;

	@Override
	public void execute(String[] cmdParts) {
		oDay = SystemDate.getInstance().clone();
		SystemDate.getInstance().set(cmdParts[1]);

		addUndoCommand(this);
		clearRedoList();

		System.out.println("Done.");
	}

	@Override
	public void undoMe() {
		nDay = SystemDate.getInstance().clone();
		SystemDate.getInstance().set(oDay.toString());

		addRedoCommand(this);
	}

	@Override
	public void redoMe() {
		SystemDate.getInstance().set(nDay.toString());

		addUndoCommand(this);
	}
}
