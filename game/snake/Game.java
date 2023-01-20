package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

/**
 * Main game class.
 * @author Jesper Bertijn
 * @author Jochem Dijkrent
 */
public class Game extends JPanel implements ActionListener {
    // Game field variables: W + H in pixels, and 'units' that determine the playing field.
    private static final int WIDTH = 499;
    private static final int HEIGHT = 499;
    private static final int UNIT_SIZE = 20;
    private static final int NUMBER_OF_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    private boolean running = false;

    // Snake variables: Arrays for body pixel positions and starting length.
    private int length = 5;
    private final int[] x = new int[NUMBER_OF_UNITS];
    private final int[] y = new int[NUMBER_OF_UNITS];
    // TODO: Getters and setters in KeyboardAdapter
    public static char direction = 'D';

    // Food variables: Storing the amount of food eaten as well as food location.
    private int foodEaten;
    private int foodX;
    private int foodY;
    // Java import functions
    Random random;
    Timer timer;

    Game() {
        random = new Random();
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(new Color(62, 66, 70));
        this.setFocusable(true);
        this.addKeyListener(new KeyboardAdapter());
        play();
    }

    public void addFood() {
        foodX = random.nextInt(WIDTH / UNIT_SIZE)*UNIT_SIZE;
        foodY = random.nextInt(HEIGHT / UNIT_SIZE)*UNIT_SIZE;
    }

    public void play() {
        addFood();
        running = true;
        timer = new Timer(100, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        draw(graphics);
    }

    public void move() {
        for (int i = length; i > 0; i--) {
            // shift the snake one unit to the desired direction to create a move
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        if (direction == 'L') {
            x[0] = x[0] - UNIT_SIZE;
        } else if (direction == 'R') {
            x[0] = x[0] + UNIT_SIZE;
        } else if (direction == 'U') {
            y[0] = y[0] - UNIT_SIZE;
        } else {
            y[0] = y[0] + UNIT_SIZE;
        }
    }

    public void checkFood() {
        if(x[0] == foodX && y[0] == foodY) {
            length++;
            foodEaten++;
            addFood();
        }
    }

    public void draw(Graphics graphics) {

        if (running) {
            // Food
            graphics.setColor(new Color(210, 115, 90));
            graphics.fillOval(foodX, foodY, UNIT_SIZE, UNIT_SIZE);

            // Snake head
            graphics.setColor(new Color(255, 255, 255));
            graphics.fillRect(x[0], y[0], UNIT_SIZE, UNIT_SIZE);

            // Snake body
            for (int i = 1; i < length; i++) {
                graphics.setColor(new Color(40, 200, 150));
                graphics.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }

            // Score counter
            graphics.setColor(new Color(255, 255, 255));
            graphics.setFont(new Font("Sans serif", Font.PLAIN, 25));
            FontMetrics metrics = getFontMetrics(graphics.getFont());
            graphics.drawString("Score: " + foodEaten, (WIDTH - metrics.stringWidth("Score: " + foodEaten)) / 2, graphics.getFont().getSize());

        } else {
            gameOver(graphics);
        }
    }

    public void checkHit() {
        // check if head run into its body
        for (int i = length; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                running = false;
                break;
            }
        }

        // check if head run into walls
        if (x[0] < 0 || x[0] > WIDTH || y[0] < 0 || y[0] > HEIGHT) {
            running = false;
        }

        if(!running) {
            timer.stop();
        }
    }

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
    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (running) {
            move();
            checkFood();
            checkHit();
        }
        repaint();
    }
}