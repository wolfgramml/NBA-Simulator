public class Statline {
    private int points;
    private int rebounds;
    private int assists;

    public Statline(int points, int rebounds, int assists) {
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
    }

    public int getPoints() { return points; }
    public int getRebounds() { return rebounds; }
    public int getAssists() { return assists; }

    public void addPoints(int points) { this.points += points; }
    public void addRebounds(int rebounds) { this.rebounds += rebounds; }
    public void addAssists(int assists) { this.assists += assists; }

    @Override
    public String toString() {
        return "PTS: " + points + ", REB: " + rebounds + ", AST: " + assists;
    }
}
