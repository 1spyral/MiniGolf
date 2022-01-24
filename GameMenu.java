import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;
import javax.swing.JOptionPane;

// Menu that contains the playable part of the game
public class GameMenu extends Menu {
    // The walls of the golf course
    GolfCourse golfCourse;
    // The ball that the player controls
    Ball ball;
    // The hole that the ball should reach
    Hole hole;
    // Ramps that change the direction of the ball
    Ramp[] ramps;
    // Sands that slow down the ball
    Sand[] sands;
    // Whether the pause button is held down (esc)
    boolean pauseHeld;
    GameMenu(StateMachine stateMachine) {
        super(stateMachine);
        pauseHeld = false;
    }
    public void draw(Graphics g) {
        g.setColor(new Color(0, 100, 0));
        // Draw the background
        g.fillRect(0, 0, Const.WIDTH, Const.HEIGHT);
        // Draw the walls of the golf course
        golfCourse.draw(g);
        // Draw all the ramps
        for (Ramp ramp: ramps) {
            ramp.draw(g);
        }
        // Draw all the sands
        for (Sand sand: sands) {
            sand.draw(g);
        }
        // Draw the hole
        hole.draw(g);
        // Draw the ball
        ball.draw(g);
    }
    public void update(Set<Integer> keysPressed, boolean mouseDown, int mouseX, int mouseY) {
        // Check whether the pause button is held (esc)
        if (keysPressed.contains(KeyEvent.VK_ESCAPE) && !pauseHeld) {
            pauseHeld = true;
        }
        // Checks if the pause button is activated (esc)
        else if (!keysPressed.contains(KeyEvent.VK_ESCAPE) && pauseHeld) {
            pauseHeld = false;
            StateChanger.pause.call(this.stateMachine);
        }
        // Send key information to the ball
        ball.update(keysPressed);
    }
    // For building the golf course
    public void build(int difficulty) throws IOException, FontFormatException {
        // Easy mode
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
        } 
        // Medium mode
        else if (difficulty == 2) {
            // Wall coords of the golf course: (150, 50), (850, 50), (850, 150), (750, 150), (750, 225), (850, 350), (850, 500), (750, 500), (750, 750), (850, 750), (850, 900), (450, 900), (450, 800), (350, 800), (350, 600), (50, 600), (50, 425), (350, 425), (350, 150), (225, 150), (225, 300), (50, 300), (50, 150)
            golfCourse = new GolfCourse(new int[]{150, 850, 850, 750, 750, 850, 850, 750, 750, 850, 850, 450, 450, 350, 350, 50, 50, 350, 350, 225, 225, 50, 50}, new int[]{50, 50, 150, 150, 225, 350, 500, 500, 750, 750, 900, 900, 800, 800, 600, 600, 425, 425, 150, 150, 300, 300, 150}, 23);
            // Ball coords: (800, 825)
            ball = new Ball(this, 800, 825);
            // Hole coords: (136, 225)
            hole = new Hole(136, 225);
            // Sand coords: (300, 550), (575, 550), (575, 825), (450, 825), (450, 800), (350, 800), (350, 600), (300, 600)
            sands = new Sand[]{new Sand(new int[]{300, 575, 575, 450, 450, 350, 350, 300}, new int[]{550, 550, 825, 825, 800, 800, 600, 600}, 8)};
            // Ramp coords: ((250, 50), (300, 50), (300, 150), (250, 150)), ((725, 50), (850, 50), (850, 150), (725, 150)), ((350, 150), (750, 159), (750, 225), (350, 225)), ((750, 225), (850, 350), (850, 500), (750, 500)), ((50, 425), (300, 425), (300, 600), (50, 600))
            ramps = new Ramp[]{new Ramp(new int[]{250, 300, 300, 250}, new int[]{50, 50, 150, 150}, 4, 4), new Ramp(new int[]{725, 850, 850, 725}, new int[]{50, 50, 150, 150}, 4, 4), new Ramp(new int[]{350, 750, 750, 350}, new int[]{150, 150, 225, 225}, 4, 1), new Ramp(new int[]{750, 850, 850, 750}, new int[]{225, 350, 500, 500}, 4, 4), new Ramp(new int[]{50, 300, 300, 50}, new int[]{425, 425, 600, 600}, 4, 2)};
        } 
        // Hard mode
        else if (difficulty == 3) {
            // Wall coords of the golf course: (50, 50), (150, 50), (150, 150), (350, 150), (350, 450), (550, 450), (550, 250), (450, 250), (450, 50), (550, 50), (550, 150), (650, 150), (650, 50), (750, 50), (750, 150), (650, 250), (650, 350), (750, 350), (750, 450), (650, 450), (550, 550), (550, 650), (450, 650), (450, 550), (350, 550), (250, 650), (250, 750), (150, 750), (150, 550), (250, 550), (250. 350), (150, 350), (150, 250), (50, 250)
            golfCourse = new GolfCourse(new int[]{50, 150, 150, 350, 350, 550, 550, 450, 450, 550, 550, 650, 650, 750, 750, 650, 650, 750, 750, 650, 550, 550, 450, 450, 350, 250, 250, 150, 150, 250, 250, 150, 150, 50}, new int[]{50, 50, 150, 150, 450, 450, 250, 250, 50, 50, 150, 150, 50, 50, 150, 250, 350, 350, 450, 450, 550, 650, 650, 550, 550, 650, 750, 750, 550, 550, 350, 350, 250, 250}, 34);
            // Ball coords(100, 100)
            ball = new Ball(this, 100, 100);
            // Hole coords: (700, 100)
            hole = new Hole(700, 100);
            // Sand coords: (200, 200), (300, 200), (300, 300), (200, 300)
            sands = new Sand[]{new Sand(new int[]{200, 300, 300, 200}, new int[]{200, 200, 300, 300}, 4)};
            // Ramp coords: ((250, 350), (350, 350), (350, 450), (250, 450)), ((550, 250), (650, 250), (650, 350), (550, 350))
            ramps = new Ramp[]{new Ramp(new int[]{250, 350, 350, 250}, new int[]{350, 350, 450, 450}, 4, 1), new Ramp(new int[]{550, 650, 650, 550}, new int[]{250, 250, 350, 350}, 4, 3)};
        }
    }
    // When the game is won
    public void win(int moves) throws FileNotFoundException {
        // Change to win menu
        this.stateMachine.toggleGame();
        this.stateMachine.toggleWin();
        this.stateMachine.winMenu.setScore(moves);
        // Ask the user for their name
        String name = JOptionPane.showInputDialog("Enter your name:");
        // Update high scores
        (new ScoreUpdater(moves, this.stateMachine.difficulty, name)).update();
    }
}
