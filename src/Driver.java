import java.util.Calendar;
import java.util.List;

public class Driver {
    public static void main(String[] args) {
        SetupUtils.addTeams();
        SetupUtils.createTeams();
//        SetupUtils.display();
        List<Team> teams =  SetupUtils.getTeams();
        CalculationUtils.calculateMatch(teams.get(0), teams.get(1));
        teams.get(0).displayRecord();
        teams.get(1).displayRecord();
    }
}