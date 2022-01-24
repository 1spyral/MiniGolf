import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

// Ramp class that affects the movement of the ball 
public class Ramp extends Polygon implements Drawable {
    // 1 is up, 2 is right, 3 is down, 4 is left
    int direction;
    // Images to display ramp direction
    BufferedImage upArrow;
    BufferedImage rightArrow;
    BufferedImage downArrow;
    BufferedImage leftArrow;
    Ramp(int[] xpoints, int[] ypoints, int npoints, int direction) {
        super(xpoints, ypoints, npoints);
        this.direction = direction;
        try {
            upArrow = ImageIO.read(new File("up.png"));
            rightArrow = ImageIO.read(new File("right.png"));
            downArrow = ImageIO.read(new File("down.png"));
            leftArrow = ImageIO.read(new File("left.png"));
        } catch (Exception e) {}
    }
    public void draw(Graphics g) {
        g.setColor(new Color(0, 100, 0));
        // Draw the ramp area
        g.fillPolygon(this);
        // Draw up arrow
        if (direction == 1) {
            g.drawImage(upArrow, (int)(this.getBounds().getX() + this.getBounds().getWidth() / 2 - 12), (int)(this.getBounds().getY() + this.getBounds().getHeight() / 2 - 12), null);
        } 
        // Draw right arrow
        else if (direction == 2) {
            g.drawImage(rightArrow, (int)(this.getBounds().getX() + this.getBounds().getWidth() / 2 - 12), (int)(this.getBounds().getY() + this.getBounds().getHeight() / 2 - 12), null);
        } 
        // Draw down arrow
        else if (direction == 3) {
            g.drawImage(downArrow, (int)(this.getBounds().getX() + this.getBounds().getWidth() / 2 - 12), (int)(this.getBounds().getY() + this.getBounds().getHeight() / 2 - 12), null);
        } 
        // Draw left arrow
        else if (direction == 4) {
            g.drawImage(leftArrow, (int)(this.getBounds().getX() + this.getBounds().getWidth() / 2 - 12), (int)(this.getBounds().getY() + this.getBounds().getHeight() / 2 - 12), null);
        }
    }
}
