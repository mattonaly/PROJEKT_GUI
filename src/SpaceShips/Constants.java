package SpaceShips;

public interface Constants {
    int GAME_WIDTH = 360;
    int GAME_HEIGHT = 640;
    int BORDER_RIGHT = 30;
    int BORDER_LEFT = 5;
    int BOMB_HEIGHT = 5;
    int SPACESHIP_HEIGHT = 12;
    int SPACESHIP_WIDTH = 12;
    int SPACESHIP_INIT_X = 150;
    int SPACESHIP_INIT_Y = 5;

    int MOVEMENT = 2;
    int GO_DOWN = 15;
    int NUMBER_OF_SPACESHIPS_TO_DESTROY = 24;
    int CHANCE = 5;
    int DELAY = 15;
    int STRIKE_DELAY = 200;
    int PLAYER_WIDTH = 15;
    int PLAYER_HEIGHT = 10;

    int GROUND = GAME_HEIGHT - 40;

    String PLAYER_IMG = "src/SpaceShips/assets/player.png";
    String SHOT_IMG = "src/SpaceShips/assets/shot.png";
    String SPACESHIP_IMG = "src/SpaceShips/assets/spaceship.png";
    String BOMB_IMG = "src/SpaceShips/assets/bomb.png";
    String EXPLOSION_IMG = "src/SpaceShips/assets/explosion.png";
    String HEART_IMG = "src/SpaceShips/assets/heart.png";

    String WON = "You won!";
    String LAND = "Space ships landed!";
    String LOST = "Game Over!";
}