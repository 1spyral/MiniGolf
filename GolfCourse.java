import java.awt.*;
public class GolfCourse extends Polygon implements Drawable {
    GolfCourse(int[] xpoints, int[] ypoints, int npoints) {
        super(xpoints, ypoints, npoints);
    }
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        ((Graphics2D)g).setStroke(new BasicStroke(10));
        g.drawPolygon(this);
    }
    public int[][][] getSegments() {
        int[][][] segments = new int[npoints][2][2];
        for (int i = 0; i < npoints; i++) {
            segments[i][0][0] = xpoints[i];
            segments[i][0][1] = ypoints[i];
            if (i == 0) {
                segments[i][1][0] = xpoints[npoints - 1];
                segments[i][1][1] = ypoints[npoints - 1];
            } else {
                segments[i][1][0] = xpoints[i - 1];
                segments[i][1][1] = ypoints[i - 1];
            }
        }
        return segments;
    }
}
