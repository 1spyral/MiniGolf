import java.awt.*;
import java.util.Set;
public class GameMenu extends Menu {
    Ball ball;
    Hole hole;
    Ramp[] ramps;
    Sand[] sands;
    // The walls
    GolfCourse golfCourse;
    GameMenu(StateMachine stateMachine) {
        super(stateMachine);
    }
    public void draw(Graphics g) {
        
    }
    public void update(Set<Integer> keysPressed, boolean mouseDown, int mouseX, int mouseY) {
        
    }
    public void build(int difficulty) {

    }
}
