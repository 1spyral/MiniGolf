import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Set;
public class GameMenu extends Menu {
    // The walls
    GolfCourse golfCourse;
    Ball ball;
    Hole hole;
    Ramp[] ramps;
    Sand[] sands;

    boolean pauseHeld;
    GameMenu(StateMachine stateMachine) {
        super(stateMachine);
        pauseHeld = false;
    }
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(0, 0, Const.WIDTH, Const.HEIGHT);
        for (Ramp ramp: ramps) {
            ramp.draw(g);
        }
        for (Sand sand: sands) {
            sand.draw(g);
        }
        golfCourse.draw(g);
        hole.draw(g);
        ball.draw(g);
    }
    public void update(Set<Integer> keysPressed, boolean mouseDown, int mouseX, int mouseY) {
        if (keysPressed.contains(KeyEvent.VK_ESCAPE) && !pauseHeld) {
            pauseHeld = true;
        } else if (!keysPressed.contains(KeyEvent.VK_ESCAPE) && pauseHeld) {
            pauseHeld = false;
            StateChanger.pause.call(this.stateMachine);
        }
    }
    public void build(int difficulty) {
        if (difficulty == 1) {
            // Wall coords of the golf course: (50, 50) -> (850, 50) -> (850, 900) -> (600, 900) -> (600, 250) -> (400, 250) -> (400, 750), (50, 750)
            golfCourse = new GolfCourse(new int[]{50, 850, 850, 600, 600, 400, 400, 50}, new int[]{50, 50, 900, 900, 250, 250, 750, 750}, 8);
            // Ball coords: (725, 825)
            ball = new Ball(this, 725, 825);
            // Hole coords: (225, 500)
            hole = new Hole(225, 500);
            // Sand coords: ((50, 50), (250, 50), (250, 175), (50, 175)), ((600, 350), (650, 350), (650, 900), (600, 900))
            sands = new Sand[]{new Sand(new int[]{50, 250, 250, 50}, new int[]{50, 50, 175, 175}, 4), new Sand(new int[]{600, 650, 650, 600}, new int[]{350, 350, 900, 900}, 4)};
            // Ramp coords: ((425, 50), (550, 50), (550, 150), (425, 150)), ((50, 625), (400, 625), (400, 750), (50, 750)), ((725, 50), (850, 50), (850, 250), (725, 250))
            ramps = new Ramp[]{new Ramp(new int[]{425, 550, 550, 425}, new int[]{50, 50, 150, 150}, 4, 2), new Ramp(new int[]{50, 400, 400, 50}, new int[]{625, 625, 750, 750}, 4, 1), new Ramp(new int[]{725, 850, 850, 725}, new int[]{50, 50, 250, 250}, 4, 4)};
        } else if (difficulty == 2) {
            // Wall coords of the golf course :
            golfCourse = new GolfCourse(new int[]{150, 850, 850, 750, 750, 850, 850, 750, 750, 850, 850, 450, 450, 350, 350, 50, 50, 350, 350, 225, 225, 50, 50}, new int[]{50, 50, 150, 150, 225, 350, 500, 500, 750, 750, 900, 900, 800, 800, 600, 600, 425, 425, 150, 150, 300, 300, 150}, 23);
            // Ball coords: (800, 825)
            ball = new Ball(this, 800, 825);
            // Hole coords: (136, 225)
            hole = new Hole(136, 225);
            // Sand coords:
            sands = new Sand[]{};
            // Ramp coords:
            ramps = new Ramp[]{};
        } else if (difficulty == 3) {  

        }
    }
}
