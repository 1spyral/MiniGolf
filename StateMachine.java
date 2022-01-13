import java.util.Set;
import java.awt.*;

public class StateMachine implements Drawable {
    StartMenu startMenu;
    GameMenu gameMenu;
    PauseMenu pauseMenu;
    SelectMenu selectMenu;

    boolean start;
    boolean game;
    boolean pause;
    boolean select;

    public Lambda toggleStart;
    public Lambda toggleGame;
    public Lambda togglePause;
    public Lambda toggleSelect;
    StateMachine() {
        startMenu = new StartMenu(this);
        gameMenu = new GameMenu(this);
        pauseMenu = new PauseMenu(this);
        selectMenu = new SelectMenu(this);

        start = true;
        game = false;
        pause = false;
        select = false;

        toggleStart = () -> start = !start;
        toggleGame = () -> game = !game;
        togglePause = () -> pause = !pause;
        toggleSelect = () -> select = !select;
    }
    public void draw(Graphics g) {
        if (start) {
            startMenu.draw(g);
        }
        else if (game) {
            gameMenu.draw(g);
        }
        else if (pause) {
            gameMenu.draw(g);
            pauseMenu.draw(g);
        }
        else if (select) {
            selectMenu.draw(g);
        }
    }
    public void update(Set<Integer> keysPressed, boolean mouseDown, int mouseX, int mouseY) {
        if (start) {
            startMenu.update(keysPressed, mouseDown, mouseX, mouseY);
        }
        else if (game) {
            gameMenu.update(keysPressed, mouseDown, mouseX, mouseY);
        }
        else if (pause) {
            pauseMenu.update(keysPressed, mouseDown, mouseX, mouseY);
        }
        else if (select) {
            selectMenu.update(keysPressed, mouseDown, mouseX, mouseY);
        }
    }
}
