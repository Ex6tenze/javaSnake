package snake;

/**
 * Application entrypoint.
 * @author Jesper Bertijn
 * @author Jochem Dijkdrent
 * @design-pattern singleton
 */
public class Main {
    private static Frame INSTANCE;

    /**
     * Class-based singleton to make sure only one game can exist at a time.
     */
    public static void getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Frame();
        }
    }

    /**
     * Application entrypoint.
     * @param args Launch parameters
     */
    public static void main(String[] args) {
        getInstance();
    }
}