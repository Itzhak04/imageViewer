package software.ulpgc.imageviewer;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SwingImageDisplay extends JPanel implements ImageDisplay {
    private Image image;
    private BufferedImage bitmap;

    @Override
    public void show(Image image) {
        this.image = image;
        this.bitmap = load(image.name());
        this.repaint();
    }

    @Override
    public Image image() {
        return image;
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        Resizer resizer = new Resizer(new Dimension(this.getWidth(), this.getHeight()));
        Dimension resized = resizer.resize(new Dimension(bitmap.getWidth(), bitmap.getHeight()));
        int x = (this.getWidth() - resized.width) / 2;
        int y = (this.getHeight() - resized.height) / 2;
        g.drawImage(bitmap, x, y, resized.width,resized.height, null);
    }

    public static class Resizer {
        private final Dimension dimension;

        public Resizer(Dimension dimension) {
            this.dimension = dimension;
        }

        public Dimension resize(Dimension dimension) {
            if (dimension.width <= this.dimension.width && dimension.height <= this.dimension.height) {
                return dimension;
            }else {
                double aspectRatio = (double) dimension.width / dimension.height;
                double panelAspectRatio = (double) this.dimension.width / this.dimension.height;

                if (aspectRatio > panelAspectRatio) {
                    return new Dimension(this.dimension.width, (int) (this.dimension.width / aspectRatio));
                } else {
                    return new Dimension((int) (this.dimension.height * aspectRatio), this.dimension.height);
                }
            }
        }
    }

    private BufferedImage load(String name) {
        try {
            return ImageIO.read(new File(name));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
