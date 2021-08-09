import java.util.ArrayList;
import java.util.Collections; //Provides sorting

public class Company {

	private ArrayList<Employee> allEmployees;
	private ArrayList<Team> allTeams;

	private static Company instance = new Company();

	private Company() {
		allEmployees = new ArrayList<>();
		allTeams = new ArrayList<>();
	}

	public static Company getInstance() {
		return instance;
	}

	public void listTeams() {
		Team.list(allTeams);
	}

	public Employee createEmployee(String name, int annualLeaves) throws ExEmployeeAlreadyExists {
		for (Employee allEmployee : allEmployees)
			if (allEmployee.getName().equals(name))
				throw new ExEmployeeAlreadyExists();

		Employee e = new Employee(name, annualLeaves);
		allEmployees.add(e);
		Collections.sort(allEmployees);
		return e;
	}

	public Team createTeam(String tName, String hName) throws ExTeamAlreadyExists, ExEmployeeNotFound, ExEmployeeAlreadyInTeam {
		Employee e = Employee.searchEmployee(allEmployees, hName);

		for (Team allTeams : allTeams)
			if (allTeams.getName().equals(tName))
				throw new ExTeamAlreadyExists();

		Team t = new Team(tName, e);
		t.addTeamMember(e);
		allTeams.add(t);
		Collections.sort(allTeams);
		return t;
	}

	public void addTeam(Team t) {
		allTeams.add(t);
		Collections.sort(allTeams);
	}

	public void removeTeam(Team t) {
		t.removeAllAssoTeamMember();
		allTeams.remove(t);
	}

	public void addEmployee(Employee e) {
		allEmployees.add(e);
		Collections.sort(allEmployees);
	}

	public void removeEmployee(Employee e) {
		allEmployees.remove(e);
	}

	public void listEmployee() {
		for (Employee e : allEmployees)
			System.out.printf("%s (Entitled Annual Leaves: %d days)\n", e.getName(), e.getYrLeavesEntitled());
	}

	public void listLeaves() {
		for (Employee e : allEmployees) {
			System.out.println(e.getName() + ":");
			e.listAllLeaves();
		}
	}

	public void listTeamMembers() {
		for (Team t : allTeams) {
			System.out.println(t.getName() + ":");
			t.listAllMembers();
			System.out.println();
		}
	}

	public Employee searchEmployee(String name) throws ExEmployeeNotFound {
		return Employee.searchEmployee(allEmployees, name);
	}

	public Team searchTeam(String tName) throws ExTeamNotFound {
		for (Team allTeams : allTeams)
			if (allTeams.getName().equals(tName))
				return allTeams;
		throw new ExTeamNotFound();
	}

}
