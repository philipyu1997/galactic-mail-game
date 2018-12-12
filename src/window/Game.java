package window;

import framework.GameState;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author Philip Yu
 */
public class Game extends JPanel {

    // CONSTANTS
    private final String GAME_TITLE = "Galactic Mail";
    private static final int GAME_WIDTH = 1280;
    private static final int GAME_HEIGHT = 1280;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;

    // GRAPHICS
    private JFrame frame;
    private BufferedImage world;
    private Graphics2D buffer;

    // OBJECTS
    private static GameState State = GameState.MENU;
    private static Game game;
    private Menu menu;

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
        world = new BufferedImage(GAME_WIDTH, GAME_HEIGHT, BufferedImage.TYPE_INT_RGB);

        menu = new Menu();

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

        } else {

            menu.render(g);

        }

    }

    public static int getGameWidth() {

        return GAME_WIDTH;

    }

    public static int getGameHeight() {

        return GAME_HEIGHT;

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

} // end class Game
