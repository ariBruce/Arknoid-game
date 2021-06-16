import biuoop.DrawSurface;
import java.awt.Color;

/**
 * this class represents a background object.
 */
public class Background implements Sprite {
    private Color color;

    /**
     * construct a background object from a given color.
     * @param color the given color.
     */
    public Background(Color color) {
        this.color = color;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
    }

    @Override
    public void timePassed() {

    }
}