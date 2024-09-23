import java.util.ArrayList;
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

    public static void calculateStandings() {
        final List<Team> allTeams = SetupUtils.getTeams();
        final List<Team> eastTeams = new ArrayList<>();
        final List<Team> westTeams = new ArrayList<>();
        for (final Team team : allTeams) {
            boolean added = false;
            if (team.getConference().equals("Eastern")) {
                for (int i = 0; i < eastTeams.size(); i++) {
                   if(team.getWins() > eastTeams.get(i).getWins()) {
                       eastTeams.add(i, team);
                       added = true;
                       break;
                   }
                }
                if (!added) {
                    eastTeams.add(team);
                }
            } else if (team.getConference().equals("Western")) {
                for (int i = 0; i < westTeams.size(); i++) {
                    if(team.getWins() > westTeams.get(i).getWins()) {
                        westTeams.add(i, team);
                        added = true;
                        break;
                    }
                }
                if (!added) {
                    westTeams.add(team);
                }
            }
        }
        System.out.println("East Standings:");
        for(int i = 1; i <= eastTeams.size(); i++) {
            System.out.print(i + ". ");
            eastTeams.get(i - 1).displayRecord();
        }
        System.out.println("----------------------------------");
        System.out.println("West Standings:");
        for(int i = 1; i <= westTeams.size(); i++) {
            System.out.print(i + ". ");
            westTeams.get(i - 1).displayRecord();
        }
    }
}
