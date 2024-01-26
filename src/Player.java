public class Player {
    private String name;
    private Team team;
    private int offenseRating;
    private int defenseRating;
    private String position;
    private int yearsExperience;
    private int age;

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
