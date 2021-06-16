//209313907 Aryeh Bruce

import java.awt.Color;

/**
 * @author Aryeh Bruce
 * date - 03.06.2021
 * This class holds the block remover listener and will remove the block from the game when needed.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * This method represents the constructor and will receive the ball remover private fields.
     *
     * @param gameLevel the game that the balls will be removed from.
     * @param removedBlocks the counter to keep track of the blocks in the game
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //don't remove borders or dead zone
        if (beingHit.getColor() == null || beingHit.getColor() == Color.GRAY) {
            return;
        }
        //remove any other blocks
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(gameLevel);
        remainingBlocks.decrease(1);
    }
}
