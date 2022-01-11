import java.awt.FontFormatException;
import java.io.IOException;

public class MiniGolf {
    public static void main(String[] args) throws IOException, FontFormatException {
        Game game = new Game();
        game.setUp();
        game.runGameLoop();
    }
}
