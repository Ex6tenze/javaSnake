package snake;

import static snake.Food.*;
import static snake.Game.*;
/**
 * The player object.
 * @author Jesper Bertijn
 * @author Jochem Dijkdrent
 */
public class Snake {

    // Game field variable:
    public static int foodEaten;

    // Snake variables: Arrays for body pixel positions and starting length.
    static int length = 5;
    static final int[] x = new int[NUMBER_OF_UNITS];
    static final int[] y = new int[NUMBER_OF_UNITS];
    public static char direction = 'D';


    /**
     * Controls for the player to control the snake.
     */
    public static void move() {
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

    /**
     *  Checks if the snake has eaten the food,
     *  and makes the snake bigger if it has eaten the food.
     *  Also spawns new food item if there are zero food items on the screen.
     */
    public static void checkFood() {
        if(x[0] == foodX && y[0] == foodY) {
            length++;
            foodEaten++;
            addFood();
        }
    }

    /**
     * Checks if the snake has run into itself or into a wall.
     */
    public static void checkHit() {
        // check if head run into its body
        for (int i = length; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                Game.running = false;
                break;
            }
        }

        // check if head run into walls
        if (x[0] < 0 || x[0] > WIDTH || y[0] < 0 || y[0] > HEIGHT) {
            Game.running = false;
        }

        if(!Game.running) {
            Game.timer.stop();
        }
    }
    /**
     * Calls the necessary functions every frame.
     */
    public static void gameChecks() {
        if (Game.running) {
            move();
            checkFood();
            checkHit();
        }
    }
}
