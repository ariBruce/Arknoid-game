// Aryeh Bruce

import java.awt.Color;

/**
 * @author Aryeh Bruce
 * date - 03.06.2021
 * This class represents the score tracker for the game.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * This method represents the constructor for the score tracker listener.
     *
     * @param scoreCounter the counter to keep track of the score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //don't count score for boarders of dead zone
        if (beingHit.getColor() == null || beingHit.getColor() == Color.GRAY) {
            return;
        }
        currentScore.increase(5);
    }
}
