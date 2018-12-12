package objects;

import framework.Entity;
import framework.GameObject;
import framework.MovableObject;
import framework.Texture;
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
    private final int ROTATION_SPEED = 2;

    // VARIABLES
    private int width;
    private int height;
    private boolean UpPressed;
    private boolean DownPressed;
    private boolean RightPressed;
    private boolean LeftPressed;

    // OBJECTS
    private BufferedImage playerImage;
    private Handler handler;
    private Texture tex;

    public Player(Entity entity, int x, int y, int velX, int velY, int angle, Texture tex, Handler handler) {

        super(entity, x, y, velX, velY, angle);
        this.handler = handler;
        this.tex = Game.getInstance();
        this.width = tex.sprite_flying.getWidth();
        this.height = tex.sprite_flying.getHeight();

        if (entity == Entity.Flying) {
            playerImage = tex.sprite_flying;
        } else if (entity == Entity.Landed) {
            playerImage = tex.sprite_landed;
        }

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

        checkBorder();
        checkCollision();

    }

    private void checkCollision() {

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

        AffineTransform rotation = AffineTransform.getTranslateInstance(x, y);
        rotation.rotate(Math.toRadians(angle), playerImage.getWidth() / 2.0, playerImage.getHeight() / 2.0);
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(playerImage, rotation, null);

    }

    @Override
    public Rectangle getBounds() {

        return (new Rectangle(x, y, tex.sprite_flying.getWidth(), tex.sprite_flying.getHeight()));

    }

    public Rectangle getBoundsTop() {

        return (new Rectangle(x + (width / 2) - ((width / 2) / 2), y, width / 2, height / 2));

    }

    public Rectangle getBoundsBottom() {

        return (new Rectangle(x + (width / 2) - ((width / 2) / 2), y + (height / 2), width / 2, height / 2));

    }

    public Rectangle getBoundsLeft() {

        return (new Rectangle(x, y + 5, 5, height - 10));

    }

    public Rectangle getBoundsRight() {

        return (new Rectangle(x + width - 5, y + 5, 5, height - 10));

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

} // end class Player