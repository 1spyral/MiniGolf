import java.util.Set;
import java.awt.*;
import java.io.IOException;

// Class that controls the various menus of the game
public class StateMachine implements Drawable {
    // All the menu objects
    StartMenu startMenu;
    GameMenu gameMenu;
    PauseMenu pauseMenu;
    SelectMenu selectMenu;
    LeaderboardMenu leaderboardMenu;
    WinMenu winMenu;
    // Booleans that dictate which menus are active
    boolean start;
    boolean game;
    boolean pause;
    boolean select;
    boolean leaderboard;
    boolean win;
    // Game difficulty: 1 is easy, 2 is medium, 3 is hard
    int difficulty;
    StateMachine() throws IOException, FontFormatException {
        startMenu = new StartMenu(this);
        gameMenu = new GameMenu(this);
        pauseMenu = new PauseMenu(this);
        selectMenu = new SelectMenu(this);
        leaderboardMenu = new LeaderboardMenu(this);
        winMenu = new WinMenu(this);

        start = true;
        game = false;
        pause = false;
        select = false;
        leaderboard = false;
        win = false;

        difficulty = 0;
    }
    public void draw(Graphics g) {
        // Draw the starting menu
        if (start) {
            startMenu.draw(g);
        }
        // Draw the game menu
        else if (game) {
            gameMenu.draw(g);
        }
        // Draw the pause menu
        else if (pause) {
            // The game menu will be drawn behind the pause window
            gameMenu.draw(g);
            pauseMenu.draw(g);
        }
        // Draw the difficulty select menu
        else if (select) {
            selectMenu.draw(g);
        }
        // Draw the high score menu
        else if (leaderboard) {
            leaderboardMenu.draw(g);
        }
        // Draw the win menu
        else if (win) {
            winMenu.draw(g);
        }
    }
    public void update(Set<Integer> keysPressed, boolean mouseDown, int mouseX, int mouseY) {
        // Update the starting menu
        if (start) {
            startMenu.update(keysPressed, mouseDown, mouseX, mouseY);
        }
        // Update the game menu
        else if (game) {
            gameMenu.update(keysPressed, mouseDown, mouseX, mouseY);
        }
        // Update the pause menu
        else if (pause) {
            pauseMenu.update(keysPressed, mouseDown, mouseX, mouseY);
        }
        // Update the difficulty select menu
        else if (select) {
            selectMenu.update(keysPressed, mouseDown, mouseX, mouseY);
        } 
        // Update the high score menu
        else if (leaderboard) {
            leaderboardMenu.update(keysPressed, mouseDown, mouseX, mouseY);
        } 
        // Update the win menu
        else if (win) {
            winMenu.update(keysPressed, mouseDown, mouseX, mouseY);
        }
    }
    // Helper methods to toggle menus on and off
    public void toggleStart() {
        start = !start;
    }
    public void toggleGame() {
        game = !game;
    }
    public void togglePause() {
        pause = !pause;
    }
    public void toggleSelect() {
        select = !select;
    }
    public void toggleLeaderboard() {
        leaderboard = !leaderboard;
    }
    public void toggleWin() {
        win = !win;
    }
    // Change the difficulty of the game
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    // Build the GolfCourse in the game menu
    public void buildGame() {
        try {
            gameMenu.build(difficulty);
        } catch (Exception e) {};
    }
}
