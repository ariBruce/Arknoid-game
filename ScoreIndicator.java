// Aryeh Bruce
import biuoop.DrawSurface;
import java.awt.Color;
/**
 * @author Aryeh Bruce
 * date - 03.06.2021
 * This class represents the score indicator for the score on the screen.
 */
public class ScoreIndicator implements Sprite {
    private GameLevel gameLevel;
    private Rectangle blockShape;

    /**
     * This method represents the ScoreIndicator constructor.
     *
     * @param gameLevel the game to present the score on.
     */
    public ScoreIndicator(GameLevel gameLevel) {
        this.gameLevel = gameLevel;
        this.blockShape = new Rectangle(new Point(1, 1),  800,  25);
    }
    @Override
    public void drawOn(DrawSurface d) {
        int upperXValue = (int) blockShape.getUpperLine().start().getX();
        int upperYValue = (int) blockShape.getUpperLine().start().getY();
        int width = (int) blockShape.getWidth();
        int height = (int) blockShape.getHeight();
        d.setColor(Color.WHITE);
        d.fillRectangle(upperXValue, upperYValue, width, height);
        d.setColor(Color.BLACK);
        d.drawRectangle(upperXValue, upperYValue, width, height);
        d.drawText(350, 20, "Score: " + gameLevel.getScore().getValue(), 15);
    }

    @Override
    public void timePassed() {

    }
}
