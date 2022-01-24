import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.PrintWriter;

// Class to update high scores
public class ScoreUpdater {
    // Player score
    int score;
    // Difficulty of the game
    int difficulty;
    // Player name
    String name;
    ScoreUpdater(int score, int difficulty, String name) {
        this.score = score;
       this.difficulty = difficulty;
       this.name = name;
    }
    // Update the highscores
    public void update() throws FileNotFoundException {
        File file = new File("scores.txt");
        Scanner input = new Scanner(file);
        int[] scores = new int[4];
        String[] names = new String[4];
        // Get current highscores for each difficulty
        for (int i = 1; i <= 3 && input.hasNext(); i++) {
            scores[i] = input.nextInt();
            names[i] = input.next();
        }
        input.close();
        // Check if the player score is higher than the current high score
        if (score < scores[difficulty]) {
            // Update the new high score
            scores[difficulty] = score;
            names[difficulty] = name;
        }
        PrintWriter output = new PrintWriter(file);
        // Update the store file
        for (int i = 1; i <= 3; i++) {
            output.println(scores[i] + " " + names[i]);
        }
        output.close();
   }
}
