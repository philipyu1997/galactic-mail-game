package framework;

import window.BufferedImageLoader;

import java.awt.image.BufferedImage;

/**
 * @author Philip Yu
 */
public class Texture {

    // PATHS
    private String bmp_path = "resources/images/bmp/";
    private String o_png_path = "resources/images/png/original-png/";
    private String t_png_path = "resources/images/png/original-transparent/";
    private String null_path = "resources/images/png/frames/null.png";
    private String ext_png = ".png";
    private String ext_bmp = ".bmp";
    private String ext_gif = ".gif";

    // IMAGES
    public BufferedImage sprite_flying;
    public BufferedImage sprite_landed;
    public BufferedImage title;
    public BufferedImage background;

    public Texture() {

        getTextures();

    }

    private void getTextures() {

        BufferedImageLoader loader = new BufferedImageLoader();

        title = loader.loadImage(o_png_path + "Title" + ext_png);
        background = loader.loadImage(o_png_path + "Background" + ext_png);

        sprite_flying = loader.loadImage(t_png_path + "Flying" + ext_png);
        sprite_flying = loader.loadImage(t_png_path + "Landed" + ext_png);

    }

} // end class Textures
