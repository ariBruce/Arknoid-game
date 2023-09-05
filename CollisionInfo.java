//Aryeh bruce
/**
 * @author Aryeh Bruce
 * date - 22.04.2021
 * <p>
 * This class represents the information of the colission that was made with the colidable.
 * It holds the colidable and the colission point with it.
 * </p>
 */
public class CollisionInfo {

    private Point collisionPoint;
    private Collidable hitBlock;
    /**
     * This method represents constructor for this class.
     *
     * @param collisionPoint the point it collides with the collidable.
     * @param hitBlock the object that has been hit.
     */
    public CollisionInfo(Point collisionPoint, Collidable hitBlock) {
        this.collisionPoint = collisionPoint;
        this.hitBlock = hitBlock;
    }
    /**
     * This method represents getter for this classes collision point.
     *
     * @return the collision point of the class.
     */
    public Point collisionPoint() {
        return collisionPoint;
    }
    /**
     * This method represents getter for this classes collision object.
     *
     * @return the collision object of the class.
     */
    public Collidable collisionObject() {
        return hitBlock;
    }
}
