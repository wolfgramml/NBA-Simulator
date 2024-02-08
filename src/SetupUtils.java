import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SetupUtils {
    private static List<String> listOfTeamNames = new ArrayList<>();
    private static List<Team> teams = new ArrayList<>();
    public static Team parseTeam(String path) {
        Team newTeam = null;
        String teamName;
        String conference;
        String division;
        try {
            Scanner scanner = new Scanner(new File(path));
            teamName = scanner.nextLine();
            conference = scanner.nextLine();
            division = scanner.nextLine();
            newTeam = new Team(teamName, conference, division);
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

        return newTeam;
    }

    public static void addTeams() {
        listOfTeamNames.add("src/teams/76ers.txt");
        listOfTeamNames.add("src/teams/bucks.txt");
        listOfTeamNames.add("src/teams/bulls.txt");
        listOfTeamNames.add("src/teams/cavaliers.txt");
        listOfTeamNames.add("src/teams/celtics.txt");
        listOfTeamNames.add("src/teams/clippers.txt");
        listOfTeamNames.add("src/teams/grizzlies.txt");
        listOfTeamNames.add("src/teams/hawks.txt");
        listOfTeamNames.add("src/teams/heat.txt");
        listOfTeamNames.add("src/teams/hornets.txt");
        listOfTeamNames.add("src/teams/jazz.txt");
        listOfTeamNames.add("src/teams/kings.txt");
        listOfTeamNames.add("src/teams/knicks.txt");
        listOfTeamNames.add("src/teams/lakers.txt");
        listOfTeamNames.add("src/teams/magic.txt");
        listOfTeamNames.add("src/teams/mavericks.txt");
        listOfTeamNames.add("src/teams/nets.txt");
        listOfTeamNames.add("src/teams/nuggets.txt");
        listOfTeamNames.add("src/teams/pacers.txt");
        listOfTeamNames.add("src/teams/pelicans.txt");
        listOfTeamNames.add("src/teams/pistons.txt");
        listOfTeamNames.add("src/teams/raptors.txt");
        listOfTeamNames.add("src/teams/rockets.txt");
        listOfTeamNames.add("src/teams/spurs.txt");
        listOfTeamNames.add("src/teams/suns.txt");
        listOfTeamNames.add("src/teams/thunder.txt");
        listOfTeamNames.add("src/teams/timberwolves.txt");
        listOfTeamNames.add("src/teams/trailblazers.txt");
        listOfTeamNames.add("src/teams/warriors.txt");
        listOfTeamNames.add("src/teams/wizards.txt");
    }

    public static void createTeams() {
        for(int i = 0; i < listOfTeamNames.size(); i++) {
            teams.add(parseTeam(listOfTeamNames.get(i)));
        }
    }

    public static void display() {
        for(int i = 0; i < teams.size(); i++) {
            System.out.print("(team " + (i+1) + ") ");
            teams.get(i).display();
        }
    }

    public static List<Team> getTeams() {
        return teams;
    }

    public static void createAllMatchups() {
        for(int i = 0; i < 30; i++) {
            Team currentTeam = teams.get(i);
            for(int j = 0; j < 30; j++) {
                Team opponent = teams.get(j);
                if(i != j) {
                    int gamesToPlay = 0;
                    if(!currentTeam.getConference().equals(opponent.getConference())) {
                        gamesToPlay = 2;
                    } else if(currentTeam.getConference().equals(opponent.getConference()) && !currentTeam.getDivision().equals(opponent.getDivision())) {
                        // Should have 4 games against 6 different teams, and 3 games against the other 4 non-division, same conference teams
                        if(true) {
                            gamesToPlay = 4;
                        }
                    } else if(currentTeam.getConference().equals(opponent.getConference()) && currentTeam.getDivision().equals(opponent.getDivision())) {
                        gamesToPlay = 4;
                    } else {
                        throw new IllegalStateException("It should not be possible to reach this line, assuming the data is in the txt files correctly.");
                    }
                    createIndividualMatchup(currentTeam, opponent, gamesToPlay);
                }
            }
        }
    }

    public static void createIndividualMatchup(Team currentTeam, Team opponent, int gamesToPlay) {
        if(!currentTeam.matchupExists(opponent)) {
            Matchup matchup = new Matchup(currentTeam, opponent, gamesToPlay);
            currentTeam.addMatchup(matchup);
            opponent.addMatchup(matchup);
        }
    }
}
