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

        this.angle = 90;
        this.speed = 50;

        this.moving = false;
        this.spaceHeld = false;

        this.font = (new FontLoader()).small;
    }
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        // fillOval asks for top left corner of the circle
        g.fillOval((int)this.x - this.radius, (int)this.y - this.radius, this.radius * 2, this.radius * 2);
        g.setColor(Color.black);
        ((Graphics2D)g).setStroke(new BasicStroke(1));
        g.drawOval((int)this.x - this.radius, (int)this.y - this.radius, this.radius * 2, this.radius * 2);
        g.setFont(this.font);
        g.setColor(Color.BLACK);
        g.drawString("Angle: " + this.angle, 1000, 150);
        g.drawString("Velocity: " + this.speed + "%", 1000, 550);
        if (!this.moving) {
            double velocity = this.speed;
            double moveX = Math.cos(AngleConverter.radians(this.angle)) * velocity + this.x;
            double moveY = -Math.sin(AngleConverter.radians(this.angle)) * velocity + this.y;
            g.setColor(Color.white);
            ((Graphics2D)g).setStroke(new BasicStroke(2));
            g.drawLine((int)this.x, (int)this.y, (int)moveX, (int)moveY);
        }
    }
    public void update(Set<Integer> keysPressed, boolean mouseDown, int mouseX, int mouseY) {
        if (moving) {
            double velocity = this.speed / 5;
            while (true) {   
                boolean hitWall = false;
                double moveX = Math.cos(AngleConverter.radians(this.angle)) * velocity + this.x;
                double moveY = -Math.sin(AngleConverter.radians(this.angle)) * velocity + this.y;
                // Slope
                double m = (moveY - this.y) / (moveX - this.x);
                // Undefined slope
                boolean undefinedM = Double.isInfinite(m);
                for (int[][] wall: this.gameMenu.golfCourse.getSegments()) {
                    double wallM = ((double)wall[0][1] - wall[1][1]) / (wall[0][0] - wall[1][0]);
                    // Undefined wall slope
                    boolean undefinedWall = Double.isInfinite(wallM);
                    if ((undefinedM && undefinedWall) || (m == wallM)) {
                        continue;
                    }
                    double meetX;
                    double meetY;
                    double b;
                    double wallB;
                    if (undefinedM) {
                        meetX = this.x;
                        wallB = wall[0][1] - wallM * wall[0][0];
                        meetY = wallM * meetX + wallB;
                    } else if (undefinedWall) {
                        meetX = wall[0][0];
                        b = this.y - m * this.x;
                        meetY = m * meetX + b;
                    } else {
                        b = this.y - m * this.x;
                        wallB = wall[0][1] - wallM * wall[0][0];
                        meetX = (wallB - b) / (m - wallM);
                        meetY = m * meetX + b;
                    }
                    if (meetX >= Math.min(this.x, moveX) &&
                    meetX <= Math.max(this.x, moveX) &&
                    meetY >= Math.min(this.y, moveY) &&
                    meetY <= Math.max(this.y, moveY) &&
                    meetX >= Math.min(wall[0][0], wall[1][0]) && 
                    meetX <= Math.max(wall[0][0], wall[1][0]) && 
                    meetY >= Math.min(wall[0][1], wall[1][1]) && 
                    meetY <= Math.max(wall[0][1], wall[1][1]) &&
                    (this.x != meetX || this.y != meetY)) {
                        double x1 = wall[0][0] - meetX;
                        double y1 = wall[0][1] - meetY;
                        double x2 = wall[1][0] - meetX;
                        double y2 = wall[1][1] - meetY;
                        double raa1 = AngleConverter.degrees((Math.atan2(y1, x1))) % 180;
                        double raa2 = AngleConverter.degrees((Math.atan2(y2, x2))) % 180;
                        double wall1;
                        double wall2;
                        if (raa1 <= 0) {
                            wall1 = 180 - raa1;
                        } else {
                            wall1 = raa1;
                        }
                        if (raa2 <= 0) {
                            wall2 = 180 - raa2;
                        } else {
                            wall2 = raa2;
                        }
                        double oldAngle = this.angle;
                        // If the wall is horizontal
                        if (y1 == y2) {
                            if (this.angle <= 90) {
                                raa1 = this.angle;
                                this.angle = 360 - raa1;
                            } else if (this.angle <= 180) {
                                raa1 = 180 - this.angle;
                                this.angle = 180 + raa1;
                            } else if (this.angle <= 270) {
                                raa1 = this.angle - 180;
                                this.angle = 180 - raa1;
                            } else {
                                raa1 = 360 - this.angle;
                                this.angle = raa1;
                            }
                        } else {
                            if (this.angle < Math.max(wall1, wall2) && this.angle > Math.min(wall1, wall2)) {
                                this.angle = 360 - Math.min(wall1, wall2) - Math.abs(this.angle - Math.max(wall1, wall2));
                            } else {
                                this.angle = 360 - Math.min(wall1, wall2) + Math.abs(this.angle - Math.max(wall1, wall2));
                            }
                            if (oldAngle == this.angle) {
                                raa1 = this.angle - 180;
                                this.angle = 540 - this.angle;
                            }
                        }
                        this.x = meetX;
                        this.y = meetY;
                        hitWall = true;
                    }
                }
                if (!hitWall) {
                    this.x = moveX;
                    this.y = moveY; 
                    break;
                }
            }
            this.speed--;
            if (this.speed <= 0) {
                this.speed = 0;
                this.moving = false;
            }
        } else {
            if (keysPressed.contains(KeyEvent.VK_UP)) {
                speed++;
                speed = Math.min(speed, 100);
            }
            if (keysPressed.contains(KeyEvent.VK_DOWN)) {
                speed--;
                speed = Math.max(speed, 1);
            }
            if (keysPressed.contains(KeyEvent.VK_RIGHT)) {
                angle--;
                if (angle < 0) {
                    angle += 360;
                }
                angle %= 360;
            }
            if (keysPressed.contains(KeyEvent.VK_LEFT)) {
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
}
