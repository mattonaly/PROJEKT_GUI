package SpaceShips.engine;

import SpaceShips.Settings;

import javax.swing.*;

public class Strike extends Engine {

    public Strike() { }

    public Strike(int x, int y) {
        initStrike(x, y);
    }

    private void initStrike(int x, int y) {
        var shotImg = Settings.SHOT_IMG;
        ImageIcon icon = new ImageIcon(shotImg);
        setImage(icon.getImage());

        int H_SPACE = 6;
        setX(x + H_SPACE);

        int V_SPACE = 1;
        setY(y - V_SPACE);
    }
}