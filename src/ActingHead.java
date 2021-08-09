import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ActingHead implements Comparable<ActingHead> {

	private String name;
	private LeaveRecord period;

	public ActingHead(String name, LeaveRecord period) {
		this.name = name;
		this.period = period;
	}

	public LeaveRecord getPeriod() {
		return period;
	}

	public String getName() {
		return name;
	}

	public LeaveRecord actingOverlapped(LeaveRecord r) {
		if ((r.getsDay() > period.getsDay() && r.getsDay() < period.geteDay()) || (r.geteDay() > period.getsDay() && r.geteDay() < period.geteDay()))
			return period;

		return null;
	}

	@Override
	public String toString() {
		return period.toString() + ": " + name;
	}

	@Override
	public int compareTo(ActingHead another) {
		return this.period.getsDay().compareTo(another.period.getsDay());
	}


}
