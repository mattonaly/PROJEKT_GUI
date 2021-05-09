package SpaceShips.entities;

import SpaceShips.Settings;

import java.awt.*;

public class Entity {
    private boolean visible;
    private Image image;
    private boolean dying;

    int x, y, moving;

    public Entity() {
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
        int START_X = Settings.GAME_WIDTH / 2 - Settings.PLAYER_WIDTH;
        setX(START_X);
        int START_Y = Settings.GROUND - 10;
        setY(START_Y);
        setDying(false);
        setVisible(true);
    }
}