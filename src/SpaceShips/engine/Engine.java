package SpaceShips.engine;

import SpaceShips.Constants;

import java.awt.*;

public class Engine {

    private boolean visible;
    private Image image;
    private boolean dying;

    int x, y, moving;

    public Engine() {
        visible = true;
    }

    public void die() {
        visible = false;
    }

    public boolean isVisible() {
        return visible;
    }

    protected void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setDying(boolean dying) {
        this.dying = dying;
    }

    public boolean isDying() {
        return this.dying;
    }

    public void revive() {
        int START_X = Constants.GAME_WIDTH / 2 - Constants.PLAYER_WIDTH;
        setX(START_X);
        int START_Y = Constants.GROUND - 10;
        setY(START_Y);
        this.dying = false;
        this.visible = true;
    }
}