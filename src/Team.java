public class Team {
    private String name;
    private Player[] players;
    private int wins = 0;
    private int losses = 0;
    private String conference;
    private String division;
    private String coach;

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
}
