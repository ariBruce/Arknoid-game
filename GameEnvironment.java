//Aryeh bruce
import java.util.ArrayList;

/**
 * @author Aryeh Bruce
 * date - 22.04.2021
 * This class the limits of the ball and colidable objects in the game, and therefor contains a list of them.
 */
public class GameEnvironment {

    private static final int MOVEMENT_PER_PRESS = 5;
    private ArrayList<Collidable> objectsOnScreen;
    /**
     * This method is the constructor and sets the game environment.
     *
     * @param objectsOnScreen receives a list of Holdable objects.
     */
    public GameEnvironment(ArrayList<Collidable>  objectsOnScreen) {
        this.objectsOnScreen = objectsOnScreen;
    }

    /**
     * This method is the second constructor and sets the game environment.
     */
    public GameEnvironment() {
        this.objectsOnScreen = new ArrayList<Collidable>();
    }

    /**
     * This method gets the game environment.
     *
     * @return a list of objects on the screen.
     */
    public ArrayList<Collidable> getObjectsOnScreen() {
        return objectsOnScreen;
    }
    /**
     * This method is the adds an item to the game environment.
     *
     * @param c the object to add.
     */
    public void addCollidable(Collidable c) {
        objectsOnScreen.add(c);
    }

    /**
     * Assuming an object is moving from line.start() to line.end()
     *  if this object will not collide with any of the collides
     *  in this environment, nothing wil be returned.
     *  otherwise, this method will return the information about the closest collision that is going to occur.
     *
     * @param trajectory line it will be compared to.
     * @return the closest intersecting point (potentially null)
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        CollisionInfo currentCollisionInfo = null;
        for (int i = 0; i < objectsOnScreen.size(); i++) {
            Point potentialCollision
                    = trajectory.closestIntersectionToStartOfLine(objectsOnScreen.get(i).getCollisionRectangle());
            if (currentCollisionInfo == null && potentialCollision != null) {
                currentCollisionInfo = new CollisionInfo(potentialCollision, objectsOnScreen.get(i));
            } else if (potentialCollision != null && trajectory.start().distance(potentialCollision)
                    < trajectory.start().distance(currentCollisionInfo.collisionPoint())) {
                currentCollisionInfo = new CollisionInfo(potentialCollision, objectsOnScreen.get(i));
            }
        }
        return currentCollisionInfo;
    }
}
