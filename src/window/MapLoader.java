package window;

import framework.Texture;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Philip Yu
 */
public class MapLoader {

    // CONSTANTS
    private final int WINDOW_WIDTH = Game.getWindowWidth();
    private final int WINDOW_HEIGHT = Game.getWindowHeight();

    // GRAPHICS
    private BufferedImage background;

    // OBJECTS
    private Texture tex = Game.getInstance();

    public MapLoader() {

        background = tex.background;

    }

    public void loadBackground(Graphics g) {

        g.drawImage(background, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, null);

    }

} // end class MapLoader
