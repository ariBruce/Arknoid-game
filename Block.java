//Aryeh bruce 209313907

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * @author Aryeh Bruce
 * date - 22.04.2021
 * <p>
 * This class represents a block on the screen that the ball can colide with.
 * </p>
 */
public class Block implements Collidable, Sprite {

    private Rectangle blockShape;
    private boolean amIHit;
    private Color color;

    /**
     * This method represents the constructor for the current block.
     *
     * @param upperLeft the ypper left corner of the block.
     * @param width the width of the block.
     * @param height the height of the block.
     */
    public Block(Point upperLeft, double width, double height) {
        this.blockShape = new Rectangle(upperLeft, width, height);
        this.amIHit = false;
    }
    /**
     * This method represents the constructor for the current block.
     *
     * @param blockShape the shape and size of the block in rectangle form.
     */
    public Block(Rectangle blockShape) {
        this.blockShape = blockShape;
        this.amIHit = false;
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
     * This method represents the getter for the current block.
     *
     * @return receive if block is hit.
     */
    public boolean getIfHit() {
        return amIHit;
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
     * @return the new velocity based on impact.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        Line pointIntoLine = new Line(collisionPoint, collisionPoint);
        amIHit = true;
        //check every side of the rectangle to see if it has been collided with
        if (blockShape.getUpperLine().intersectionWith(pointIntoLine) != null) {
            return new Velocity(currentVelocity.getdx(), currentVelocity.getdy() * (-1));
        }
        if (blockShape.getLowerLine().intersectionWith(pointIntoLine) != null) {
            return new Velocity(currentVelocity.getdx(), currentVelocity.getdy() * (-1));
        }
        return new Velocity(currentVelocity.getdx()  * (-1), currentVelocity.getdy());
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
            d.setColor(color);
            d.fillRectangle(upperXValue, upperYValue, width, height);
            d.setColor(Color.BLACK);
            d.drawRectangle(upperXValue, upperYValue, width, height);

    }
    /**
     * This method will in the future clla the block to action when needed.
     */
    public void timePassed() {

    }
    /**
     * This method insert every block into the game.
     *
     * @param game the holder of the collections the the block needs to be inserted into.
     */
    public void addToGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
    }
}
