import java.awt.*;
import java.io.IOException;
import java.util.Set;
public class SelectMenu extends Menu {
    Button easyButton;
    Button mediumButton;
    Button hardButton;

    Font font;
    SelectMenu(StateMachine stateMachine) throws IOException, FontFormatException {
        super(stateMachine);
        easyButton = new Button(150, 600, 250, 250, "Easy", Color.BLUE, StateChanger.easy, stateMachine);
        mediumButton = new Button(477, 600, 250, 250, "Medium", Color.BLUE, StateChanger.medium, stateMachine);
        hardButton = new Button(804, 600, 250, 250, "Hard", Color.BLUE, StateChanger.hard, stateMachine);

        font = (new FontLoader()).title;
    }
    public void draw(Graphics g) {
        g.setFont(font);
        g.drawString("Choose Difficulty", Const.WIDTH / 2 - g.getFontMetrics().charWidth('a') * "Choose Difficulty".length() / 2, 200);
        easyButton.draw(g);
        mediumButton.draw(g);
        hardButton.draw(g);
    }
    public void update(Set<Integer> keysPressed, boolean mouseDown, int mouseX, int mouseY) {
        easyButton.update(mouseDown, mouseX, mouseY);
        mediumButton.update(mouseDown, mouseX, mouseY);
        hardButton.update(mouseDown, mouseX, mouseY);
    }
}
