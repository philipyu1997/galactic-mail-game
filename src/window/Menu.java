package window;

import framework.GameState;
import framework.Texture;

import java.awt.*;

/**
 * @author Philip Yu
 */
public class Menu {

    // CONSTANTS
    private final int WINDOW_WIDTH = Game.getWindowWidth();

    // GRAPHICS
    private Graphics2D g2d;
    private Texture tex = new Texture();

    // OBJECTS
    public Rectangle playButton = new Rectangle(WINDOW_WIDTH / 2 - 60, 150, 120, 50);
    public Rectangle helpButton = new Rectangle(WINDOW_WIDTH / 2 - 60, 250, 120, 50);
    public Rectangle quitButton = new Rectangle(WINDOW_WIDTH / 2 - 60, 350, 120, 50);

    public void render(Graphics g) {

        g2d = (Graphics2D) g;

        if (Game.getState() == GameState.MENU) {

            // DRAWS TITLE
            g.drawImage(tex.title, 0, 0, 800, 600, null);

            // SETS FONT
            Font fnt1 = new Font("arial", Font.BOLD, 30);
            g.setFont(fnt1);
            g.setColor(Color.BLACK);

            g.fillRect(playButton.x, playButton.y, 120, 50);
            g.setColor(Color.WHITE);

            printStringCenter("1. Play", 50, playButton.x + 60, playButton.y + 35);
            g2d.draw(playButton);

            g.setColor(Color.BLACK);
            g.fillRect(helpButton.x, helpButton.y, 120, 50);
            g.setColor(Color.WHITE);

            printStringCenter("2. Help", 50, helpButton.x + 60, helpButton.y + 35);
            g2d.draw(helpButton);

            g.setColor(Color.BLACK);
            g.fillRect(quitButton.x, quitButton.y, 120, 50);
            g.setColor(Color.WHITE);

            printStringCenter("3. Quit", 50, quitButton.x + 60, quitButton.y + 35);
            g2d.draw(quitButton);

        } else if (Game.getState() == GameState.HELP) {

            // DRAWS BACKGROUND
            g.fillRect(0, 0, 800, 600);

            // SETS FONT
            Font fnt0 = new Font("arial", Font.BOLD, 50);
            g.setFont(fnt0);
            g.setColor(Color.WHITE);

            printStringCenter("HELP", fnt0.getSize(), WINDOW_WIDTH / 2, 200);

            // SETS FONT
            Font fnt1 = new Font("arial", Font.BOLD, 30);
            g.setFont(fnt1);

            printStringCenter("Player Controls: UP, DOWN, LEFT, RIGHT, SPACE", fnt1.getSize(), WINDOW_WIDTH / 2, 300);
            printStringCenter("Press M to Menu", fnt1.getSize(), WINDOW_WIDTH / 2, 450);

        } else if (Game.getState() == GameState.LOSE) {

            // DRAWS BACKGROUND
            g.fillRect(0, 0, 800, 600);

            // SETS FONT
            Font fnt0 = new Font("arial", Font.BOLD, 50);
            g.setFont(fnt0);
            g.setColor(Color.WHITE);

            printStringCenter("YOU LOSE!", fnt0.getSize(), WINDOW_WIDTH / 2, 200);

            // SETS FONT
            Font fnt1 = new Font("arial", Font.BOLD, 30);
            g.setFont(fnt1);

            printStringCenter("Press Q to Quit", fnt1.getSize(), WINDOW_WIDTH / 2, 300);
        }

    }

    private void printStringCenter(String s, int width, int x, int y) {

        int strLength = (int) g2d.getFontMetrics().getStringBounds(s, g2d).getWidth();
        int start = width / 2 - strLength / 2 - width / 2;
        g2d.drawString(s, start + x, y);

    }

} // end class Menu
