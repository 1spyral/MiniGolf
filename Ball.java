import java.awt.*;
public class Ball implements Drawable {
    int x;
    int y;
    int radius;

    // Tracked in degrees
    double angle;
    double speed;
    Ball(int x, int y) {
        this.x = x;
        this.y = y;
        this.radius = Const.BALL_RADIUS;

        this.angle = 0;
        this.speed = 0;
    }
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(this.x, this.y, this.radius, this.radius);
    }
    public void update() {

    }
    private boolean collides(Rectangle other) {
        
    }
    public void setAngle(double angle) {
        this.angle = angle;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
