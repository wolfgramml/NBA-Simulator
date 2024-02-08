public class Matchup {
    private final Team teamOne;
    private final Team teamTwo;
    private int teamOneWins;
    private int teamOneLosses;
    private final int gamesToPlay;

    public Matchup(Team teamOne, Team teamTwo, int gamesToPlay) {
        this.teamOne = teamOne;
        this.teamTwo = teamTwo;
        this.gamesToPlay = gamesToPlay;
    }

    public void updateWins() {
        teamOneWins += 1;
    }

    public void updateLosses() {
        teamOneLosses += 1;
    }

    public void display() {
        System.out.println(teamOne.getName() + " vs " + teamTwo.getName());
    }

    public Team getTeamOne() {
        return teamOne;
    }

    public Team getTeamTwo() {
        return teamTwo;
    }

    public boolean allGamesPlayed() {
        return gamesToPlay <= (teamOneWins + teamOneLosses);
    }

}
