package objects;

import framework.Entity;
import framework.GameObject;
import framework.Texture;
import window.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

/**
 * @author Philip Yu
 */
public class Base extends GameObject {

    // CONSTANTS
    private final int BASES_WIDTH;
    private final int BASES_HEIGHT;
    private boolean baseArrival;

    // IMAGES
    private BufferedImage baseImage;

    // OBJECTS
    private Random rand = new Random();
    private Handler handler;
    private Texture tex;


    public Base(Entity entity, int x, int y, Texture tex, Handler handler) {

        super(entity, x, y);
        this.tex = tex;
        this.handler = handler;
        this.baseArrival = false;

        baseImage = tex.sprite_base[rand.nextInt(tex.sprite_base.length)];
        BASES_WIDTH = tex.sprite_base[0].getWidth();
        BASES_HEIGHT = tex.sprite_base[0].getHeight();

    }

    @Override
    public void tick(List<GameObject> object) {

        checkCollision();

    }

    private void checkCollision() {

        for (int i = 0; i < handler.objectList.size(); ++i) {
            GameObject gameObject = handler.objectList.get(i);

            if (gameObject instanceof Player) {
                Player player = (Player) gameObject;

                if (getBounds().intersects(player.getBounds())) {
                    baseArrival = true;
                    player.setCollidable(false);
                    player.setEntity(Entity.Landed);
                    player.setScore(player.getScore() - 2);
                } else {
                    if (baseArrival) {
                        handler.removeObject(this);
                        player.setCollidable(true);
                        player.setEntity(Entity.Flying);
                        player.setMoonCounter(player.getMoonCounter() + 1);
                    }

                    player.setScore(player.getScore() + 1);
                    baseArrival = false;
                }
            }
        }

    }

    @Override
    public void render(Graphics g) {

        g.drawImage(baseImage, x, y, null);

    }

    @Override
    public Rectangle getBounds() {

        return (new Rectangle(x, y, BASES_WIDTH, BASES_HEIGHT));

    }

} // end class Moon
