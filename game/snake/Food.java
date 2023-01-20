package snake;

import static snake.Game.*;
/**
 * The score object
 * @author Jochem Dijkrent
 */
public class Food {
    public static int foodX;
    public static int foodY;

    /**
     *  Chooses on a random spot in the frame where a food item could spawn.
     */
    public static void addFood() {
        foodX = Game.random.nextInt(WIDTH / UNIT_SIZE) * UNIT_SIZE;
        foodY = Game.random.nextInt(HEIGHT / UNIT_SIZE) * UNIT_SIZE;
    }
}