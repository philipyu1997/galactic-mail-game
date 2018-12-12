package framework;

import java.awt.*;
import java.util.List;

/**
 * @author Philip Yu
 */
public abstract class MovableObject extends GameObject {

    // VARIABLES
    protected int velX, velY, angle;

    public MovableObject(Entity entity, int x, int y, int velX, int velY, int angle) {

        super(entity, x, y);
        this.velX = velX;
        this.velY = velY;
        this.angle = angle;

    }

    @Override
    public abstract void tick(List<GameObject> object);

    @Override
    public abstract void render(Graphics g);

    @Override
    public abstract Rectangle getBounds();

    public int getAngle() {

        return angle;

    }

    public void setAngle(int angle) {

        this.angle = angle;

    }

    public int getRevolutions() {

        return angle / 360;

    }

} // end class MovableObject
