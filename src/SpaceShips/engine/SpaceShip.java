package SpaceShips.engine;

import SpaceShips.Constants;

import javax.swing.ImageIcon;

public class SpaceShip extends Engine {
    private Bomb bomb;

    public SpaceShip(int x, int y) {
        initAsteroid(x,y);
    }

    private void initAsteroid(int x, int y) {
        this.x = x;
        this.y = y;

        bomb = new Bomb(x,y);

        var asteroidImage = Constants.SPACESHIP_IMG;
        ImageIcon icon = new ImageIcon(asteroidImage);

        setImage(icon.getImage());
    }

    public void movement(int direction) {
        this.x += direction;
    }

    public Bomb getBomb() {
        return bomb;
    }

    public class Bomb extends Engine {
        private boolean destroyed;

        public Bomb(int x, int y) {
            initBomb(x, y);
        }

        private void initBomb(int x, int y) {
            setDestroyed(true);

            this.x = x;
            this.y = y;

            var bombImg = Constants.BOMB_IMG;
            ImageIcon icon = new ImageIcon(bombImg);
            setImage(icon.getImage());
        }

        public void setDestroyed(boolean destroyed) {
            this.destroyed = destroyed;
        }

        public boolean isDestroyed() {
            return destroyed;
        }
    }

}
