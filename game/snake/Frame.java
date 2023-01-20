package snake;

import javax.swing.JFrame;

/**
 * The JFrame window upon which the game is rendered.
 * @author Jesper Bertijn
 * @author Jochem Dijkrent
 * @design-pattern Facade
 */
public class Frame extends JFrame {
    Frame() {
        Game game = new Game();
        this.add(game);
        this.setTitle("Snake game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }
}
