import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Set;

// Starting menu of the game
public class StartMenu extends Menu {
    // Button to start the game
    Button startButton;
    // Button to view high scores
    Button leaderboardButton;
    // The game logo
    BufferedImage title;
    StartMenu(StateMachine stateMachine) throws IOException, FontFormatException {
        super(stateMachine);
        startButton = new Button(200, 300, 880, 300, "start", Color.BLUE, StateChanger.startToSelect, stateMachine);
        leaderboardButton = new Button(200, 650, 880, 300, "high scores", Color.blue, StateChanger.leaderboard, stateMachine);
        title = ImageIO.read(new File("Title.png"));
    }
    public void draw(Graphics g) {
        g.setColor(new Color(0, 100, 0));
        // Draw background
        g.fillRect(0, 0, Const.WIDTH, Const.HEIGHT);
        // Draw start button
        startButton.draw(g);
        // Draw leaderboard button
        leaderboardButton.draw(g);
        // Draw game logo
        g.drawImage(title, 342, 100, null);
    }
    public void update(Set<Integer> keysPressed, boolean mouseDown, int mouseX, int mouseY) {
        // Update the start button
        startButton.update(mouseDown, mouseX, mouseY);
        // Update the leaderboard button
        leaderboardButton.update(mouseDown, mouseX, mouseY);
    }
}
