package SpaceShips.entities;

import SpaceShips.Settings;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Player extends Entity {
    private int width;

    public Player() {
        initPlayer();
    }

    private void initPlayer() {
        var playerImg = Settings.PLAYER_IMG;
        ImageIcon icon = new ImageIcon(playerImg);

        width = icon.getImage().getWidth(null);
        setImage(icon.getImage());

        int START_X = Settings.GAME_WIDTH / 2 - Settings.PLAYER_WIDTH;
        setX(START_X);

        int START_Y = Settings.GROUND - 10;
        setY(START_Y);
    }

    public void movement() {
        x += moving;

        if (x <= 2) {
            x = 2;
        }

        if (x >= Settings.GAME_WIDTH - 2 * width) {
            x = Settings.GAME_WIDTH - 2 * width;
        }
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            moving = -Settings.MOVEMENT;
        }

        if (key == KeyEvent.VK_RIGHT) {
            moving = Settings.MOVEMENT;
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
