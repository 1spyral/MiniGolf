import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Set;

// Menu that is displayed when the game is paused
public class PauseMenu extends Menu {
    // Whether the pause button is held down (esc)
    boolean pauseHeld;
    // Button for quitting the game
    Button exitButton;
    // Display font for pause text
    Font font;
    PauseMenu(StateMachine stateMachine) throws IOException, FontFormatException {
        super(stateMachine);
        this.pauseHeld = false;
        this.exitButton = new Button(Const.WIDTH / 2 - 150, Const.HEIGHT / 2 - 50, 300, 175, "quit", Color.GREEN, StateChanger.pauseToStart, this.stateMachine);

        this.font = (new FontLoader()).title;
    }
    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 127));
        // Blur the background
        g.fillRect(0, 0, Const.WIDTH, Const.HEIGHT);
        g.setColor(Color.YELLOW);
        // Draw the pause window
        g.fillRoundRect(Const.WIDTH / 2 - 200, Const.HEIGHT / 2 - 200, 400, 400, 30, 30);
        g.setColor(Color.BLACK);
        // Draw the outline of the pause window
        g.drawRoundRect(Const.WIDTH / 2 - 200, Const.HEIGHT / 2 - 200, 400, 400, 30, 30);
        g.setFont(font);
        g.setColor(Color.BLACK);
        // Display pause text
        g.drawString("Paused", Const.WIDTH / 2 - g.getFontMetrics().charWidth('a') * "Paused".length() / 2, Const.HEIGHT / 2 - 100);
        // Draw the quit button
        exitButton.draw(g);
    }
    public void update(Set<Integer> keysPressed, boolean mouseDown, int mouseX, int mouseY) {
        // Check if the pause button is held (esc)
        if (keysPressed.contains(KeyEvent.VK_ESCAPE) && !pauseHeld) {
            pauseHeld = true;
        } 
        // Check if the user activates the pause button (esc)
        else if (!keysPressed.contains(KeyEvent.VK_ESCAPE) && pauseHeld) {
            pauseHeld = false;
            // Unpause the game
            StateChanger.pause.call(this.stateMachine);
        }
        // Update the quit button
        exitButton.update(mouseDown, mouseX, mouseY);
    }
}
