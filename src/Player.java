public class Player {
    private String name;
    private Team team;
    private int offenseRating;
    private int defenseRating;
    private String position;
    private int yearsExperience; // won't implement until working on multiple seasons
    private int age; // Probably won't implement until working on multiple seasons

    public Player(String name, Team team) {
        this.name = name;
        this.team = team;
    }

    public Player(String name, Team team, int offenseRating, int defenseRating) {
        this.name = name;
        this.team = team;
        this.offenseRating = offenseRating;
        this.defenseRating = defenseRating;
    }

}
