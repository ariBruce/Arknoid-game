//Aryeh bruce 209313907
import biuoop.DrawSurface;

/**
 * @author Aryeh Bruce
 * date - 22.04.2021
 * <p>
 * This class represents a ball, it has a center point, radius, color velocity and frame.
 * the class has the appropriate getters and a methoud in order to draw the ball,
 * move the ball and sets its limits.
 * </p>
 */

public class Ball implements Sprite {

    private Point center; //center point of the ball
    private int radius; //radius of the ball
    private java.awt.Color color; //color of the ball
    private Velocity speedAndDirection; //speed and direction of the ball
    private GameEnvironment ballLimits; //the balls movement limits
    private  CollisionInfo previousCollisionInfo; // the balls last collision

    /**
     * This method represents the first of two constructors and will receive the circle variables.
     *
     * @param center is the center point of the circle that is received.
     * @param radius is the radius of the circle that is received.
     * @param color  the color of the circle that is received.
     */
    public Ball(Point center, int radius, java.awt.Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
    }

    /**
     * This method represents the second of two constructors and will receive the circle variables.
     *
     * @param xOfPoint is the x coordinate of the center point of the circle that is received.
     * @param yOfPoint is the y coordinate of the center point of the circle that is received.
     * @param radius   is the radius of the circle that is received.
     * @param color    the color of the circle that is received.
     */
    public Ball(double xOfPoint, double yOfPoint, int radius, java.awt.Color color) {
        this.center = new Point(xOfPoint, yOfPoint);
        this.radius = radius;
        this.color = color;
    }

    /**
     * This method is the accessor to the x coordinate of the center point of the circle.
     *
     * @return the x coordinate of the center point.
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * This method is the accessor to the y coordinate of the center point of the circle.
     *
     * @return the y coordinate of the center point.
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * This method is the accessor to the center point of the circle.
     *
     * @return the radius of the circle.
     */
    public Point getCenter() {
        return center;
    }

    /**
     * This method is the accessor to the radius of the circle.
     *
     * @return the radius of the circle.
     */
    public int getSize() {
        return radius;
    }

    /**
     * This method is the accessor to the velocity of the ball.
     *
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return speedAndDirection;
    }

    /**
     * This method is the accessor to the color of the circle.
     *
     * @return the color of the circle.
     */
    public java.awt.Color getColor() {
        return color;
    }

    /**
     * This method is the accessor to the frame of the circle's limits.
     *
     * @return the frame of the limits of the ball.
     */
    public GameEnvironment getGameEnvironment() {
        return ballLimits;
    }

    /**
     * This method sets the velocity of the ball.
     *
     * @param v the velocity to be put into the ball.
     */
    public void setVelocity(Velocity v) {
        speedAndDirection = v;
    }

    /**
     * This method sets the velocity of the ball based on the changes needed on the axis's.
     *
     * @param dx the change.
     * @param dy the speed of the new velocity.
     */
    public void setVelocity(double dx, double dy) {
        this.speedAndDirection = new Velocity(dx, dy);
    }

    /**
     * This method sets the limits of movement for the ball.
     *
     * @param gameEnvironment the rectangle's in the game that will be collided with
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        ballLimits = gameEnvironment;
    }
    /**
     * This method draws the ball on the given DrawSurface.
     *
     * @param surface the surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(getColor()); //set color for the circles
        surface.fillCircle(getX(), getY(), radius); //draw the circle
    }

    /**
     * This method fix's the location of the ball and places it one step
     * in the direction and at the speed of the velocity.
     * The method does this by advancing the location of the ball by its velocity,
     * and in the process calls other methods in order to
     * adjust the velocity when needed and find proper collision points.
     */
    public void moveOneStep() {
        Line fullTrajectory = new Line(center.getX(), center.getY(),
                speedAndDirection.applyToPoint(center).getX(), speedAndDirection.applyToPoint(center).getY());
        //check for collision object
        CollisionInfo aboutToHit = ballLimits.getClosestCollision(fullTrajectory);
        if (aboutToHit != null) {
            //avoid getting stuck if the middle lands exactly on a line
            if (previousCollisionInfo != null
                    && previousCollisionInfo.collisionPoint().equals(aboutToHit.collisionPoint())) {
                center = getVelocity().applyToOneAbovePoint(center);
                return;
            }
            speedAndDirection = aboutToHit.collisionObject().hit(aboutToHit.collisionPoint(), speedAndDirection);
            previousCollisionInfo = aboutToHit;
            //in order not to bounce into another block
            return;
        }
        center = getVelocity().applyToPoint(center);
    }

    /**
     * This method will notify the ball to continue on its course during the next frame.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * This method will insert the ball into the game.
     *
     * @param game the holder of the needed gui.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}

