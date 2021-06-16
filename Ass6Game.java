//209313907 Aryeh Bruce

import biuoop.GUI;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Aryeh Bruce
 * date - 25.04.2021
 * This class holds the main method to run the game.
 */

public class Ass6Game {

    /**
     * This method runs and initializes the game.
     *
     * @param args the unused arguments inserted into the program
     */
    public static void main(String[] args) {
        AnimationRunner runner = new AnimationRunner(new GUI("Arkanoid", 800, 600));
        GameFlow game = new GameFlow(runner, runner.getGui().getKeyboardSensor());
        List<LevelInformation> levels = new ArrayList<LevelInformation>();
            for (int i = 0; i < args.length; i++) {
                if (args[i].length() != 1) {
                    continue;
                }
                try {
                    int j = Integer.parseInt(args[i]);
                } catch (Exception e) {
                    continue;
                }
                if (Integer.parseInt(args[i]) == 1) {
                    levels.add(new Level1());
                } else if (Integer.parseInt(args[i]) == 2) {
                    levels.add(new Level2());
                } else if (Integer.parseInt(args[i]) == 3) {
                    levels.add(new Level3());
                } else if (Integer.parseInt(args[i]) == 4) {
                    levels.add(new Level4());
                }
            }
        if (levels.size() == 0) {
            levels.add(new Level1());
            levels.add(new Level2());
            levels.add(new Level3());
            levels.add(new Level4());
        }
            game.runLevels(levels);
    }
}
