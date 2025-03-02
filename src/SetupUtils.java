import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SetupUtils {
    private static final List<String> listOfTeamNames = new ArrayList<>();
    private static final List<Team> teams = new ArrayList<>();
    public static Team parseTeam(String path) {
        Team newTeam = null;
        String teamName;
        String conference;
        String division;
        String coach;
        try {
            Scanner scanner = new Scanner(new File(path));
            teamName = scanner.nextLine();
            conference = scanner.nextLine();
            division = scanner.nextLine();
            coach = scanner.nextLine();
            newTeam = new Team(teamName, conference, division, coach);
            while (scanner.hasNextLine()) {
                newTeam.addPlayer(parsePlayer(scanner.nextLine(), newTeam));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return newTeam;
    }

    public static Player parsePlayer(String line, Team team) {
        String[] parts = line.split(",");
        Player player = new Player(parts[0], team, Integer.parseInt(parts[1]), Integer.parseInt(parts[2]),
                                Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]),
                                Integer.parseInt(parts[6]), Integer.parseInt(parts[7]), Integer.parseInt(parts[8]));

        return player;
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
        List<Team> atlantic = new ArrayList<>();
        List<Team> central = new ArrayList<>();
        List<Team> southeast = new ArrayList<>();
        List<Team> northwest = new ArrayList<>();
        List<Team> pacific = new ArrayList<>();
        List<Team> southwest = new ArrayList<>();
        for(Team team : teams) {
            if(team.getDivision().equals("Atlantic")) {
                atlantic.add(team);
            } else if(team.getDivision().equals("Central")) {
                central.add(team);
            } else if(team.getDivision().equals("Southeast")) {
                southeast.add(team);
            } else if(team.getDivision().equals("Northwest")) {
                northwest.add(team);
            } else if(team.getDivision().equals("Pacific")) {
                pacific.add(team);
            } else if(team.getDivision().equals("Southwest")) {
                southwest.add(team);
            }
        }
        //creates all east vs west matchups
        createWestEastMatchups(atlantic, northwest, pacific, southwest);
        createWestEastMatchups(central, northwest, pacific, southwest);
        createWestEastMatchups(southeast, northwest, pacific, southwest);

        //creates all same division matchups
        createDivisionMatchups(atlantic);
        createDivisionMatchups(central);
        createDivisionMatchups(southeast);
        createDivisionMatchups(northwest);
        createDivisionMatchups(pacific);
        createDivisionMatchups(southwest);

        //creates all same conference non-divisional matchups
        createNonDivisionMatchups(atlantic, central, southeast);
        createNonDivisionMatchups(central, atlantic, southeast);
        createNonDivisionMatchups(southeast, central, atlantic);
        createNonDivisionMatchups(northwest, pacific, southwest);
        createNonDivisionMatchups(pacific, northwest, southwest);
        createNonDivisionMatchups(southwest, pacific, northwest);
    }

    public static void createWestEastMatchups(List<Team> eastDivision, List<Team> northwest, List<Team> pacific, List<Team> southwest) {
        for(Team eastTeam : eastDivision) {
            for(Team westTeam : northwest) {
                createIndividualMatchup(eastTeam, westTeam, 2);
            }
            for(Team westTeam : pacific) {
                createIndividualMatchup(eastTeam, westTeam, 2);
            }
            for(Team westTeam : southwest) {
                createIndividualMatchup(eastTeam, westTeam, 2);
            }
        }
    }

    public static void createDivisionMatchups(List<Team> division) {
        for(int i = 0; i < division.size(); i++) {
            Team currentTeam = division.get(i);
            for(int j = 1; j < division.size(); j++) {
                Team opponent = division.get(j);
                if(!currentTeam.equals(opponent)) {
                    createIndividualMatchup(currentTeam, opponent, 4);
                }
            }
        }
    }

    public static void createNonDivisionMatchups(List<Team> currentDivision, List<Team> otherDivisionOne, List<Team> otherDivisionTwo) {
        int size = currentDivision.size();
        for(int i = 0; i < size; i++) {
            Team currentTeam = currentDivision.get(i);
            Team opponent = otherDivisionOne.get(i % 5);
            createFourGameMatchup(currentTeam, opponent);
            opponent = otherDivisionTwo.get(i % 5);
            createFourGameMatchup(currentTeam, opponent);
            opponent = otherDivisionOne.get((i + 1) % 5);
            createFourGameMatchup(currentTeam, opponent);
            opponent = otherDivisionTwo.get((i + 1) % 5);
            createFourGameMatchup(currentTeam, opponent);
            opponent = otherDivisionOne.get((i + 2) % 5);
            createFourGameMatchup(currentTeam, opponent);
            opponent = otherDivisionTwo.get((i + 2) % 5);
            createFourGameMatchup(currentTeam, opponent);
            opponent = otherDivisionOne.get((i + 3) % 5);
            createThreeGameMatchup(currentTeam, opponent);
            opponent = otherDivisionTwo.get((i + 3) % 5);
            createThreeGameMatchup(currentTeam, opponent);
            opponent = otherDivisionOne.get((i + 4) % 5);
            createThreeGameMatchup(opponent, currentTeam);
            opponent = otherDivisionTwo.get((i + 4) % 5);
            createThreeGameMatchup(opponent, currentTeam);
        }
    }

    public static void createFourGameMatchup(Team currentTeam, Team opponent) {
        if (createIndividualMatchup(currentTeam, opponent, 4)) {
            currentTeam.addNonDivFourGameMatchup();
            opponent.addNonDivFourGameMatchup();
        }
    }

    public static void createThreeGameMatchup(Team currentTeam, Team opponent) {
        if(createIndividualMatchup(currentTeam, opponent, 3)) {
            currentTeam.addNonDivThreeGameMatchup();
            opponent.addNonDivThreeGameMatchup();
        }
    }

    public static boolean createIndividualMatchup(Team currentTeam, Team opponent, int gamesToPlay) {
        if(!currentTeam.matchupExists(opponent)) {
            Matchup matchup = new Matchup(currentTeam, opponent, gamesToPlay);
            currentTeam.addMatchup(matchup);
            opponent.addMatchup(matchup);
            return true;
        }
        return false;
    }
}
