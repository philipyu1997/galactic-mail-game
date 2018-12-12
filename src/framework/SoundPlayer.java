package framework;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Philip Yu
 */
public class SoundPlayer {

    // VARIABLES
    private String soundFile;
    private int type;

    // OBJECTS
    private AudioInputStream soundStream;
    private Clip clip;

    // 1 for sounds that needs to be played all the time
    // 2 for sounds that only need to be played once

    public SoundPlayer(int type, String soundFile) {

        this.soundFile = soundFile;
        this.type = type;

        try {
            soundStream = AudioSystem.getAudioInputStream(new File(soundFile));
            clip = AudioSystem.getClip();
            clip.open(soundStream);
        } catch (Exception e) {
            System.out.println(e.getMessage() + " Unable to load sound resources!");
        }

        if (this.type == 1) {
            Runnable myRunnable = new Runnable() {
                public void run() {
                    while (true) {
                        clip.start();
                        clip.loop(clip.LOOP_CONTINUOUSLY);
                        try {
                            Thread.sleep(10000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(SoundPlayer.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            };

            Thread thread = new Thread(myRunnable);
            thread.start();
        }

    }

    public void play() {

        clip.start();

    }

    public void stop() {

        clip.stop();

    }

} // end class SoundPlayer