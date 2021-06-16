//Aryeh bruce 209313907
/**
 * @author Aryeh Bruce
 * date - 04.04.2021
 * <p>
 * This class has two variables as values, the addition to the x point and addition to the y point.
 * Velocity specifies the change in position on the `x` and the `y` axes.
 * This class applies the volocity to a given object
 * and gives the ability to create more of it through angle and speed.
 * </p>
 */
public class Velocity {

    private static final double SMALL_CHANGE_OF_POINT = 0.01;
    private double dx; // movement of the ball along x axis
    private double dy; // speed of the ball along y axis
    private double speed;

    /**
     * This method represents the constructor and will receive the velocity variables,
     * of movement on the axis's. these will be also used to determine the movement angle.
     *
     * @param dx is the representation of the movement on the x axis.
     * @param dy is the representation of the movement on the y axis.
     */
    public Velocity(double dx, double dy) {

        this.dx = dx;
        this.dy = dy;
        speed = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
    }

    /**
     * This method is the accessor to the necessary x axis movement of the velocity.
     *
     * @return the angle (on th x axis) of velocity.
     */
    public double getdx() {
        return dx;
    }

    /**
     * This method is the accessor to the necessary y axis movement of the velocity.
     *
     * @return the speed (on the y axis) of velocity.
     */
    public double getdy() {
        return dy;
    }

    /**
     * This method is the accessor to the speed of the velocity.
     *
     * @return the speed of velocity.
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * This method takes a point with position the (x,y) and return a new point,
     * according to the velocity.
     * it calculates the new needed position of the old point through simple addition.
     *
     * @param p the point that needs to be changed.
     * @return a new point in the form of the old one plus the velocity parameters.
     */
    public Point applyToPoint(Point p) { //fix
        return new Point(dx + p.getX(), dy + p.getY());
    }

    /**
     * This method takes a point with position the (x,y) and return a new point,
     * according to the velocity.
     * it calculates the new needed position of the old point through simple addition.
     * this version of the methud
     *
     * @param p the point that needs to be changed.
     * @return a new point in the form of the old one plus the velocity parameters.
     */
    public Point applyToOneAbovePoint(Point p) {
        if (dx >= 0 && dy >= 0) {
            return new Point(dx + p.getX() - SMALL_CHANGE_OF_POINT, dy + p.getY() - SMALL_CHANGE_OF_POINT);
        } else if (dx <= 0 && dy <= 0) {
            return new Point(dx + p.getX() + SMALL_CHANGE_OF_POINT, dy + p.getY() + SMALL_CHANGE_OF_POINT);
        } else if (dx >= 0 && dy <= 0) {
        return new Point(dx + p.getX() - SMALL_CHANGE_OF_POINT, dy + p.getY() + SMALL_CHANGE_OF_POINT);
        } else {
            return new Point(dx + p.getX() + SMALL_CHANGE_OF_POINT, dy + p.getY() - SMALL_CHANGE_OF_POINT);
        }
    }

    /**
     * This represents a second form of constructor for the velocity.
     * It receives an angle and speed of a vector and based on that it calculates the necessary movement
     *
     * @param angle represents the pace along the x axis at which velocity takes affect.
     * @param speed represents the pace along the y axis at which velocity takes affect
     * @return a new velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //make angle within calculating range
        while (angle > 360) {
            angle = angle - 360;
        }
        while (angle < -360) {
            angle = angle + 360;
        }
        double newDy = Math.sin(Math.toRadians(angle - 90)) * speed;
        double newDx = Math.cos(Math.toRadians(angle - 90)) * speed;

        return new Velocity(newDx, newDy);
    }

}
