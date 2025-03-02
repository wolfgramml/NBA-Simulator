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
    private String position; // Not yet implemented
    private int yearsExperience; // won't implement until working on multiple seasons
    private int age; // Probably won't implement until working on multiple seasons

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
    }

    public void display() {
        System.out.println(name);
    }
}
