package snake;

/**
 * Application entrypoint.
 * @author Jesper Bertijn
 * @author Jochem Dijkrent
 * @design-pattern singleton
 */
public class Main {
    private static Frame INSTANCE;

    /**
     * Class-based singleton to make sure only one game can exist at a time.
     * @return New or existing frame class constructor
     */
    public static Frame getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new Frame();
        }
        return INSTANCE;
    }

    /**
     * Application entrypoint.
     * @param args Launch parameters
     */
    public static void main(String[] args) {
        getInstance();
    }
}