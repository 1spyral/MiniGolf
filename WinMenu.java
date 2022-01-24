import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import javax.imageio.ImageIO;

// Menu that is displayed when the player wins the game
public class WinMenu extends Menu {
    // The number of moves that the player used to win, lower is better
    int score;
    // The win logo
    BufferedImage logo;
    // Display font for the menu title
    Font titleFont;
    // Button to return to the starting menu
    Button backButton;
    WinMenu(StateMachine stateMachine) throws IOException, FontFormatException {
        super(stateMachine);
        logo = ImageIO.read(new File("logo.png"));
        titleFont = (new FontLoader()).title;
        backButton = new Button(Const.WIDTH / 2 - 100, 800, 200, 150, "back", Color.BLUE, StateChanger.winToStart, stateMachine);
    }
    public void draw(Graphics g) {
        g.setColor(new Color(0, 100, 0));
        // Draw the background
        g.fillRect(0, 0, Const.WIDTH, Const.HEIGHT);
        g.setColor(Color.BLACK);
        g.setFont(titleFont);
        // Draw the win text
        g.drawString("You won in " + score + " moves", Const.WIDTH / 2 - g.getFontMetrics().charWidth('a') * "Choose Difficulty".length() / 2, 200);
        // Draw the win logo
        g.drawImage(logo, Const.WIDTH / 2 - 150, 300, null);
        // Draw the back button
        backButton.draw(g);
    }
    public void update(Set<Integer> keysPressed, boolean mouseDown, int mouseX, int mouseY) {
        // Update the back button
        backButton.update(mouseDown, mouseX, mouseY);
    }
    // Method to change the user score
    public void setScore(int score) {
        this.score = score;
    }
}
