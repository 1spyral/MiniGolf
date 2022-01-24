import java.awt.*;
import java.util.Set;

// Abstract class for menu states
public abstract class Menu implements Drawable {
    // The StateMachine that the menu belongs to
    StateMachine stateMachine;
    Menu(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }
    public abstract void draw(Graphics g);
    public abstract void update(Set<Integer> keysPressed, boolean mouseDown, int mouseX, int mouseY);
}
