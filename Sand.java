import java.awt.*;
public class Sand extends Polygon implements Drawable {
    Sand(int[] xpoints, int[] ypoints, int npoints) {
        super(xpoints, ypoints, npoints);
    }
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillPolygon(this);
    }
}
