package window;

import framework.Peripheral;
import objects.Player;

import java.awt.*;
import java.util.ArrayList;

/**
 * @author Philip Yu
 */
public class Statistics {

    // VARIABLES
    private static int spaceX = 15;
    private static int spaceY = 15;

    // OBJECTS
    private static Player player, p2;
    private static MapLoader mapLoader;
    private static Handler handler;

    public Statistics(Player player) {

        this.player = player;
        this.handler = handler;
        mapLoader = new MapLoader();

    }

    public static void renderMapSkeleton(Graphics g) {

//        mapLoader.loadW(g);

    }

    public static void renderForeground(Graphics g) {

        resetString();
        drawString(g, "");
        drawString(g, "AmountKeysPressed: " + Peripheral.getKeysPressed().size());
        drawList(g, toStringList("KeyPressed: ", Peripheral.getKeysPressed()));
        drawString(g, "");
        drawString(g, "MouseX: " + Peripheral.getMouseX());
        drawString(g, "MouseY: " + Peripheral.getMouseY());
        drawString(g, "");
        drawString(g, "PlayerX: " + player.getX());
        drawString(g, "PlayerY: " + player.getY());
        drawString(g, "PlayerAngle: " + player.getAngle());
        drawString(g, "PlayerRev:" + player.getRevolutions());
        drawString(g, "");

    }

    private static void resetString() {

        spaceY = 15;

    }

    private static void drawString(Graphics g, String text) {

        g.setColor(Color.WHITE);
        g.drawString(text, spaceX, spaceY);
        spaceY += 15;

    }

    private static void drawList(Graphics g, java.util.List<String> list) {

        for (String text : list) {
            drawString(g, text);
        }

    }

    private static java.util.List<String> toStringList(String pre, java.util.List list) {

        java.util.List<String> stringList = new ArrayList<>();

        for (Object object : list) {
            stringList.add(pre + object.toString());
        }

        return stringList;

    }

} // end class Statistics
