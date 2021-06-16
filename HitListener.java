//Aryeh bruce 209313907
/**
 * @author Aryeh Bruce
 * date - 03.06.2021
 * This interface represents the objects that are listners to
 * the objects being hit on the screen.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     *
     * @param beingHit the colidable that is hit.
     * @param hitter the ball hitting the object.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
