import biuoop.KeyboardSensor;

import java.util.List;

public class GameFlow {
    private KeyboardSensor keys;
    private AnimationRunner runner;
    private Counter score;

    public GameFlow(AnimationRunner ar, KeyboardSensor ks) {
        keys = ks;
        runner = ar;
        this.score = new Counter();
    }

    public void runLevels(List<LevelInformation> levels) {
        // ...
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.keys, this.runner, score);
            if (levels.get(levels.size() - 1).equals(levelInfo)) {
                level = new GameLevel(levelInfo, this.keys, this.runner, score, true);
            }

            level.initialize();

            while (level.getBlocksInGame().getValue() != 0 && level.getBallsInGame().getValue() != 0) {
                level.run();
            }

            if (level.getBallsInGame().getValue() == 0) {
                break;
            }

        }
        runner.getGui().close();
    }
}
