import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Set;
public class Ball implements Drawable {
GameMenu gameMenu;

    double x;
    double y;
    int radius;

    // Tracked in degrees
    double angle;
    double speed;

    boolean moving;
    boolean spaceHeld;

    Font font;
    Ball(GameMenu gameMenu, int x, int y) throws IOException, FontFormatException {
        this.gameMenu = gameMenu;
        this.x = x;
        this.y = y;
        this.radius = Const.BALL_RADIUS;

        this.angle = 0;
        this.speed = 1;

        this.moving = false;
        this.spaceHeld = false;

        this.font = (new FontLoader()).small;
    }
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        // fillOval asks for top left corner of the circle
        g.fillOval((int)this.x - this.radius, (int)this.y - this.radius, this.radius * 2, this.radius * 2);
        g.setFont(this.font);
        g.setColor(Color.BLACK);
        g.drawString("Angle: " + this.angle, 1000, 150);
        g.drawString("Velocity: " + this.speed + "%", 1000, 550);
    }
    public void update(Set<Integer> keysPressed, boolean mouseDown, int mouseX, int mouseY) {
        if (moving) {
            double velocity = this.speed / 10;
            boolean hitWall = false;
            while (true) {            
                double moveX = Math.cos(AngleConverter.radians(this.angle)) * velocity + this.x;
                double moveY = Math.sin(AngleConverter.radians(this.angle)) * velocity + this.x;
                // Slope
                double m = (moveY - this.y) / (moveX - this.x);
                // Y-intercept
                double b = this.y - m * this.x;
                for (int i = 0; i < this.gameMenu.golfCourse.npoints; i++) {
                    // Slope of the wall segment
                    double wallM;
                    // Y-intercept of the wall segment
                    double wallB;
                    if (i == 0) {
                        System.out.println((this.gameMenu.golfCourse.xpoints[i] - this.gameMenu.golfCourse.xpoints[this.gameMenu.golfCourse.npoints - 1]));
                        wallM = (this.gameMenu.golfCourse.ypoints[i] - this.gameMenu.golfCourse.ypoints[this.gameMenu.golfCourse.npoints - 1]) / (this.gameMenu.golfCourse.xpoints[i] - this.gameMenu.golfCourse.xpoints[this.gameMenu.golfCourse.npoints - 1]);
                        wallB = this.gameMenu.golfCourse.ypoints[i] - wallM * this.gameMenu.golfCourse.xpoints[i];
                    } else {
                        wallM = (this.gameMenu.golfCourse.ypoints[i] - this.gameMenu.golfCourse.ypoints[i - 1]) / (this.gameMenu.golfCourse.xpoints[i] - this.gameMenu.golfCourse.xpoints[i - 1]);
                        wallB = this.gameMenu.golfCourse.ypoints[i] - wallM * this.gameMenu.golfCourse.xpoints[i];
                    }
                    // Where the lines meet
                    double meetX = (wallB - b) / (m - wallM);
                    double meetY = m * meetX + b;
                    if (meetX >= Math.min(this.x, moveX) && meetX <= Math.max(this.x, moveX) && meetY >= Math.min(this.y, moveY) && meetY <= Math.max(this.y, moveY)) {
                        velocity *= (1 - Math.abs(meetX - x) / Math.abs(moveX - x));
                        this.x = meetX;
                        this.y = meetY;
                        hitWall = true;
                        break;
                    }
                }
                if (!hitWall) {
                    this.x = moveX;
                    this.y = moveY;
                    break;
                }
            }  
            moving = false;
        } else {
            if (keysPressed.contains(KeyEvent.VK_UP)) {
                speed++;
                speed = Math.min(speed, 100);
            }
            if (keysPressed.contains(KeyEvent.VK_DOWN)) {
                speed--;
                speed = Math.max(speed, 1);
            }
            if (keysPressed.contains(KeyEvent.VK_LEFT)) {
                angle--;
                if (angle < 0) {
                    angle += 360;
                }
                angle %= 360;
            }
            if (keysPressed.contains(KeyEvent.VK_RIGHT)) {
                angle++;
                angle %= 360;
            }
            if (keysPressed.contains(KeyEvent.VK_SPACE) && !this.spaceHeld) {
                this.spaceHeld = true;
            } else if (!keysPressed.contains(KeyEvent.VK_SPACE) && this.spaceHeld) {
                this.spaceHeld = false;
                this.moving = true;
            }
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
