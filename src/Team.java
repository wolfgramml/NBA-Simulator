public class Team {
    private String name;
    private Player[] players;
    private int wins = 0;
    private int losses = 0;
    private String conference;
    private String division;

    public Team(String name) {
        this.name = name;
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
        System.out.println(name);
    }
}
