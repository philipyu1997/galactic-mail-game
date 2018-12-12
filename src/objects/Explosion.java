package objects;

import framework.*;
import window.Game;

import java.awt.*;
import java.util.List;

/**
 * @author Philip Yu
 */
public class Explosion extends GameObject {

    // OBJECTS
    private Animation object_explosion;
    private SoundPlayer explosion_sound;
    private Texture tex;

    public Explosion(Entity entity, int x, int y) {

        super(entity, x, y);
        this.tex = Game.getInstance();

        explosion_sound = new SoundPlayer(2, tex.explosion_sound_path);
        object_explosion = new Animation(10, tex.sprite_explosion);

    }

    @Override
    public void tick(List<GameObject> object) {

        object_explosion.runAnimation();

    }

    @Override
    public void render(Graphics g) {

        object_explosion.drawAnimation(g, x, y, 0);

    }

    @Override
    public Rectangle getBounds() {

        return (new Rectangle(x, y, tex.sprite_explosion[0].getWidth(), tex.sprite_explosion[0].getHeight()));

    }

    public void playExplosionSound() {

        explosion_sound.play();

    }

} // end class Explosion
