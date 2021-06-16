//Aryeh bruce 209313907

import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aryeh Bruce
 * date - 03.06.2021
 * <p>
 * This class represents a block on the screen that the ball can colide with.
 * </p>
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private List<HitListener> hitListeners;
    private Rectangle blockShape;
    private Color color;

    /**
     * This method represents the constructor for the current block.
     *
     * @param upperLeft the ypper left corner of the block.
     * @param width the width of the block.
     * @param height the height of the block.
     * @param br this is the inserted listener that will remove blocks from the game.
     * @param st this is the inserted listener that will keep track of the score.
     */
    public Block(Point upperLeft, double width, double height, BlockRemover br, ScoreTrackingListener st) {
        this.hitListeners = new ArrayList<HitListener>();
        this.hitListeners.add(br);
        this.hitListeners.add(st);
        this.blockShape = new Rectangle(upperLeft, width, height);
    }
    /**
     * This method represents the constructor for the current block.
     *
     * @param blockShape the shape and size of the block in rectangle form.
     * @param br this is the inserted listener that will remove blocks from the game.
     * @param st this is the inserted listener that will keep track of the score.
     */
    public Block(Rectangle blockShape, BlockRemover br, ScoreTrackingListener st) {
        this.hitListeners = new ArrayList<HitListener>();
        this.hitListeners.add(br);
        this.hitListeners.add(st);
        this.blockShape = blockShape;
    }
    public Block(Rectangle blockShape){
        this.blockShape = blockShape;
    }
    /**
     * This method represents the getter for the current block.
     *
     * @return the shape and size of the block in rectangle form.
     */
    public Rectangle getCollisionRectangle() {
        return blockShape;
    }

    /**
     * This method represents the getter for the current color of the block.
     *
     * @return the color of the block.
     */
    public Color getColor() {
        return color;
    }

    /**
     * This method represents the setter for the current color of the block.
     *
     * @param newColor the current color of the block.
     */
    public void setColor(Color newColor) {
        this.color = newColor;
    }
    /**
     * This method returns the change of velocity to the item that collided with it.
     *
     * @param collisionPoint point of collision with the block.
     * @param currentVelocity the velocity of the object that hit the block.
     * @param hitter the ball that collided with the brick.
     * @return the new velocity based on impact.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line pointIntoLine = new Line(collisionPoint, collisionPoint);
        Velocity newCorrectVelocity = currentVelocity;
        //check every side of the rectangle to see if it has been collided with
        if (blockShape.getUpperLine().intersectionWith(pointIntoLine) != null) {
            newCorrectVelocity = new Velocity(currentVelocity.getdx(), currentVelocity.getdy() * (-1));
        }
        if (blockShape.getLowerLine().intersectionWith(pointIntoLine) != null) {
            newCorrectVelocity = new Velocity(currentVelocity.getdx(), currentVelocity.getdy() * (-1));
        }
        if (blockShape.getRightLine().intersectionWith(pointIntoLine) != null) {
            newCorrectVelocity = new Velocity(currentVelocity.getdx() * (-1), newCorrectVelocity.getdy());
        }
        if (blockShape.getLeftLine().intersectionWith(pointIntoLine) != null) {
            newCorrectVelocity = new Velocity(currentVelocity.getdx() * (-1), newCorrectVelocity.getdy());
        }
        this.notifyHit(hitter);
        hitter.notifyHit(this);
        return newCorrectVelocity;
    }

    /**
     * this method draws the objects on the screen.
     *
     * @param d the surface to be drawn on.
     */
    public void drawOn(DrawSurface d) {
            int upperXValue = (int) blockShape.getUpperLine().start().getX();
            int upperYValue = (int) blockShape.getUpperLine().start().getY();
            int width = (int) blockShape.getWidth();
            int height = (int) blockShape.getHeight();
            if (color == null) {
                return;
            }
            d.setColor(color);
            d.fillRectangle(upperXValue, upperYValue, width, height);
            if (color == Color.GRAY) {
                return;
            }
            d.setColor(Color.BLACK);
            d.drawRectangle(upperXValue, upperYValue, width, height);

    }
    /**
     * This method will in the future call the block to action when needed.
     */
    public void timePassed() {

    }
    /**
     * This method remove this block from the game.
     *
     * @param gameLevel the holder of the collections the the block needs to remove itself.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }
    /**
     * This method inserts every block into the game.
     *
     * @param gameLevel the holder of the collections the the block needs to be inserted into.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addCollidable(this);
        gameLevel.addSprite(this);
    }
    /**
     * This method will notify all listeners that the ball has hit the block.
     *
     * @param hitter ball that collided with the block.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
   @Override
    public void addHitListener(HitListener hl) {
        hitListeners.add(hl);
    }
    @Override
    public void removeHitListener(HitListener hl) {
        hitListeners.remove(hl);
    }
}
