package SpaceShips.entities;

import SpaceShips.Settings;

import javax.swing.ImageIcon;

public class SpaceShip extends Entity {
    private Bomb bomb;
    private Power power;

    public SpaceShip(int x, int y) {
        initSpaceShip(x,y);
    }

    private void initSpaceShip(int x, int y) {
        this.x = x;
        this.y = y;

        bomb = new Bomb(x,y);
        power = new Power(x,y);

        var spaceshipImage = Settings.SPACESHIP_IMG;
        ImageIcon icon = new ImageIcon(spaceshipImage);

        setImage(icon.getImage());
    }

    public void movement(int direction) {
        this.x += direction;
    }

    public Bomb getBomb() {
        return bomb;
    }

    public class Bomb extends Entity {
        private boolean destroyed;

        public Bomb(int x, int y) {
            initBomb(x, y);
        }

        private void initBomb(int x, int y) {
            setDestroyed(true);

            this.x = x;
            this.y = y;

            var bombImg = Settings.BOMB_IMG;
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

    public Power getPower() {
        return power;
    }

    public class Power extends Entity {
        private boolean destroyed;

        public Power(int x, int y) {
            initPower(x, y);
        }

        private void initPower(int x, int y) {
            setDestroyed(true);

            this.x = x;
            this.y = y;

            var powerImg = Settings.POWER_IMG;
            ImageIcon icon = new ImageIcon(powerImg);
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
