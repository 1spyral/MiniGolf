import java.awt.*;

// The hole that the ball must fall into
public class Hole implements Drawable {
    // x coordinate of the hole
    int x;
    // y coordinate of the hole
    int y;
    // Radius of the hole
    int radius;
    Hole(int x, int y) {
        this.x = x;
        this.y = y;
        // The radius of the hole is 5 more than the radius of the ball
        this.radius = Const.BALL_RADIUS + 5;
    }
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        // fillOval asks for top left corner of the circle
        // Draw the hole
        g.fillOval(this.x - radius, this.y - radius, this.radius * 2, this.radius * 2);
    }
}
