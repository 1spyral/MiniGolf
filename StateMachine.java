import java.util.Set;
import java.awt.*;

public class StateMachine implements Drawable {
    StartMenu startMenu;
    GameMenu gameMenu;
    PauseMenu pauseMenu;

    boolean start;
    boolean game;
    boolean pause;

    StateMachine() {
        start = true;
        game = false;
        pause = false;
    }
    public void draw(Graphics g) {
        
    }
    public void update(Set<Integer> keysPressed, boolean mouseDown, int mouseX, int mouseY) {

    }
}
