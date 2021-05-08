package SpaceShips.engine;

import SpaceShips.Constants;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Player extends Engine {
    private int width;

    public Player() {
        initPlayer();
    }

    private void initPlayer() {
        var playerImg = Constants.PLAYER_IMG;
        ImageIcon icon = new ImageIcon(playerImg);

        width = icon.getImage().getWidth(null);
        setImage(icon.getImage());

        int START_X = Constants.GAME_WIDTH / 2 - Constants.PLAYER_WIDTH;
        setX(START_X);

        int START_Y = Constants.GROUND - 10;
        setY(START_Y);
    }

    public void movement() {
        x += moving;

        if (x <= 2) {
            x = 2;
        }

        if (x >= Constants.GAME_WIDTH - 2 * width) {
            x = Constants.GAME_WIDTH - 2 * width;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            moving = -Constants.MOVEMENT;
        }

        if (key == KeyEvent.VK_RIGHT) {
            moving = Constants.MOVEMENT;
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            moving = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            moving = 0;
        }
    }
}
