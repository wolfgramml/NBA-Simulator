import java.util.ArrayList;
import java.util.Collections;
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
        List<Player> homePlayers = getTopPlayers(homeTeam.getPlayers());
        List<Player> awayPlayers = getTopPlayers(awayTeam.getPlayers());

        List<Integer> homeMinutes = assignMinutes(homePlayers);
        List<Integer> awayMinutes = assignMinutes(awayPlayers);

        for (int i = 0; i < homePlayers.size(); i++) {
            homePlayers.get(i).calculateStats(homeMinutes.get(i));
        }

        for (int i = 0; i < awayPlayers.size(); i++) {
            awayPlayers.get(i).calculateStats(awayMinutes.get(i));
        }

        Statline homeStatline = new Statline(0, 0, 0);
        Statline awayStatline = new Statline(0, 0, 0);

        for (int i = 0; i < homePlayers.size(); i++) {
            homeStatline.addPoints(homePlayers.get(i).getLastGameStats().getPoints());
            homeStatline.addRebounds(homePlayers.get(i).getLastGameStats().getRebounds());
            homeStatline.addAssists(homePlayers.get(i).getLastGameStats().getAssists());
        }
        homeTeam.addStatline(homeStatline);

        for (int i = 0; i < awayPlayers.size(); i++) {
            awayStatline.addPoints(awayPlayers.get(i).getLastGameStats().getPoints());
            awayStatline.addRebounds(awayPlayers.get(i).getLastGameStats().getRebounds());
            awayStatline.addAssists(awayPlayers.get(i).getLastGameStats().getAssists());
        }
        awayTeam.addStatline(awayStatline);

        int pointsDiff = homeStatline.getPoints() - awayStatline.getPoints();

        if (pointsDiff > 0) {
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

    private static List<Player> getTopPlayers(List<Player> players) {
        players.sort((p1, p2) -> Double.compare(p2.getOverallRating(), p1.getOverallRating()));
        return players;
    }

    private static List<Integer> assignMinutes(List<Player> players) {
    List<Integer> minutes = new ArrayList<>(Collections.nCopies(players.size(), 0));

    for (int i = 0; i < players.size(); i++) {
        if (i < 5) {
            minutes.set(i, 36);
        } else if (i < 10) {
            minutes.set(i, 12);
        }
    }

    return minutes;
}
}
