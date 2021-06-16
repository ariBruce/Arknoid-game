//Aryeh bruce 209313907
/**
 * @author Aryeh Bruce
 * date - 03.06.2021
 * This interface represents the objects that notify the listners to
 * the objects being hit on the screen.
 */
public interface HitNotifier {
    /**
     * This method will Add hl as a listener to hit events.
     *
     * @param hl the listener to be added.
     */
    void addHitListener(HitListener hl);
    /**
     * This method will Remove hl from the list of listeners to hit events.
     *
     * @param hl the listener to be removed.
     */
    void removeHitListener(HitListener hl);
}
