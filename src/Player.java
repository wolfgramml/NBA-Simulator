public class Player {
    private String name;
    private Team team;
    /*
    Offensive rating is calculated by the mean of outside scoring, inside scoring, athleticism,
    playmaking, intangibles, and potential according to 2kratings.com
     */
    private int offenseRating;
    /*
    Defensive rating is calculated by the mean of defending and rebounding on 2kratings.com
     */
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
