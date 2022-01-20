import java.awt.*;
public class Hole implements Drawable {
    int x;
    int y;
    int radius;
    Hole(int x, int y) {
        this.x = x;
        this.y = y;
        this.radius = Const.BALL_RADIUS + 5;
    }
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        // fillOval asks for top left corner of the circle
        g.fillOval(this.x - radius, this.y - radius, this.radius * 2, this.radius * 2);
    }
}
