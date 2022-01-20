import java.awt.*;
public class Ramp extends Polygon implements Drawable {
    // 1 is up, 2 is right, 3 is down, 4 is left
    int direction;
    Ramp(int[] xpoints, int[] ypoints, int npoints, int direction) {
        super(xpoints, ypoints, npoints);
        this.direction = direction;
    }
    public void draw(Graphics g) {
        g.setColor(new Color(0, 100, 0));
        g.fillPolygon(this);
        if (direction == 1) {

        } else if (direction == 2) {

        } else if (direction == 3) {

        } else if (direction == 4) {

        }
    }
}
