import java.util.Random;

public class CalculationUtils {

    public static void calculateMatch(Team awayTeam, Team homeTeam) {
        int val;
        Random rand = new Random();
        val = rand.nextInt();
        if (val % 2 == 0) {
            homeTeam.updateWins();
            awayTeam.updateLosses();
        } else {
            homeTeam.updateLosses();
            awayTeam.updateWins();
        }
    }
}
