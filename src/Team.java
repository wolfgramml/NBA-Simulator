import java.util.ArrayList;
import java.util.List;

public class Team {
    private String name;
    private Player[] players;
    private int wins = 0;
    private int losses = 0;
    private String conference;
    private String division;
    private String coach;
    private List<Matchup> listOfMatchups = new ArrayList<>();
    private int nonDivFourGames = 0;
    private int nonDivThreeGames = 0;
    private int homeGamesPlayed = 0;
    private int awayGamesPlayed = 0;

    public Team(String name, String conference, String division) {
        this.name = name;
        this.conference = conference;
        this.division = division;
    }

    public Team(String name, Player[] players) {
        this.name = name;
        this.players = players;
    }

    public void updateWins() {
        wins += 1;
    }

    public void updateLosses() {
        losses += 1;
    }

    public void display() {
        System.out.println("The " + name + " are in the " + conference + " Conference in the "
            + division + " division.");
    }

    public void displayRecord() {
        System.out.println("The " + name + " have a record of " + wins + "-" + losses + ".");
    }

    public void displayMatchups() {
        for(int i = 0; i < listOfMatchups.size(); i++) {
            listOfMatchups.get(i).display();
        }
    }

    public void displayGamesPlayed() {
        System.out.println("The " + name + " have played " + (wins + losses) + " games. " +
                homeGamesPlayed + " home games and " + awayGamesPlayed + " away games.");
    }

    public void displayNonDivisional() {
        System.out.println("The " + name + " have played 4 games against " + nonDivFourGames + " teams and" +
                " 3 games against " + nonDivThreeGames + " teams.");
    }

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public String getConference() {
        return conference;
    }

    public String getDivision() {
        return division;
    }

    public void addNonDivFourGameMatchup() {
        nonDivFourGames++;
    }

    public void addNonDivThreeGameMatchup() {
        nonDivThreeGames++;
    }

    public void addHomeGame() {
        homeGamesPlayed++;
    }

    public void addAwayGame() {
        awayGamesPlayed++;
    }

    public void addMatchup(Matchup matchup) {
        listOfMatchups.add(matchup);
    }

    public void playAllMatchups() {
        for(int i = 0; i < listOfMatchups.size(); i++) {
            while(!listOfMatchups.get(i).allGamesPlayed()) {
                CalculationUtils.calculateMatch(listOfMatchups.get(i));
            }
        }
    }

    public boolean matchupExists(Team otherTeam) {
        for(int i = 0; i < listOfMatchups.size(); i++) {
            if(otherTeam.getName() .equals(listOfMatchups.get(i).getTeamOne().getName()) ||
                    otherTeam.getName().equals(listOfMatchups.get(i).getTeamTwo().getName())) {
                return true;
            }
        }
        return false;
    }

}
