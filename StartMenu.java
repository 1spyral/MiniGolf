import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.Set;
public class StartMenu extends Menu {
    Button startButton;
    BufferedImage title;
    StartMenu(StateMachine stateMachine) throws IOException, FontFormatException {
        super(stateMachine);
        startButton = new Button(200, 600, 880, 300, "start", Color.BLUE, StateChanger.startToSelect, stateMachine);
        title = ImageIO.read(new File("Title.png"));
    }
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, Const.WIDTH, Const.HEIGHT);
        startButton.draw(g);
        g.drawImage(title, 342, 100, null);
    }
    public void update(Set<Integer> keysPressed, boolean mouseDown, int mouseX, int mouseY) {
        startButton.update(mouseDown, mouseX, mouseY);
    }
}
