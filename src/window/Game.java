package window;

import framework.GameState;
import framework.Peripheral;
import framework.Texture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * @author Philip Yu
 */
public class Game extends JPanel {

    // CONSTANTS
    private final String GAME_TITLE = "Galactic Mail";
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    // GRAPHICS
    private JFrame frame;
    private BufferedImage world;
    private Graphics2D buffer;

    // OBJECTS
    private static GameState State = GameState.MENU;
    private static Game game;
    private static Texture tex;
    private Menu menu;
    private MapLoader mapLoader;

    public static void main(String[] args) {

        game = new Game();
        game.init();

        try {
            while (true) {
                game.repaint();
                Thread.sleep(1000 / 144);
            }
        } catch (InterruptedException ignored) {
            System.out.println(ignored.getMessage());
        }

    }

    private void init() {

        requestFocus();

        frame = new JFrame(GAME_TITLE);
        world = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);

        menu = new Menu();

        tex = new Texture();

        mapLoader = new MapLoader();

        Peripheral pe1 = new Peripheral(KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_SPACE);

        frame.addKeyListener(pe1);
        frame.addMouseListener(pe1);
        frame.addMouseMotionListener(pe1);

        // SETUP FRAME
        frame.setLayout(new BorderLayout());
        frame.add(this);

        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    @Override
    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        buffer = world.createGraphics();
        super.paintComponent(g2);

        if (State == GameState.GAME) {

            // RENDERS SCREEN
            g2.drawImage(world, 0, 0, null);

            mapLoader.loadBackground(buffer);

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

} // end class Game
