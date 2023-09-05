// Aryeh Bruce
/**
 * @author Aryeh Bruce
 * date - 25.04.2021
 * This class holds the main method to run the game.
 */

public class GameStarter {

    /**
     * This method runs and initializes the game.
     *
     * @param args the unused arguments inserted into the program
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.initialize();
        game.run();
    }
}
