//Aryeh bruce 209313907
import biuoop.DrawSurface;

/**
 * @author Aryeh Bruce
 * date - 01.04.2021
 * <p>
 * This class represents a ball, it has a center point, radius and color.
 * the class has the appropriate getters and a methoud in order to draw the ball.
 * </p>
 */

public class Ball {

    private Point center; //center point of the ball
    private int radius; //radius of the ball
    private java.awt.Color color; //color of the ball
    private Velocity speed; //speed and direction of the ball
    private Frame ballLimits; //the balls movement limits

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
        return speed;
    }

    /**
     * This method is the accessor to the color of the circle.
     *
     * @return the color of the circle.
     */
    public java.awt.Color getColor() {
        return color;
    }

    public Frame getBallLimits() {
        return ballLimits;
    }

    /**
     * This method sets the velocity of the ball.
     *
     * @param v the velocity to be put into the ball.
     */
    public void setVelocity(Velocity v) {
        speed = v;
    }

    /**
     * This method sets the velocity of the ball based on the angle and speed.
     *
     * @param angle the angle of the new velocity.
     * @param givenSpeed the speed of the new velocity.
     */
    public void setVelocity(double angle, double givenSpeed) {
        this.speed = new Velocity(angle, givenSpeed);
    }

    /**
     * This method sts the limits of movement for the ball.
     *
     * @param height maximum height potential of the ball.
     * @param width maximum horizontal potential of the ball.
     */
    public void setBallLimits(double height, double width, Point topLeftCorner) {
        ballLimits = new Frame(height, width, topLeftCorner);
    }
    public void setBallLimits(double height, double width, double topLeftCornerX, double topLeftCornerY) {
        ballLimits = new Frame(height, width, topLeftCornerX, topLeftCornerY);
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
     * and in the process changes the balls velocity as it reaches its limits.
     */
    public void moveOneStep() {
        //if the ball is going out of the frame on the x axis change it's direction.
        if ((center.getX() - radius < ballLimits.getLeftmostPointX() && speed.getAngle() < 0)
            || (ballLimits.getFrameWidth() + ballLimits.getLeftmostPointX() < center.getX() + radius
                && speed.getAngle() > 0)) {
            speed = Velocity.fromAngleAndSpeed(speed.getAngle() * (-1), speed.getSpeed());
        }
        //if the ball is going out of the frame on the y axis change it's direction.
        if ((ballLimits.getFrameHeight() + ballLimits.getLeftmostPointY() < center.getY() + radius
                && 0 < speed.getSpeed())
            || (ballLimits.getLeftmostPointY() > center.getY() - radius && 0 > speed.getSpeed())) {
            speed = Velocity.fromAngleAndSpeed(speed.getAngle(), speed.getSpeed() * (-1));
        }
        //advance a step
        center = this.getVelocity().applyToPoint(center);
    }
}


