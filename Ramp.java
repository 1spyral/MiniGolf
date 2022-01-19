import java.awt.*;
public class Ramp extends Polygon implements Drawable {
    // 1 is up, 2 is right, 3 is down, 4 is left
    int direction;
    Ramp(int[] xpoints, int[] ypoints, int npoints, int direction) {
        super(xpoints, ypoints, npoints);
        this.direction = direction;
    }
    public void draw(Graphics g) {
        g.fillPolygon(this);
        // Display direction here

        //
    }
}
