package window;

import framework.*;
import objects.Asteroid;
import objects.Base;
import objects.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import java.util.List;

/**
 * @author Philip Yu
 */
public class Game extends JPanel {

    // CONSTANTS
    private static final String GAME_TITLE = "Galactic Mail";
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    // GRAPHICS
    private BufferedImage world = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);
    private Graphics2D buffer;
    private JFrame frame;

    // VARIABLES
    private static int level;

    // OBJECTS
    public static List<Base> baseList = new ArrayList<>();
    private static Game game;
    private static GameState State;
    private static Handler handler;
    private static Texture tex;
    private Asteroid asteroid;
    private Base base;
    private MapLoader mapLoader;
    private Menu menu;
    private Player player;
    private Random rand = new Random();
    private Statistics statistics;

    public static void main(String[] args) {

        game = new Game();
        game.init();

        try {
            while (true) {
                game.tick();
                game.repaint();
                Thread.sleep(1000 / 144);
            }
        } catch (InterruptedException ignored) {
            System.out.println(ignored.getMessage());
        }

    }

    private void tick() {

        if (State == GameState.GAME) {
            handler.tick();

            if (baseList.size() == player.getMoonCounter())
                restart();
        }

    }

    private void init() {

        State = GameState.MENU;
        level++;

        requestFocus();

        frame = new JFrame(GAME_TITLE);
        world = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);

        tex = new Texture();
        menu = new Menu();
        handler = new Handler();
        mapLoader = new MapLoader();

        tex.background_sound = new SoundPlayer(1, "resources/sound/Music.wav");

        // INIT ASTEROIDS
        for (int i = 0; i < level; ++i) {
            asteroid = new Asteroid(Entity.Asteroid,
                    rand.nextInt(WINDOW_WIDTH - tex.sprite_asteroid[0].getWidth()),
                    rand.nextInt(WINDOW_HEIGHT - tex.sprite_asteroid[0].getHeight()),
                    0, 0, rand.nextInt(360), handler);
            handler.addObject(asteroid);
        }

        // INIT BASES
        for (int i = 0; i < level; ++i) {
            base = new Base(Entity.Base,
                    rand.nextInt(WINDOW_WIDTH - tex.sprite_base[0].getWidth()),
                    rand.nextInt(WINDOW_HEIGHT - tex.sprite_base[0].getHeight()),
                    tex, handler);
            baseList.add(base);
            handler.addObject(base);
        }

        // INIT PLAYER
        player = new Player(Entity.Flying, 100, 100, 0, 0, rand.nextInt(360), handler);
        handler.addObject(player);

        Peripheral pe1 = new Peripheral(player, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D);

        statistics = new Statistics(player);

        frame.addKeyListener(pe1);
        frame.addMouseListener(pe1);
        frame.addMouseMotionListener(pe1);

        // SETUP FRAME
        setLayout(new BorderLayout());
        frame.add(this);

        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    @Override
    public void paintComponent(Graphics g) {

        if (State == GameState.GAME) {

            Graphics2D g2 = (Graphics2D) g;
            buffer = world.createGraphics();
            super.paintComponent(g2);
            ////////////////////////////////
            // DRAW HERE

            // RENDERS BACKGROUND
            mapLoader.loadBackground(buffer);

            // RENDERS OBJECTS
            handler.render(buffer);

            // RENDERS SCREEN
            g2.drawImage(world, 0, 0, null);

            // USED FOR DEBUGGING - REMOVE ME
            statistics.renderForeground(g);

        } else {

            menu.render(g);

        }

    }

    public static int getWindowWidth() {

        return WINDOW_WIDTH;

    }

    public static int getWindowHeight() {

        return WINDOW_HEIGHT;

    }

    public static GameState getState() {

        return State;

    }

    public static void setState(GameState state) {

        State = state;

    }

    public static Texture getInstance() {

        return tex;

    }

    public static List<Base> getBaseList() {

        return baseList;

    }

    public static void setBaseList(List<Base> baseList) {

        Game.baseList = baseList;

    }

    public void restart() {

        setBaseList(new ArrayList<>());
        player.setMoonCounter(0);
        init();

    }

    public static int getLevel() {

        return level;

    }

    public static void setLevel(int l) {

        level = l;

    }

} // end class Game
