import java.awt.*;

// Sand class to slow down the ball movement
public class Sand extends Polygon implements Drawable {
    Sand(int[] xpoints, int[] ypoints, int npoints) {
        super(xpoints, ypoints, npoints);
    }
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        // Draw the sand area
        g.fillPolygon(this);
    }
}
