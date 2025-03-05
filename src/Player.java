import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Player {
    private String name;
    private Team team;
    private int outsideScoring;
    private int insideScoring;
    private int athleticism;
    private int playmaking;
    private int defense;
    private int rebounding;
    private int intangibles;
    private int potential;
    private double overallRatingScore;
    private String position; // Not yet implemented
    private int yearsExperience; // won't implement until working on multiple seasons
    private int age; // Probably won't implement until working on multiple seasons
    private List<Statline> gameStats = new ArrayList<>();

    public Player(String name, Team team) {
        this.name = name;
        this.team = team;
    }

    public Player(String name, Team team, int outsideScoring, int insideScoring, int athleticism, int rebounding,
                    int playmaking, int defense, int intangibles, int potential) {
        this.name = name;
        this.team = team;
        this.outsideScoring = outsideScoring;
        this.insideScoring = insideScoring;
        this.athleticism = athleticism;
        this.playmaking = playmaking;
        this.defense = defense;
        this.rebounding = rebounding;
        this.intangibles = intangibles;
        this.potential = potential;
        this.overallRatingScore = calculateOverallRating();
    }

    public double getOverallRating() {
        return overallRatingScore;
    }

    private double calculateOverallRating() {
        return (outsideScoring * .25) + (insideScoring * .2) + (athleticism * .15) + (playmaking * .15) + (defense * .15) + (rebounding * .05) + (intangibles * .05);
    }

    public void calculateStats(int minutes) {
        Random rand = new Random();
    
        // Scale factors for attributes
        double scoringFactor = (outsideScoring * 0.6 + insideScoring * 0.4) / 100.0;
        double assistFactor = (playmaking * 0.7 + athleticism * 0.3) / 100.0;
        double reboundFactor = (rebounding * 0.6 + athleticism * 0.4) / 100.0;
    
        // Base per-minute stats with some randomness
        double pointsPerMinute = (scoringFactor * rand.nextDouble() * 0.5 + 0.75) * (minutes / 36.0) * 25; 
        double assistsPerMinute = (assistFactor * rand.nextDouble() * 0.5 + 0.5) * (minutes / 36.0) * 10;
        double reboundsPerMinute = (reboundFactor * rand.nextDouble() * 0.5 + 0.5) * (minutes / 36.0) * 12;
    
        int points = (int) Math.round(pointsPerMinute);
        int assists = (int) Math.round(assistsPerMinute);
        int rebounds = (int) Math.round(reboundsPerMinute);
    
        Statline statline = new Statline(points, rebounds, assists);
        gameStats.add(statline);
    }

    public void displayPerGameStats() {
        int gamesPlayed = gameStats.size(); // Directly get size instead of incrementing
        if (gamesPlayed == 0) { 
            System.out.println(name);
            System.out.println("No games played yet.");
            return;
        }

        int totalPoints = 0;
        int totalRebounds = 0;
        int totalAssists = 0;
        for (Statline statline : gameStats) {
            totalPoints += statline.getPoints();
            totalRebounds += statline.getRebounds();
            totalAssists += statline.getAssists();
        }

        double pointsPerGame = (double) totalPoints / gamesPlayed;
        double reboundsPerGame = (double) totalRebounds / gamesPlayed;
        double assistsPerGame = (double) totalAssists / gamesPlayed;

        System.out.println(name);
        System.out.println("Points per game: " + pointsPerGame);
        System.out.println("Rebounds per game: " + reboundsPerGame);
        System.out.println("Assists per game: " + assistsPerGame);
    }

    public Statline getLastGameStats() {
        return gameStats.get(gameStats.size() - 1);
    }

    public void display() {
        System.out.println(name);
    }
}
