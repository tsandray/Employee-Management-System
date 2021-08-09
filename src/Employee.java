import java.util.ArrayList;
import java.util.Collections;

public class Employee implements Comparable<Employee> {

	Company co = Company.getInstance();
	private String name;
	private int yrLeavesEntitled;
	private ArrayList<LeaveRecord> allLeaveRecord;
	private ArrayList<Team> allTeam;
	private ArrayList<Team> allHeadTeam;

	public Employee(String n, int yle) {
		this.name = n;
		this.yrLeavesEntitled = yle;
		allLeaveRecord = new ArrayList<>();
		allTeam = new ArrayList<>();
		allHeadTeam = new ArrayList<>();
	}

	public String getName() {
		return name;
	}

	public int getYrLeavesEntitled() {
		return yrLeavesEntitled;
	}

	public static Employee searchEmployee(ArrayList<Employee> list, String nameToSearch) throws ExEmployeeNotFound {
		for (Employee aList : list)
			if (aList.getName().equals(nameToSearch))
				return aList;
		throw new ExEmployeeNotFound();
	}

	public LeaveRecord getOverlapped(LeaveRecord r) {
		for (LeaveRecord r1 : allLeaveRecord)
			if ((r.getsDay() > r1.getsDay() && r.getsDay() < r1.geteDay()) || (r.geteDay() > r1.getsDay() && r.geteDay() < r1.geteDay()))
				return r1;

		return null;
	}

	public void addLeave(LeaveRecord r, String[] actingHead) throws ExOverlappedLeaves, ExDateHasAlreadyPassed, ExInsufficientBalance, ExTeamNotFound, ExTeamMemberNotFound, ExTeamMemberOnLeave, ExNoActingHead, ExTeamMemberOnActing {
		Day currentDay = SystemDate.getInstance().clone();
		if (r.getsDay() < currentDay.getIntDay())
			throw new ExDateHasAlreadyPassed(currentDay);

		LeaveRecord temp = getOverlapped(r);
		if (temp != null)
			throw new ExOverlappedLeaves(temp);

		if (yrLeavesEntitled < r.daysBetween())
			throw new ExInsufficientBalance(yrLeavesEntitled);

		if (actingHead.length == 0) {
			for (Team t : allTeam)
				for (ActingHead aH : t.getAllActingHead())
					if (aH.getName().equals(name)) {
						temp = aH.actingOverlapped(r);
						if (temp != null)
							throw new ExTeamMemberOnActing(temp, name, t.getName());
					}
		} else {
			allHeadTeam.clear();
			for (Team t : allTeam)
				if (t.getHeadName().equals(name))
					allHeadTeam.add(t);

			for (int i = 0; i < actingHead.length; i += 2) {
				Team t = co.searchTeam(actingHead[i]);
				Employee e = t.searchTeamMember(actingHead[i + 1]);
				temp = e.getOverlapped(r);
				if (temp != null)
					throw new ExTeamMemberOnLeave(temp, e.getName());
				allHeadTeam.remove(t);
			}

			if (!allHeadTeam.isEmpty())
				throw new ExNoActingHead(allHeadTeam.get(0));

			for (int i = 0; i < actingHead.length; i += 2) {
				Team t = co.searchTeam(actingHead[i]);

				ActingHead aH = new ActingHead(actingHead[i + 1], r);
				t.addActingHead(aH);
			}


		}

		allLeaveRecord.add(r);
		yrLeavesEntitled -= r.daysBetween();
		Collections.sort(allLeaveRecord);
	}

	public void removeLeave(LeaveRecord r, String[] actingHead) {
		try {
			for (int i = 0; i < actingHead.length; i += 2) {
				Team t = co.searchTeam(actingHead[i]);
				ActingHead aH = new ActingHead(actingHead[i + 1], r);
				t.removeActingHead(aH);
			}
		} catch (ExTeamNotFound e) {
			System.out.println(e.getMessage());
		}

		yrLeavesEntitled += r.daysBetween();
		allLeaveRecord.remove(r);
	}

	public void listAllLeaves() {
		if (allLeaveRecord.isEmpty())
			System.out.println("No leave record");

		for (LeaveRecord r : allLeaveRecord)
			System.out.println(r.toString());
	}

	public void addTeam(Team t) {
		allTeam.add(t);
		Collections.sort(allTeam);
	}

	public void removeTeam(Team t) {
		allTeam.remove(t);
	}

	public void listRoles() {
		if (allTeam.size() == 0)
			System.out.println("No role");

		for (Team t : allTeam)
			if (t.getHeadName().equals(name))
				System.out.println(t.getName() + " (Head of Team)");
			else
				System.out.println(t.getName());
	}

	@Override
	public int compareTo(Employee another) {
		return this.name.compareTo(another.name);
	}
}
