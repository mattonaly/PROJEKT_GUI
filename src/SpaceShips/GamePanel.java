package SpaceShips;

import SpaceShips.entities.SpaceShip;
import SpaceShips.entities.Player;
import SpaceShips.entities.Strike;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GamePanel extends JPanel {
    private List<SpaceShip> spaceShips;
    private Player player;
    private List<Strike> strikes;

    private int direction = -1;
    private int lives = 3;
    private int kills = 0;
    private int points = 0;
    private int record = -1;

    private boolean inGame = false;
    private boolean hasRecord = false;
    private String message = Settings.START;

    private final Timer timer = new Timer(Settings.DELAY, new UpdateGamePanel());
    private final Timer timePoints = new Timer(100, event -> addPoints());

    File recordFile = new File("record.txt");

    public GamePanel() {
        initGamePanel();
    }

    private void initGamePanel() {
        addKeyListener(new KeyPressAdapter());
        setFocusable(true);
        setBackground(Color.black);
    }

    private void startGame() {
        checkRecord();
        gameInit();
        timer.start();
        timePoints.start();
    }

    private void checkRecord() {
        if (!recordFile.exists()) {
            try {
                recordFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileReader fileReader = new FileReader(recordFile);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                String line;
                while((line = bufferedReader.readLine()) != null){
                    record = Integer.parseInt(line);
                    hasRecord = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void gameInit() {
        inGame = true;
        lives = 3;
        kills = 0;
        points = 0;

        spaceShips = new ArrayList<>();

        for (int i = 0; i < Settings.NUMBER_OF_SPACESHIPS_ROWS; i++) {
            for (int j = 0; j < Settings.NUMBER_OF_SPACESHIPS_COLUMNS; j++) {
                var asteroid = new SpaceShip(Settings.SPACESHIP_INIT_X + 18 * j,
                        Settings.SPACESHIP_INIT_Y + 18 * i);
                spaceShips.add(asteroid);
            }
        }

        player = new Player();
        strikes = new ArrayList<>();
    }

    private void addPoints() {
        points += Settings.POINTS_PER_TICK;
    }

    private void drawAsteroids(Graphics g) {
        for (SpaceShip spaceShip : spaceShips) {
            if (spaceShip.isVisible()) {
                g.drawImage(spaceShip.getImage(), spaceShip.getX(), spaceShip.getY(), this);
            }

            if (spaceShip.isDying()) {
                spaceShip.die();
            }
        }
    }

    private void drawPlayer(Graphics g) {
        if (player.isVisible()) {
            g.drawImage(player.getImage(), player.getX(), player.getY(), this);
        }

        if (player.isDying()) {
            player.die();
            if (lives > 0) {
                String playerImg = Settings.PLAYER_IMG;
                ImageIcon icon = new ImageIcon(playerImg);
                player.setImage(icon.getImage());
                player.revive();
                System.out.println(lives + " lives left");
            } else {
                inGame = false;
                message = Settings.LOST;
            }
        }
    }

    private void drawStrike(Graphics g) {
        for (Strike strike : strikes) {
            if (strike.isVisible()) {
                g.drawImage(strike.getImage(), strike.getX(), strike.getY(), this);
            }
        }
    }

    private void drawBombing(Graphics g) {
        for (SpaceShip a : spaceShips) {
            SpaceShip.Bomb b = a.getBomb();

            if (!b.isDestroyed()) {
                g.drawImage(b.getImage(), b.getX(), b.getY(), this);
            }
        }
    }

    private void drawHearts(Graphics g) {
        var heartImg = Settings.HEART_IMG;
        ImageIcon icon = new ImageIcon(heartImg);
        int heartWidth = 20;
        int x = heartWidth + heartWidth / 2;
        for (int i = 0; i < lives; i++) {
            g.drawImage(icon.getImage(), Settings.GAME_WIDTH / 2 - x, 8, this);
            x -= heartWidth;
        }
    }

    private void drawPoints(Graphics g) {
        String text = "Points: " + points;
        g.drawString(text, Settings.BORDER_LEFT, 20);
    }

    private void drawRecord(Graphics g) {
        String text = "Record: " + record;
        int width = g.getFontMetrics().stringWidth(text);
        g.drawString(text, Settings.GAME_WIDTH - width - 20, 20);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    private void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Settings.GAME_WIDTH, Settings.GAME_HEIGHT);
        g.setColor(Color.white);
        g.drawLine(0, 30, Settings.GAME_WIDTH, 30);
        g.setColor(Color.green);

        if (inGame) {
            drawAsteroids(g);
            drawPlayer(g);
            drawStrike(g);
            drawBombing(g);
            drawHearts(g);
            drawPoints(g);

            if (hasRecord) {
                drawRecord(g);
            }

        } else {
            if (timer.isRunning() && timePoints.isRunning()) {
                timer.stop();
                timePoints.stop();
            }
            gameOver(g);
        }
    }

    private void gameOver(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(0, 0, Settings.GAME_WIDTH, Settings.GAME_HEIGHT);

        g.setColor(new Color(0, 32, 48));
        g.fillRect(50, Settings.GAME_WIDTH / 2 - 20, Settings.GAME_WIDTH - 100, 50);
        g.setColor(Color.white);
        g.drawRect(50, Settings.GAME_WIDTH / 2 - 20, Settings.GAME_WIDTH - 100, 50);

        var small = new Font("Helvetica", Font.BOLD, 14);
        var fontMetrics = this.getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);

        if (points > 0) {
            g.drawString(message, (Settings.GAME_WIDTH - fontMetrics.stringWidth(message)) / 2,
                    Settings.GAME_WIDTH / 2);
            g.drawString(points + " points", (Settings.GAME_WIDTH - fontMetrics.stringWidth(points + " points")) / 2,
                    Settings.GAME_WIDTH / 2 + fontMetrics.getHeight() + 5);
        } else {
            g.drawString(message, (Settings.GAME_WIDTH - fontMetrics.stringWidth(message)) / 2,
                    Settings.GAME_WIDTH / 2 + fontMetrics.getHeight() / 2);
        }

        if ((points < record && message.equals(Settings.WON)) || record < 0 && message.equals(Settings.WON)) {
            try {
                PrintWriter printWriter = new PrintWriter(recordFile);
                printWriter.print(points);
                printWriter.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        int spaceShipsCount = Settings.NUMBER_OF_SPACESHIPS_ROWS * Settings.NUMBER_OF_SPACESHIPS_COLUMNS;

        if (kills == spaceShipsCount) {
            inGame = false;
            timer.stop();
            timePoints.stop();
            message = Settings.WON;
        }

        player.movement();

        String expImg = Settings.EXPLOSION_IMG;
        for (Strike strike : strikes) {
            if (strike.isVisible()) {
                int shotX = strike.getX();
                int shotY = strike.getY();

                for (SpaceShip spaceShip : spaceShips) {
                    int asteroidX = spaceShip.getX();
                    int asteroidY = spaceShip.getY();

                    if (spaceShip.isVisible() && strike.isVisible()) {
                        if (shotX >= (asteroidX)
                                && shotX <= (asteroidX + Settings.SPACESHIP_WIDTH)
                                && shotY >= (asteroidY)
                                && shotY <= (asteroidY + Settings.SPACESHIP_HEIGHT)) {

                            ImageIcon icon = new ImageIcon(expImg);
                            spaceShip.setImage(icon.getImage());
                            spaceShip.setDying(true);
                            kills++;
                            strike.die();
                        }
                    }
                }

                int y = strike.getY();
                y -= 4;

                if (y < 0) {
                    strike.die();
                } else {
                    strike.setY(y);
                }
            }
        }

        for (SpaceShip spaceShip : spaceShips) {
            int x = spaceShip.getX();
            Iterator<SpaceShip> iterator = spaceShips.iterator();

            if (x >= Settings.GAME_WIDTH - Settings.BORDER_RIGHT && direction != -1) {
                direction = -1;

                while (iterator.hasNext()) {
                    SpaceShip ss = iterator.next();
                    ss.setY(ss.getY() + Settings.GO_DOWN);
                }
            }

            if (x <= Settings.BORDER_LEFT && direction != 1) {
                direction = 1;

                while (iterator.hasNext()) {
                    SpaceShip ss = iterator.next();
                    ss.setY(ss.getY() + Settings.GO_DOWN);
                }
            }
        }

        for (SpaceShip spaceShip : spaceShips) {
            if (spaceShip.isVisible()) {
                int y = spaceShip.getY();

                if (y > Settings.GROUND - Settings.SPACESHIP_HEIGHT) {
                    inGame = false;
                    message = Settings.LAND;
                }

                spaceShip.movement(direction);
            }
        }

        var generator = new Random();

        for (SpaceShip spaceShip : spaceShips) {
            int shot = generator.nextInt(Settings.CHANCE_OF);
            SpaceShip.Bomb bomb = spaceShip.getBomb();

            if (shot == Settings.CHANCE && spaceShip.isVisible() && bomb.isDestroyed()) {
                bomb.setDestroyed(false);
                bomb.setX(spaceShip.getX());
                bomb.setY(spaceShip.getY());
            }

            int bombX = bomb.getX();
            int bombY = bomb.getY();
            int playerX = player.getX();
            int playerY = player.getY();

            if (player.isVisible() && !bomb.isDestroyed()) {
                if (bombX >= (playerX) && bombX <= (playerX + Settings.PLAYER_WIDTH) && bombY >= (playerY) && bombY <= (playerY + Settings.PLAYER_HEIGHT)) {
                    ImageIcon icon = new ImageIcon(expImg);
                    player.setImage(icon.getImage());
                    player.setDying(true);
                    lives--;
                    bomb.setDestroyed(true);
                }
            }

            if (!bomb.isDestroyed()) {
                bomb.setY(bomb.getY() + 1);

                if (bomb.getY() >= Settings.GROUND - Settings.BOMB_HEIGHT) {
                    bomb.setDestroyed(true);
                }
            }
        }
    }

    private class UpdateGamePanel implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            update();
            repaint();
        }
    }

    private class KeyPressAdapter extends KeyAdapter {
        boolean canFire = true;

        @Override
        public void keyReleased(KeyEvent e) {
            if (inGame) {
                player.keyReleased(e);
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (inGame) {
                player.keyPressed(e);
            } else if (key == KeyEvent.VK_ENTER) {
                startGame();
            }

            if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_UP) {
                if (inGame && canFire) {
                    strikes.add(new Strike(player.getX(), player.getY()));
                    canFire = false;
                    Timer strikeDelay = new Timer(Settings.STRIKE_DELAY, event -> canFire = true);
                    strikeDelay.setRepeats(false);
                    strikeDelay.start();
                }
            }

            if (key == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }
        }
    }
}