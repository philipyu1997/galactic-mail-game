package window;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.imageio.ImageIO.read;

/**
 * @author Philip Yu
 */
public class BufferedImageLoader {

    // IMAGES
    private BufferedImage image;

    public BufferedImage loadImage(String path) {

        try {
            image = read(new File(path));
        } catch (IOException e) {
            System.out.println(e.getMessage() + " Unable to load image resources!");
        }

        return image;

    }

} // end class BufferedImageLoader
