//Aryeh bruce 209313907
/**
 * @author Aryeh Bruce
 * date - 22.04.2021
 * This interface represents the objects that are colidable on the screen.
 */
public interface Collidable {
    /**
     * This method represents the getter for the objects that are collidable on the screen.
     *
     * @return the proper block size.
     */
    Rectangle getCollisionRectangle();

    /**
     * This method returns the change of velocity to the item that collided with it.
     *
     * @param hitter the ball colliding with the object.
     * @param collisionPoint point of collision with the block.
     * @param currentVelocity the velocity of the object that hit the block.
     * @return the new velocity based on impact.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
