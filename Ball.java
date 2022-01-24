import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.Set;

// Golf ball class
public class Ball implements Drawable {
GameMenu gameMenu;
    // x coordinate of the center of the ball
    double x;
    // y coordinate of the center of the balls
    double y;
    // radius of the ball
    int radius;
    // The angle the ball moves at, tracked in degrees
    double angle;
    // A value proportionate to the velocity of the ball
    double speed;
    // Whether the ball is moving or still
    boolean moving;
    // The number of times the player has hit the ball
    int moves;

    boolean spaceHeld;
    // Display font for angle and speed
    Font font;
    // Display font for number of moves
    Font movesFont;
    Ball(GameMenu gameMenu, int x, int y) throws IOException, FontFormatException {
        this.gameMenu = gameMenu;
        this.x = x;
        this.y = y;
        this.radius = Const.BALL_RADIUS;

        this.angle = 90;
        this.speed = 50;
        this.moves = 0;

        this.moving = false;
        this.spaceHeld = false;

        this.font = (new FontLoader()).small;
        this.movesFont = (new FontLoader()).medium;
    }
    // Draw the ball and its values
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        // Draw the ball
        // fillOval asks for top left corner of the circle
        g.fillOval((int)this.x - this.radius, (int)this.y - this.radius, this.radius * 2, this.radius * 2);
        g.setColor(Color.black);
        ((Graphics2D)g).setStroke(new BasicStroke(1));
        // Draw the outline of the ball
        g.drawOval((int)this.x - this.radius, (int)this.y - this.radius, this.radius * 2, this.radius * 2);
        g.setFont(this.font);
        g.setColor(Color.BLACK);
        // Display the current angle
        g.drawString("Angle: " + (int)this.angle, 1000, 150);
        // Display the current speed
        g.drawString("Velocity: " + (int)this.speed + "%", 1000, 350);
        g.setFont(this.movesFont);
        // Display the amount of moves
        g.drawString("Moves - " + this.moves, 1000, 800);
        // Only runs if the ball is still
        if (!this.moving) {
            double velocity = this.speed;
            double moveX = Math.cos(AngleConverter.radians(this.angle)) * velocity + this.x;
            double moveY = -Math.sin(AngleConverter.radians(this.angle)) * velocity + this.y;
            g.setColor(Color.white);
            ((Graphics2D)g).setStroke(new BasicStroke(2));
            // Draw the guide line that the ball will move at
            g.drawLine((int)this.x, (int)this.y, (int)moveX, (int)moveY);
        }
    }
    public void update(Set<Integer> keysPressed) {
        // Move the ball
        if (moving) {
            // Calculate the velocity the ball will move at
            double velocity = this.speed / 10;
            // Loop until the ball stops
            while (true) {
                // Whether the ball bounced off of a wall
                boolean hitWall = false;
                // Where the ball should move to this frame
                double moveX = Math.cos(AngleConverter.radians(this.angle)) * velocity + this.x;
                double moveY = -Math.sin(AngleConverter.radians(this.angle)) * velocity + this.y;
                // Loop through the ramps
                for (Ramp ramp: this.gameMenu.ramps) {
                    // Check if the ball collides with a ramp
                    if (this.collides(ramp.getBounds())) {
                        // Upwards ramp
                        if (ramp.direction == 1) {
                            moveY -= 1;
                        } 
                        // Right-way ramp
                        else if (ramp.direction == 2) {
                            moveX += 1;
                        } 
                        // Downwards ramp
                        else if (ramp.direction == 3) {
                            moveY += 1;
                        } 
                        // Left-way ramp
                        else if (ramp.direction == 4) {
                            moveX -= 1;
                        }
                    }
                }
                // Loop through the sands
                for (Sand sand: this.gameMenu.sands) {
                    // Check if the ball collides with a sand
                    if (this.collides(sand.getBounds())) {
                        // Slow the ball down
                        this.speed -= 5;
                    }
                }
                // The slope the ball is moving at
                double m = (moveY - this.y) / (moveX - this.x);
                // Whether the slope is undefined (moving vertically)
                boolean undefinedM = Double.isInfinite(m);
                // Loop through the walls of the golf course
                for (int[][] wall: this.gameMenu.golfCourse.getSegments()) {
                    // The slope of the wall segment
                    double wallM = ((double)wall[0][1] - wall[1][1]) / (wall[0][0] - wall[1][0]);
                    // Whether the wall slope is undefined (vertical)
                    boolean undefinedWall = Double.isInfinite(wallM);
                    // If the ball angle and the wall are parallel, skip this segment because collision is impossible
                    if ((undefinedM && undefinedWall) || (m == wallM)) {
                        continue;
                    }
                    // The x coordinate that the ball and the wall will meet
                    double meetX;
                    // The y coordinate that the ball and the wall will meet
                    double meetY;
                    // The y-intercept of the path of the ball
                    double b;
                    // The y-intercept of the line of the wall segment
                    double wallB;
                    // If the ball is moving vertically
                    if (undefinedM) {
                        meetX = this.x;
                        wallB = wall[0][1] - wallM * wall[0][0];
                        meetY = wallM * meetX + wallB;
                    } 
                    // If the wall is vertical
                    else if (undefinedWall) {
                        meetX = wall[0][0];
                        b = this.y - m * this.x;
                        meetY = m * meetX + b;
                    } 
                    // If neither slope is vertical
                    else {
                        b = this.y - m * this.x;
                        wallB = wall[0][1] - wallM * wall[0][0];
                        meetX = (wallB - b) / (m - wallM);
                        meetY = m * meetX + b;
                    }
                    // Large boolean to check if the ball and the wall will collide
                    // Note: after hours of troubleshooting and adjusting the code, collision detection still fails on seemingly random occasions
                    if (
                    // If the meet point is between the ball coords and the movement coords    
                    meetX >= Math.min(this.x, moveX) &&
                    meetX <= Math.max(this.x, moveX) &&
                    meetY >= Math.min(this.y, moveY) &&
                    meetY <= Math.max(this.y, moveY) &&
                    // If the meet point is on the wall
                    meetX >= Math.min(wall[0][0], wall[1][0]) && 
                    meetX <= Math.max(wall[0][0], wall[1][0]) && 
                    meetY >= Math.min(wall[0][1], wall[1][1]) && 
                    meetY <= Math.max(wall[0][1], wall[1][1]) && 
                    (this.x != meetX || this.y != meetY)) { 
                        // Get location of the points of the wall relative to the collision point
                        double x1 = wall[0][0] - meetX;
                        double y1 = wall[0][1] - meetY;
                        double x2 = wall[1][0] - meetX;
                        double y2 = wall[1][1] - meetY;
                        // The related acute angle of either point of the wall
                        double raa1 = AngleConverter.degrees((Math.atan2(y1, x1))) % 180;
                        double raa2 = AngleConverter.degrees((Math.atan2(y2, x2))) % 180;
                        // The angles of the walls relative to the collision point
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
                        // The current angle of the ball
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
                        } 
                        // If the wall is vertical
                        else if (x1 == x2) {
                            if (this.angle <= 90) {
                                raa1 = this.angle;
                                this.angle = 180 - raa1;
                            } else if (this.angle <= 180) {
                                raa1 = 180 - this.angle;
                                this.angle = raa1;
                            } else if (this.angle <= 270) {
                                raa1 = this.angle - 180;
                                this.angle = 360 - raa1;
                            } else {
                                raa1 = 360 - this.angle;
                                this.angle = 180 + raa1;
                            }
                        } else {
                            // Try to find the new ball angle
                            if (this.angle < Math.max(wall1, wall2) && this.angle > Math.min(wall1, wall2)) {
                                this.angle = 360 - Math.min(wall1, wall2) - Math.abs(this.angle - Math.max(wall1, wall2));
                            } else {
                                this.angle = 360 - Math.min(wall1, wall2) + Math.abs(this.angle - Math.max(wall1, wall2));
                            }
                            // If the angle didn't change, try to change it
                            if (oldAngle == this.angle) {
                                this.angle = 540 - this.angle;
                            }
                        }
                        // Move the ball to the collision point
                        this.x = meetX;
                        this.y = meetY;
                        hitWall = true;
                        // Check if the ball has entered the hole
                        if (this.collides(this.gameMenu.hole)) {
                            // Win the game
                            try {
                                this.gameMenu.win(this.moves);
                            } catch (Exception e) {}
                            hitWall = false;
                        }
                        break;
                    }
                }
                if (!hitWall) {
                    // Check if the ball has entered the hole
                    if (this.collides(this.gameMenu.hole)) {
                        // Win the game
                        try {
                            this.gameMenu.win(this.moves);
                        } catch (Exception e) {}
                }
                // Move the ball to the destination point
                this.x = moveX;
                this.y = moveY; 
                break;
                }
            }
            // The speed will gradually decrease
            this.speed--;
            // Check if the ball has stopped
            if (this.speed <= 0) {
                this.speed = 50;
                this.moving = false;
            }
        } else {
            // Increase speed
            if (keysPressed.contains(KeyEvent.VK_UP)) {
                speed++;
                speed = Math.min(speed, 100);
            }
            // Decrease speed
            if (keysPressed.contains(KeyEvent.VK_DOWN)) {
                speed--;
                speed = Math.max(speed, 1);
            }
            // Decrease angle
            if (keysPressed.contains(KeyEvent.VK_RIGHT)) {
                angle--;
                if (angle < 0) {
                    angle += 360;
                }
                angle %= 360;
            }
            // Increase angle
            if (keysPressed.contains(KeyEvent.VK_LEFT)) {
                angle++;
                angle %= 360;
            }
            // Checks if the spacebar is held down
            if (keysPressed.contains(KeyEvent.VK_SPACE) && !this.spaceHeld) {
                this.spaceHeld = true;
            } 
            // Launches the ball
            else if (!keysPressed.contains(KeyEvent.VK_SPACE) && this.spaceHeld) {
                this.spaceHeld = false;
                this.moving = true;
                this.moves++;
            }
        }
    }
    // Method to calculate if the ball is touching a rectangular object
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
    // Method to calculate if the ball is on top of the hole
    private boolean collides(Hole other) {
        // Use geometry circle formula
        if (Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2) <= Math.pow(other.radius, 2)) {
            return true;
        }
        return false;
    }
}
