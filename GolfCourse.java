import java.awt.*;
public class GolfCourse extends Polygon implements Drawable {
    GolfCourse(int[] xpoints, int[] ypoints, int npoints) {
        super(xpoints, ypoints, npoints);
    }
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawPolygon(this);
    }
}
