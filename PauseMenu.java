import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Set;

public class PauseMenu extends Menu {
    boolean pauseHeld;
    Button exitButton;

    Font font;
    PauseMenu(StateMachine stateMachine) throws IOException, FontFormatException {
        super(stateMachine);
        this.pauseHeld = false;
        this.exitButton = new Button(Const.WIDTH / 2 - 150, Const.HEIGHT / 2 - 50, 300, 175, "quit", Color.GREEN, StateChanger.pauseToStart, this.stateMachine);

        this.font = (new FontLoader()).title;
    }
    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 127));
        g.fillRect(0, 0, Const.WIDTH, Const.HEIGHT);
        g.setColor(Color.BLUE);
        g.fillRect(Const.WIDTH / 2 - 200, Const.HEIGHT / 2 - 200, 400, 400);
        g.setFont(font);
        g.setColor(Color.BLACK);
        g.drawString("Paused", Const.WIDTH / 2 - g.getFontMetrics().charWidth('a') * "Paused".length() / 2, Const.HEIGHT / 2 - 100);
        exitButton.draw(g);
    }

    public void update(Set<Integer> keysPressed, boolean mouseDown, int mouseX, int mouseY) {
        if (keysPressed.contains(KeyEvent.VK_ESCAPE) && !pauseHeld) {
            pauseHeld = true;
        } else if (!keysPressed.contains(KeyEvent.VK_ESCAPE) && pauseHeld) {
            pauseHeld = false;
            StateChanger.pause.call(this.stateMachine);
        }
        exitButton.update(mouseDown, mouseX, mouseY);
    }
}
