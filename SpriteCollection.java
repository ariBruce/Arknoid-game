//Aryeh Bruce
import biuoop.DrawSurface;
import java.util.ArrayList;
/**
 * @author Aryeh Bruce
 * date - 25.04.2021
 * This class holds the collection of all sprites in the game.
 */
public class SpriteCollection {

    private ArrayList<Sprite> spritesOnScreen;
    /**
     * This method represents the constructor of the sprite collection.
     */
    public SpriteCollection() {
        this.spritesOnScreen = new ArrayList<Sprite>();
    }
    /**
     * This method represents the getter of the sprite collection.
     *
     * @return the list of sprites.
     */
    public ArrayList<Sprite> getSpriteCollection() {
        return spritesOnScreen;
    }
    /**
     * This method allows an addition of sprites to the sprite collection.
     *
     * @param s the sprite to be added.
     */
    public void addSprite(Sprite s) {
        spritesOnScreen.add(s);
    }
    /**
     * This method will notify all sprites that time has passed and act accordingly.
     */
    public void notifyAllTimePassed() {
        for (int i = 0; i < spritesOnScreen.size(); i++) {
            spritesOnScreen.get(i).timePassed();
        }
    }

    /**
     * This method will draw all sprites that on the screen.
     *
     * @param d the surface to draw the sprites on.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < spritesOnScreen.size(); i++) {
            spritesOnScreen.get(i).drawOn(d);
        }
    }
}
