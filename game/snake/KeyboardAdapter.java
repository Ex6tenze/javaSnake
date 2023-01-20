package snake;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static snake.Snake.direction;


/**
 * Keyboard listener class using java.awt.event.
 * @author Jesper Bertijn
 * @design-pattern Adapter
 */
public class KeyboardAdapter extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> {
                if (direction != 'R') {
                    direction = 'L';
                }
            }
            case KeyEvent.VK_RIGHT -> {
                if (direction != 'L') {
                    direction = 'R';
                }
            }
            case KeyEvent.VK_UP -> {
                if (direction != 'D') {
                    direction = 'U';
                }
            }
            case KeyEvent.VK_DOWN -> {
                if (direction != 'U') {
                    direction = 'D';
                }
            }
        }
    }
}
