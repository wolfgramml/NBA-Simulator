import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        SetupUtils.addTeams();
        SetupUtils.createTeams();
        SetupUtils.createMatchups();
        List<Team> teams =  SetupUtils.getTeams();
        CalculationUtils.simulateMatchups();

        Scanner scanner = new Scanner(System.in);
        int teamNum;
        SetupUtils.display();
        System.out.println("What team's record would you like to view? Enter -1 to quit.");
        teamNum = scanner.nextInt();
        while(teamNum != -1) {
            teams.get(teamNum-1).displayRecord();
            System.out.println("----------------------------------");
            SetupUtils.display();
            System.out.println("What team's record would you like to view? Enter -1 to quit.");
            teamNum = scanner.nextInt();
        }

    }
}