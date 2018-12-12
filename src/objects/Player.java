package objects;

import framework.*;
import window.Game;
import window.Handler;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * @author Philip Yu
 */
public class Player extends MovableObject {

    // CONSTANTS
    private final int MOVEMENT_SPEED = 4;
    private final int ROTATION_SPEED = 1;

    // VARIABLES
    private static int moonCounter;
    private int score;
    private int width;
    private int height;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;
    private boolean collidable;

    // OBJECTS
    private BufferedImage playerImage;
    private Explosion explosion;
    private Handler handler;
    private Texture tex;

    public Player(Entity entity, int x, int y, int velX, int velY, int angle, Handler handler) {

        super(entity, x, y, velX, velY, angle);
        this.handler = handler;
        this.tex = Game.getInstance();
        this.width = tex.sprite_flying.getWidth();
        this.height = tex.sprite_flying.getHeight();
        this.collidable = true;
        this.moonCounter = 0;

    }

    @Override
    public void tick(List<GameObject> object) {

        if (UpPressed) {
            moveForwards();
        }

        if (DownPressed) {
            moveBackwards();
        }

        if (LeftPressed) {
            rotateLeft();
        }

        if (RightPressed) {
            rotateRight();
        }

        if (moonCounter <= -5000) {
            Game.setState(GameState.LOSE);
        } else if (moonCounter >= 10000) {
            Game.setState(GameState.WIN);
        }

        checkBorder();

    }

    public void lose() {

        explosion = new Explosion(Entity.Explosion, x, y);
        explosion.playExplosionSound();
        handler.addObject(explosion);
        handler.removeObject(this);
        Game.setState(GameState.LOSE);

    }

    private void checkBorder() {

        // TOP BORDER
        if (y < 0)
            y = 0;

        // BOTTOM BORDER
        if (y >= Game.getWindowHeight() - tex.sprite_flying.getHeight() - tex.sprite_flying.getHeight() / 2)
            y = Game.getWindowHeight() - tex.sprite_flying.getHeight() - tex.sprite_flying.getHeight() / 2;

        // LEFT BORDER
        if (x <= 0)
            x = 0;

        // RIGHT BORDER
        if (x >= Game.getWindowWidth() - tex.sprite_flying.getWidth())
            x = Game.getWindowWidth() - tex.sprite_flying.getWidth();

    }

    @Override
    public void render(Graphics g) {

        if (entity == Entity.Flying) {
            playerImage = tex.sprite_flying;
        } else if (entity == Entity.Landed) {
            playerImage = tex.sprite_landed;
        }

        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), playerImage.getWidth() / 2.0, playerImage.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(playerImage, rotation, null);

    }

    @Override
    public Rectangle getBounds() {

        return (new Rectangle(x, y, tex.sprite_flying.getWidth(), tex.sprite_flying.getHeight()));

    }

    public void toggleUpPressed() {

        this.UpPressed = true;

    }

    public void toggleDownPressed() {

        this.DownPressed = true;

    }

    public void toggleRightPressed() {

        this.RightPressed = true;

    }

    public void toggleLeftPressed() {

        this.LeftPressed = true;

    }

    public void unToggleUpPressed() {

        this.UpPressed = false;

    }

    public void unToggleDownPressed() {

        this.DownPressed = false;

    }

    public void unToggleRightPressed() {

        this.RightPressed = false;

    }

    public void unToggleLeftPressed() {

        this.LeftPressed = false;

    }

    private void moveForwards() {

        velX = (int) Math.round(MOVEMENT_SPEED * Math.cos(Math.toRadians(angle)));
        velY = (int) Math.round(MOVEMENT_SPEED * Math.sin(Math.toRadians(angle)));
        x += velX;
        y += velY;
        checkBorder();

    }

    private void moveBackwards() {

        velX = (int) Math.round(MOVEMENT_SPEED * Math.cos(Math.toRadians(angle)));
        velY = (int) Math.round(MOVEMENT_SPEED * Math.sin(Math.toRadians(angle)));
        x -= velX;
        y -= velY;
        checkBorder();

    }

    private void rotateLeft() {

        this.angle -= this.ROTATION_SPEED;

    }

    private void rotateRight() {

        this.angle += this.ROTATION_SPEED;

    }

    public BufferedImage getPlayerImage() {

        return playerImage;

    }

    public boolean isCollidable() {

        return collidable;

    }

    public void setCollidable(boolean collidable) {

        this.collidable = collidable;

    }

    public int getScore() {

        return score;

    }

    public void setScore(int score) {

        this.score = score;

    }

    public static int getMoonCounter() {

        return moonCounter;

    }

    public static void setMoonCounter(int moon) {

        moonCounter = moon;

    }

} // end class Player