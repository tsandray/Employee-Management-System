import java.util.ArrayList;
import java.util.Collections;

public class Team implements Comparable<Team> {

	private String teamName;
	private Employee head;
	private Day dateSetup;
	private ArrayList<Employee> allMembers;
	private ArrayList<ActingHead> allActingHead;

	public Team(String n, Employee hd) {
		this.teamName = n;
		this.head = hd;
		this.dateSetup = SystemDate.getInstance().clone();
		allMembers = new ArrayList<>();
		allActingHead = new ArrayList<>();
	}

	public String getName() {
		return teamName;
	}

	public String getHeadName() {
		return head.getName();
	}

	public ArrayList<ActingHead> getAllActingHead() {
		return allActingHead;
	}

	public static void list(ArrayList<Team> list) {
		System.out.printf("%-30s%-10s%-13s\n", "Team Name", "Leader", "Setup Date");
		for (Team t : list)
			System.out.printf("%-30s%-10s%-13s\n", t.teamName, t.head.getName(), t.dateSetup.toString());
	}

	public void addTeamMember(Employee e) throws ExEmployeeAlreadyInTeam {
		for (Employee e1 : allMembers)
			if (e1.getName().equals(e.getName()))
				throw new ExEmployeeAlreadyInTeam();

		allMembers.add(e);
		e.addTeam(this);
		Collections.sort(allMembers);
	}

	public void removeTeamMember(Employee e) {
		allMembers.remove(e);
		e.removeTeam(this);
	}

	public void addActingHead(ActingHead actingHead) {
		allActingHead.add(actingHead);
		Collections.sort(allActingHead);
	}

	public void removeActingHead(ActingHead actingHead) {
		for (ActingHead aH : allActingHead)
			if (aH.getPeriod() == actingHead.getPeriod()) {
				allActingHead.remove(aH);
				break;
			}
	}

	public void removeAllAssoTeamMember() {
		for (Employee e : allMembers)
			e.removeTeam(this);
	}

	public Employee searchTeamMember(String name) throws ExTeamMemberNotFound {
		for (Employee e : allMembers)
			if (e.getName().equals(name))
				return e;
		throw new ExTeamMemberNotFound(teamName, name);
	}

	public void listAllMembers() {
		for (Employee e : allMembers)
			if (e.getName().equals(getHeadName()))
				System.out.println(e.getName() + " (Head of Team)");
			else
				System.out.println(e.getName());

		if (!allActingHead.isEmpty()) {
			System.out.println("Acting heads:");
			for (ActingHead aH : allActingHead)
				System.out.println(aH.toString());
		}
	}

	@Override
	public int compareTo(Team another) {
		return this.teamName.compareTo(another.teamName);
	}
}
