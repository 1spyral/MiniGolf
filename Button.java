import java.awt.*;
import java.io.IOException;

public class Button extends Rectangle implements Drawable {
    Color color;
    String text;
    StateLambda method;
    StateMachine stateMachine;

    Font font;
    boolean held;
    Button(int x, int y, int width, int height, String text, Color color, StateLambda method, StateMachine stateMachine) throws IOException, FontFormatException {
        super(x, y, width, height);
        this.text = text;
        this.color = color;
        this.method = method;
        this.stateMachine = stateMachine;

        this.font = (new FontLoader()).medium;
    }
    public void draw(Graphics g) {
        // Draw the button
        g.setColor(this.color);
        g.fillRect(this.x, this.y, this.width, this.height);
        // Draw the button text
        g.setColor(Color.BLACK);
        g.setFont(this.font);
        g.drawString(this.text, this.x + this.width / 2 - g.getFontMetrics().charWidth('a') * this.text.length() / 2, this.y + this.height / 2);
    }
    public void update(boolean mouseDown, int mouseX, int mouseY) {
        // Checks if the user is holding down the button
        if (mouseDown && !this.held && this.collides(mouseX, mouseY)) {
            this.held = true;
        } 
        // When the user lets go of the button
        else if (!mouseDown && this.held && this.collides(mouseX, mouseY)) {
            this.held = false;
            this.method.call(this.stateMachine);
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
