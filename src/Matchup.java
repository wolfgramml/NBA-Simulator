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

    public Team getHomeTeam() {
        if((teamOneWins + teamOneLosses) % 2 == 0) {
            return teamOne;
        } else {
            return teamTwo;
        }
    }

    public Team getAwayTeam() {
        if((teamOneWins + teamOneLosses) % 2 == 0) {
            return teamTwo;
        } else {
            return teamOne;
        }
    }

    public boolean allGamesPlayed() {
        return gamesToPlay <= (teamOneWins + teamOneLosses);
    }

}
