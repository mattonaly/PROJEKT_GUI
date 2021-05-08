package SpaceShips;

import javax.swing.*;
import java.awt.*;

public class SpaceShips extends JFrame {

    public SpaceShips() {
        initComponents();
    }

    private void initComponents() {
        add(new GamePanel());
        setTitle("Space ships game");
        setSize(Settings.GAME_WIDTH, Settings.GAME_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
    }

    public static void main(String[] args) {
	// write your code here
        EventQueue.invokeLater(() -> {
            SpaceShips spaceShips = new SpaceShips();
            spaceShips.setVisible(true);
        });
    }
}
