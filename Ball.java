import java.awt.*;
public class Ball implements Drawable {
GameMenu gameMenu;

    int x;
    int y;
    int radius;

    // Tracked in degrees
    double angle;
    double speed;
    Ball(GameMenu gameMenu, int x, int y) {
        this.gameMenu = gameMenu;
        this.x = x;
        this.y = y;
        this.radius = Const.BALL_RADIUS;

        this.angle = 0;
        this.speed = 0;
    }
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        // fillOval asks for top left corner of the circle
        g.fillOval(this.x - this.radius, this.y - this.radius, this.radius * 2, this.radius * 2);
    }
    public void update() {
        if (this.speed > 0) {
            
        }
    }
    private boolean collides(Rectangle other) {
        // Calculate the distance between the centers of each object
        double distX = Math.abs(this.x - other.x - other.width / 2);
        double distY = Math.abs(this.y - other.y - other.height / 2);
        double distSq = Math.pow(distX - other.width / 2, 2) + Math.pow(distY - other.height / 2, 2);
        // If the distance between the centers is greater than the radii of both shapes
        if (distX > other.width / 2 + this.radius || distY > other.height / 2 + this.radius) {
            return false;
        }
        // If the distance centers is smaller or equal to the width/height of the rectangle
        else if (distX <= other.width / 2 || distY <= other.height / 2) {
            return true;
        }
        // Check if the distance between the corner of the rectangle and the center of the circle is smaller or equal to the radius of the circle
        return distSq <= Math.pow(this.radius, 2);
    }
    public void setAngle(double angle) {
        this.angle = angle;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }
}
