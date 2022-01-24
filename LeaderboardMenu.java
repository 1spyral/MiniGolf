import java.awt.*;
import java.io.IOException;
import java.io.File;
import java.util.Set;
import java.util.Scanner;

// Menu that displays the high scores of the player
public class LeaderboardMenu extends Menu {
    // Display font for the title text
    Font titleFont;
    // Display font for the difficulty text
    Font difficultyFont;
    // Display font for the score text
    Font scoreFont;
    // Button to return to the main menu
    Button backButton;
    LeaderboardMenu(StateMachine stateMachine) throws IOException, FontFormatException {
        super(stateMachine);
        titleFont = (new FontLoader()).title;
        difficultyFont = (new FontLoader()).medium;
        scoreFont = (new FontLoader()).small;
        backButton = new Button(Const.WIDTH / 2 - 100, 800, 200, 150, "back", Color.BLUE, StateChanger.leaderboard, stateMachine);
    }
    public void draw(Graphics g) {
        g.setColor(new Color(0, 100, 0));
        g.fillRect(0, 0, Const.WIDTH, Const.HEIGHT);
        // Draw the return button
        backButton.draw(g);
        g.setColor(Color.green);
        g.fillRoundRect(240, 400, 200, 300, 25, 25);
        g.fillRoundRect(540, 400, 200, 300, 25, 25);
        g.fillRoundRect(840, 400, 200, 300, 25, 25);
        g.setFont(titleFont);
        g.setColor(Color.BLACK);
        // Display the title text
        g.drawString("High Scores", Const.WIDTH / 2 - g.getFontMetrics().charWidth('a') * "High Scores".length() / 2, 300);
        g.setFont(difficultyFont);
        g.setColor(Color.black);
        // Display the difficulty texts
        g.drawString("Easy", 240 + 100 - g.getFontMetrics().charWidth('a') * "Easy".length() / 2, 450);
        g.drawString("Medium", 540 + 100 - g.getFontMetrics().charWidth('a') * "Medium".length() / 2, 450);
        g.drawString("Hard", 840 + 100 - g.getFontMetrics().charWidth('a') * "Hard".length() / 2, 450);
        g.setFont(scoreFont);
        File file = new File("scores.txt");
        // Create a dummy Scanner using System.in and then close it, to prevent data leaks
        Scanner input = new Scanner(System.in);
        input.close();
        try {
            input = new Scanner(file);
        } catch (Exception e) {}
        int[] scores = new int[4];
        String[] names = new String[4];
        for (int i = 1; i <= 3 && input.hasNext(); i++) {
            scores[i] = input.nextInt();
            names[i] = input.next();
        }
        input.close();
        // Display the high score texts
        g.drawString(names[1] + " " + scores[1], 240 + 100 - g.getFontMetrics().charWidth('a') * (names[1] + " " + scores[1]).length() / 2, 550);
        g.drawString(names[2] + " " + scores[2], 540 + 100 - g.getFontMetrics().charWidth('a') * (names[2] + " " + scores[2]).length() / 2, 550);
        g.drawString(names[3] + " " + scores[3], 840 + 100 - g.getFontMetrics().charWidth('a') * (names[3] + " " + scores[3]).length() / 2, 550);

    }
    public void update(Set<Integer> keysPressed, boolean mouseDown, int mouseX, int mouseY) {
        // Check if the return button is pressed
        backButton.update(mouseDown, mouseX, mouseY);
    }
    
}
