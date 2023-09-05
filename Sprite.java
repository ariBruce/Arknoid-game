// Aryeh Bruce
import biuoop.DrawSurface;
/**
 * @author Aryeh Bruce
 * date - 25.04.2021
 * This interface represents all objects that are on the screen.
 */
public interface Sprite {
    /**
     * This method draws all of thee needed items.
     *
     * @param d the surface on which to draw on.
     */
    void drawOn(DrawSurface d);
    /**
     * This method will notify all methods that time has passed.
     */
    void timePassed();
}
