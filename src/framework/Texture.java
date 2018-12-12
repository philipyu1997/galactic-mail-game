package framework;

import window.BufferedImageLoader;

import java.awt.image.BufferedImage;

/**
 * @author Philip Yu
 */
public class Texture {

    // PATHS
    public String explosion_sound_path;
    private String bmp_path = "resources/images/bmp/";
    private String o_png_path = "resources/images/png/original-png/";
    private String t_png_path = "resources/images/png/original-transparent/";
    private String asteroid_png_path = "resources/images/png/frames/transparent/Asteroid/";
    private String base_png_path = "resources/images/png/frames/transparent/Base/";
    private String explosion_png_path = "resources/images/png/frames/transparent/Explosion/";
    private String null_path = "resources/images/png/frames/null.png";
    private String ext_png = ".png";
    private String ext_bmp = ".bmp";
    private String ext_gif = ".gif";

    // IMAGES
    public BufferedImage title;
    public BufferedImage background;
    public BufferedImage sprite_flying;
    public BufferedImage sprite_landed;
    public BufferedImage[] sprite_base = new BufferedImage[8];
    public BufferedImage[] sprite_asteroid = new BufferedImage[180];
    public BufferedImage[] sprite_explosion = new BufferedImage[9];

    // SOUND
    public SoundPlayer background_sound;

    public Texture() {

        getTextures();

    }

    private void getTextures() {

        BufferedImageLoader loader = new BufferedImageLoader();

        explosion_sound_path = "resources/sound/Explosion.wav";

        title = loader.loadImage(t_png_path + "Title" + ext_png);
        background = loader.loadImage(o_png_path + "Background" + ext_png);

        sprite_flying = loader.loadImage(t_png_path + "Flying" + ext_png);
        sprite_landed = loader.loadImage(t_png_path + "Landed" + ext_png);

        for (int i = 0; i < sprite_base.length; ++i)
            sprite_base[i] = loader.loadImage(base_png_path + "Base-" + i + ext_png);

        for (int i = 0; i < sprite_explosion.length; ++i)
            sprite_explosion[i] = loader.loadImage(explosion_png_path + "Explosion-" + i + ext_png);

        for (int i = 0; i < sprite_asteroid.length; ++i)
            sprite_asteroid[i] = loader.loadImage(asteroid_png_path + "Asteroid-" + i + ext_png);

    }

} // end class Textures
