//Aryeh Bruce
/**
 * @author Aryeh Bruce
 * date - 03.06.2021
 * This class holds the ball remover listener and will remove the ball from the game when needed.
 */
public class BallRemover implements HitListener {

    private GameLevel gameLevel;

    /**
     * This method represents the constructor and will receive the ball remover private fields.
     *
     * @param gameLevel the game that the balls will be removed from.
     */
    public BallRemover(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //remove when hits dead zone block
        if (beingHit.getColor() == null) {
            hitter.removeHitListener(this);
            hitter.removeFromGame(gameLevel);
            gameLevel.getBallsInGame().decrease(1);
        }
    }
}
