import java.awt.*;
import java.io.IOException;
import java.util.Set;

// Menu that lets the player choose a difficulty
public class SelectMenu extends Menu {
    // Button to choose easy mode
    Button easyButton;
    // Button to choose medium mode
    Button mediumButton;
    // Button to choose hard mode
    Button hardButton;
    // Display font for the title
    Font font;
    SelectMenu(StateMachine stateMachine) throws IOException, FontFormatException {
        super(stateMachine);
        easyButton = new Button(150, 600, 250, 250, "Easy", Color.BLUE, StateChanger.easy, stateMachine);
        mediumButton = new Button(477, 600, 250, 250, "Medium", Color.BLUE, StateChanger.medium, stateMachine);
        hardButton = new Button(804, 600, 250, 250, "Hard", Color.BLUE, StateChanger.hard, stateMachine);

        font = (new FontLoader()).title;
    }
    public void draw(Graphics g) {
        g.setColor(new Color(0, 100, 0));
        // Draw background
        g.fillRect(0, 0, Const.WIDTH, Const.HEIGHT);
        g.setColor(Color.BLACK);
        g.setFont(font);
        // Display title text
        g.drawString("Choose Difficulty", Const.WIDTH / 2 - g.getFontMetrics().charWidth('a') * "Choose Difficulty".length() / 2, 400);
        // Draw easy mode button
        easyButton.draw(g);
        // Draw medium mode button
        mediumButton.draw(g);
        // Draw hard mode button
        hardButton.draw(g);
    }
    public void update(Set<Integer> keysPressed, boolean mouseDown, int mouseX, int mouseY) {
        // Update easy mode button
        easyButton.update(mouseDown, mouseX, mouseY);
        // Update medium mode button
        mediumButton.update(mouseDown, mouseX, mouseY);
        // Update hard mode button
        hardButton.update(mouseDown, mouseX, mouseY);
    }
}
