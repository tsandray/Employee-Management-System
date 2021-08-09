import java.util.ArrayList;

public class CmdTakeLeave extends RecordedCommand {

	Company co = Company.getInstance();
	Employee e;
	LeaveRecord r;
	String[] actingHead;

	@Override
	public void execute(String[] cmdParts) {
		try {
			if (cmdParts.length < 4)
				throw new ExInsufficientArguments();

			e = co.searchEmployee(cmdParts[1]);
			r = new LeaveRecord(new Day(cmdParts[2]), new Day(cmdParts[3]));
			actingHead = new String[cmdParts.length - 4];
			System.arraycopy(cmdParts, 4, actingHead, 0, cmdParts.length - 4);

			e.addLeave(r, actingHead);

			addUndoCommand(this);
			clearRedoList();

			System.out.println("Done.");
		} catch (ExInsufficientArguments | ExEmployeeNotFound | ExOverlappedLeaves | ExDateHasAlreadyPassed | ExInsufficientBalance | ExTeamNotFound | ExTeamMemberNotFound | ExTeamMemberOnLeave | ExNoActingHead | ExTeamMemberOnActing e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void undoMe() {
		e.removeLeave(r, actingHead);
		addRedoCommand(this);
	}

	@Override
	public void redoMe() {
		try {
			e.addLeave(r, actingHead);
			addUndoCommand(this);
		} catch (ExOverlappedLeaves | ExDateHasAlreadyPassed | ExInsufficientBalance | ExTeamNotFound | ExTeamMemberNotFound | ExTeamMemberOnLeave | ExNoActingHead | ExTeamMemberOnActing e) {
			System.out.println(e.getMessage());
		}
	}
}