import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Method;

public class Button extends Rectangle implements Drawable {
    Color color;
    String text;

    Font font;
    boolean held;

    Method method;
    Button(int x, int y, int width, int height, String text, Color color) throws IOException, FontFormatException {
        super(x, y, width, height)
        this.text = text;
        this.color = color;

        this.font = (new FontLoader()).medium;
    }
    public void setMethod(Method method) {
        this.method = method;
    }
    public void draw(Graphics g) {
        // Draw the button
        g.setColor(color);
        g.fillRect(x, y, width, height);
        // Draw the button text
        g.setColor(Color.BLACK);
        g.setFont(font);
        g.drawString(text, x, y);
    }
    public void update(boolean mouseDown, int mouseX, int mouseY) {
        if (mouseDown && !this.held && this.collides(mouseX, mouseY)) {

        }
    }
    public boolean collides(int mouseX, int mouseY) {
        return mouseX 
    }
}
