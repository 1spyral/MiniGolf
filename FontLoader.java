import java.io.IOException;
import java.io.File;
import java.awt.*;
// Class for loading and storing fonts
public class FontLoader {
    private static final String FONT_PATH = "ComicMono.ttf";
    private File FONT_FILE;
    private Font FONT;
    // Used for small text
    public Font small;
    // Used for more visible text
    public Font medium;
    // Used for game titles
    public Font title;
    FontLoader() throws IOException, FontFormatException {
        FONT_FILE = new File(FONT_PATH);
        FONT = Font.createFont(Font.TRUETYPE_FONT, FONT_FILE);
        small = FONT.deriveFont(Font.PLAIN, 18);
        medium = FONT.deriveFont(Font.BOLD, 30);
        title = FONT.deriveFont(Font.BOLD | Font.ITALIC , 60);
    }
}
