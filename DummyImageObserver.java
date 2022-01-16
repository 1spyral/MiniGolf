import java.awt.image.ImageObserver;
import java.awt.Image;
// A dummy ImageObserver for drawing images.
// This program does not actually need an ImageObserver because every image rendered will be a BufferedImage, however Graphics.drawImage requires an ImageObserver
public class DummyImageObserver implements ImageObserver {
    DummyImageObserver() {}
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return true;
    }
}
