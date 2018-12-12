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
    private int asteroidSpeed;

    // VARIABLES
    private BufferedImage asteroidImage;
    private Random rand;

    // OBJECTS
    private Handler handler;
    private Texture tex;

    public Asteroid(Entity entity, int x, int y, int velX, int velY, int angle, Handler handler) {

        super(entity, x, y, velX, velY, angle);
        this.rand = new Random();
        this.handler = handler;
        this.tex = Game.getInstance();

        asteroidImage = tex.sprite_asteroid[rand.nextInt(180)];
        ASTEROID_WIDTH = tex.sprite_asteroid[0].getWidth();
        ASTEROID_HEIGHT = tex.sprite_asteroid[0].getHeight();
        asteroidSpeed = 4;

    }

    @Override
    public void tick(List<GameObject> object) {

        x -= asteroidSpeed;

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
                        player.lose();
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
