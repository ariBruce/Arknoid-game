// Aryeh Bruce
import biuoop.DrawSurface;
import java.awt.Color;
    /**
     * @author Aryeh Bruce
     * date - 03.06.2021
     * This class represents the score indicator for the score on the screen.
     */
    public class LevelName implements Sprite {
        private GameLevel gameLevel;

        /**
         * This method represents the ScoreIndicator constructor.
         *
         * @param gameLevel the game to present the score on.
         */
        public LevelName(GameLevel gameLevel) {
            this.gameLevel = gameLevel;
        }
        @Override
        public void drawOn(DrawSurface d) {
            d.setColor(Color.BLACK);
            d.drawText(600, 20, gameLevel.getCurrentLevel().levelName(), 15);
        }

        @Override
        public void timePassed() {

        }
    }

