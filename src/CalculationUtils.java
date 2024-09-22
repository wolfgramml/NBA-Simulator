import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class CalculationUtils {

    public static void simulateMatchups() {
        List<Team> listOfTeams = SetupUtils.getTeams();
        for(int i = 0; i < listOfTeams.size(); i++) {
            listOfTeams.get(i).playAllMatchups();
        }
    }

    public static void calculateMatch(Matchup matchup) {
        Team homeTeam = matchup.getHomeTeam();
        Team awayTeam = matchup.getAwayTeam();
        int val;
        Random rand = new Random();
        val = rand.nextInt();
        if (val % 2 == 0) {
            matchup.updateWins();
            homeTeam.updateWins();
            awayTeam.updateLosses();
        } else {
            matchup.updateLosses();
            homeTeam.updateLosses();
            awayTeam.updateWins();
        }
        homeTeam.addHomeGame();
        awayTeam.addAwayGame();
    }

    public static void calculatePlayoffSeries() {
        List<Team> teams = SetupUtils.getTeams();
        Scanner scanner = new Scanner(System.in);
        int awayTeam;
        int homeTeam;
        SetupUtils.display();
        System.out.println("Please enter the number of the away team.");
        awayTeam = scanner.nextInt() - 1;
        System.out.println("Please enter the number of the home team.");
        homeTeam = scanner.nextInt() - 1;
        for(int i = 0; i < 7; i++) {
            CalculationUtils.calculateMatch(new Matchup(teams.get(awayTeam), teams.get(homeTeam),2));
        }
        teams.get(awayTeam).displayRecord();
        teams.get(homeTeam).displayRecord();
        scanner.close();
    }
}
