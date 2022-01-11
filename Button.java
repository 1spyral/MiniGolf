import java.awt.*;
import java.io.IOException;

public class Button extends Rectangle implements Drawable {
    Color color;
    String text;
    Lambda method;

    Font font;
    boolean held;
    Button(int x, int y, int width, int height, String text, Color color, Lambda method) throws IOException, FontFormatException {
        super(x, y, width, height);
        this.text = text;
        this.color = color;
        this.method = method;

        this.font = (new FontLoader()).medium;
    }
    public void draw(Graphics g) {
        // Draw the button
        g.setColor(this.color);
        g.fillRect(this.x, this.y, this.width, this.height);
        // Draw the button text
        g.setColor(Color.BLACK);
        g.setFont(this.font);
        g.drawString(this.text, this.x + 50, this.y + 50);
    }
    public void update(boolean mouseDown, int mouseX, int mouseY) {
        // Checks if the user is holding down the button
        if (mouseDown && !this.held && this.collides(mouseX, mouseY)) {
            this.held = true;
        } 
        // When the user lets go of the button
        else if (!mouseDown && this.held && this.collides(mouseX, mouseY)) {
            this.held = false;
            this.method.call();
        }
        // If the user moves their mouse away while holding down the button
        else if (mouseDown && this.held && !this.collides(mouseX, mouseY)) {
            this.held = false;
        }
    }
    public boolean collides(int mouseX, int mouseY) {
        return mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height;
    }
}
