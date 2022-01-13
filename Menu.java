import java.awt.*;
import java.util.Set;
public abstract class Menu implements Drawable {
    StateMachine stateMachine;
    Menu(StateMachine stateMachine) {
        this.stateMachine = stateMachine;
    }
    public abstract void draw(Graphics g);
    public abstract void update(Set<Integer> keysPressed, boolean mouseDown, int mouseX, int mouseY);
}
