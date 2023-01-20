package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

import static snake.Food.*;
import static snake.Snake.*;

/**
 * Main game class.
 * @author Jesper Bertijn
 * @author Jochem Dijkdrent
 * @design-pattern state
 */
public class Game extends JPanel implements ActionListener {
    // Width in pixels
    public static final int WIDTH = 499;
    // Height in pixels
    public static final int HEIGHT = 499;
    // Game pixel size
    static final int UNIT_SIZE = 20;
    // Total field size
    static final int NUMBER_OF_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    // Running state
    public static boolean running = false;
    // Minimal score to win
    public static byte winCondition = 25;
    // MilliSeconds Per Frame; game speed, lower = faster.
    public static byte MS_PF = 80;

    // Java import functions
    static Random random;
    static Timer timer;

    /**
     * Class entrypoint method called by Frame
     */
    Game() {
        random = new Random();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(new Color(88, 185, 63));
        this.setFocusable(true);
        this.addKeyListener(new KeyboardAdapter());
        play();
    }

    /**
     * Starts the game by spawning food and starting the timer
     */
    public void play() {
        addFood();
        running = true;
        timer = new Timer(MS_PF, this);
        timer.start();
    }

    /**
     * Draws the game objects as well as the end screens.
     * @param graphics AWT
     */
    public void draw(Graphics graphics) {
        if (running) {
            // Food
            graphics.setColor(new Color(199, 57, 17));
            graphics.fillOval(foodX, foodY, UNIT_SIZE, UNIT_SIZE);

            // Snake head
            graphics.setColor(new Color(211, 211, 211));
            graphics.fillRect(Snake.x[0], Snake.y[0], UNIT_SIZE, UNIT_SIZE);

            // Snake body
            for (int i = 1; i < Snake.length; i++) {
                graphics.setColor(new Color(9, 145, 252));
                graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }

            // Score counter
            graphics.setColor(new Color(255, 255, 255));
            graphics.setFont(new Font("Sans serif", Font.PLAIN, 25));
            FontMetrics metrics = getFontMetrics(graphics.getFont());
            graphics.drawString("Score: " + foodEaten, (WIDTH - metrics.stringWidth("Score: " + foodEaten)) / 2, graphics.getFont().getSize());

        } else if (foodEaten >= winCondition) {
            win(graphics);
        } else {
            gameOver(graphics);
        }
    }

    /**
     * Extension of draw(), defines the game over screen
     * @param graphics AWT
     */
    public void gameOver(Graphics graphics) {
        graphics.setColor(new Color(220, 32, 32));
        graphics.setFont(new Font("Sans serif", Font.PLAIN, 50));
        FontMetrics metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("Game Over", (WIDTH - metrics.stringWidth("Game Over")) / 2, HEIGHT / 2);

        graphics.setColor(new Color(222, 222, 222));
        graphics.setFont(new Font("Sans serif", Font.PLAIN, 25));
        metrics = getFontMetrics(graphics.getFont());
        graphics.drawString("Score: " + foodEaten, (WIDTH - metrics.stringWidth("Score: " + foodEaten)) / 2, graphics.getFont().getSize());

    }

    /**
     * Extension of draw(), defines the win screen
     * @param graphics AWT
     */
    public void win(Graphics graphics) {
            graphics.setColor(new Color(79, 220, 32));
            graphics.setFont(new Font("Sans serif", Font.PLAIN, 50));
            FontMetrics metrics = getFontMetrics(graphics.getFont());
            graphics.drawString("You Won!", (WIDTH - metrics.stringWidth("You Won!")) / 2, HEIGHT / 2);
    }

    /**
     * Checks whether the score is equal or higher to the winning condition, and stops the game when the condition is met.
     * This function is called every frame, while Draw() is only called at specific times.
     */
    public void checkScore() {
        if (foodEaten >= winCondition) {
            running = false;
        }
    }

    /**
     * Runs specific functions to check game state every frame, then renders them.
     * Additionally, it passes any events to the snake class for collision detection.
     * @param arg0 the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent arg0) {
        checkScore();
        Snake.gameChecks();
        repaint();
    }

    /**
     * Draws components onto the panel.
     * @param graphics the Graphics object to protect
     */
    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        draw(graphics);
    }
}