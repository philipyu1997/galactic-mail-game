package objects;

import framework.*;
import window.Game;
import window.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

/**
 * @author Philip Yu
 */
public class Asteroid extends MovableObject {

    // CONSTANTS
    private final int ASTEROID_WIDTH;
    private final int ASTEROID_HEIGHT;
    private final int ASTEROID_SPEED;

    // VARIABLES
    private BufferedImage asteroidImage;
    private Texture tex;
    private Random rand = new Random();
    private Handler handler;

    public Asteroid(Entity entity, int x, int y, int velX, int velY, int angle, Texture tex, Handler handler) {

        super(entity, x, y, velX, velY, angle);
        this.tex = tex;
        this.handler = handler;

        asteroidImage = tex.sprite_asteroid[rand.nextInt(180)];
        ASTEROID_WIDTH = tex.sprite_asteroid[0].getWidth();
        ASTEROID_HEIGHT = tex.sprite_asteroid[0].getHeight();
        ASTEROID_SPEED = 2;

    }

    @Override
    public void tick(List<GameObject> object) {

        x -= ASTEROID_SPEED;

        checkBorder();
        checkCollision();

    }

    private void checkCollision() {

        for (int i = 0; i < handler.objectList.size(); ++i) {
            GameObject gameObject = handler.objectList.get(i);

            if (gameObject instanceof Player) {
                Player player = (Player) gameObject;

                if (player.isCollidable()) {
                    if (getBounds().intersects(player.getBounds())) {
                        player.isDied();
                        handler.removeObject(this);
                    }
                }
            }

        }

    }

    public void checkBorder() {

        if (x < 0 - ASTEROID_WIDTH) {
            x = Game.getWindowWidth();
            y = rand.nextInt(Game.getWindowHeight() - ASTEROID_HEIGHT);
        }

    }

    @Override
    public void render(Graphics g) {

        g.drawImage(asteroidImage, x, y, null);

    }

    @Override
    public Rectangle getBounds() {

        return (new Rectangle(x, y, ASTEROID_WIDTH, ASTEROID_HEIGHT));

    }

} // end class Moon
