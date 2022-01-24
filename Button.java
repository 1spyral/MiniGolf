import java.awt.*;
import java.io.IOException;

// Custom button class interacting with the StateMachine
public class Button extends Rectangle implements Drawable {
    // The color the button will render as
    Color color;
    // The text printed on the button
    String text;
    // The method that will be called when the button is activated, a lambda is used because methods cannot be passed as arguments
    StateLambda method;
    // The StateMachine the button will be interacting with 
    StateMachine stateMachine;
    // The font of the text
    Font font;
    // Whether the button is being held down
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
        g.fillRoundRect(this.x, this.y, this.width, this.height, 25, 25);
        g.setColor(Color.BLACK);
        ((Graphics2D)g).setStroke(new BasicStroke(5));
        g.drawRoundRect(this.x, this.y, this.width, this.height, 25, 25);
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
    // Method for checking whether the mouse is colliding with the button
    public boolean collides(int mouseX, int mouseY) {
        return mouseX >= this.x && mouseX <= this.x + this.width && mouseY >= this.y && mouseY <= this.y + this.height;
    }
}
