import java.util.List;
import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        SetupUtils.addTeams();
        SetupUtils.createTeams();
//        SetupUtils.display();
        List<Team> teams =  SetupUtils.getTeams();
        Scanner scanner = new Scanner(System.in);
        int awayTeam;
        int homeTeam;
        SetupUtils.display();
        System.out.println("Please enter the number of the away team.");
        awayTeam = scanner.nextInt() - 1;
        System.out.println("Please enter the number of the home team.");
        homeTeam = scanner.nextInt() - 1;
        for(int i = 0; i < 7; i++) {
            CalculationUtils.calculateMatch(teams.get(awayTeam), teams.get(homeTeam));
        }
        teams.get(awayTeam).displayRecord();
        teams.get(homeTeam).displayRecord();
    }
}