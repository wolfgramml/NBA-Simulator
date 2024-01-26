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
        try {
            Scanner scanner = new Scanner(new File(path));
            teamName = scanner.nextLine();
            newTeam = new Team(teamName);
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
            teams.get(i).display();
        }
    }
}
