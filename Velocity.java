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

    private double dx; // movement of the ball along x axis
    private double dy; // speed of the ball along y axis

    /**
     * This method represents the constructor and will receive the velocity variables,
     * of movement on the axis's.
     *
     * @param dx is the representation of the movement on the x axis.
     * @param dy is the representation of the movement on the y axis.
     */
    public Velocity(double dx, double dy) {

        this.dx = dx;
        this.dy = dy;
    }

    /**
     * This method is the accessor to the necessary x axis movement of the velocity.
     *
     * @return the angle (x axis) of velocity.
     */
    public double getdx() {
        return dx;
    }

    /**
     * This method is the accessor to the necessary y axis movement of the velocity.
     *
     * @return the speed (y axis) of velocity.
     */
    public double getdy() {
        return dy;
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
     * This represents a second form of constructor for the velocity.
     * It receives an angle and speed of a vector and based on that it calculates the necessary movement
     *
     * @param angle represents the pace along the x axis at which velocity takes affect.
     * @param speed represents the pace along the y axis at which velocity takes affect
     * @return a new point in the form of the old one plus the velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //make angle within calculating range
        while (angle > 360) {
            angle = angle - 360;
        }
        while (angle < -360) {
            angle = angle + 360;
        }
        double angleInRadians;
        //convert to radians in order to use sin and cos functions correctly
        if (angle >= 0 && 90 >= angle) {
            angleInRadians = Math.toRadians(90 - angle);
        } else if (angle >= 90 && 180 >= angle) {
            angleInRadians = Math.toRadians(angle - 90);
        } else if (angle >= 180 && 270 >= angle) {
            angleInRadians = Math.toRadians(90 - (angle - 180));
        } else if (angle >= 270 && 360 >= angle) {
            angleInRadians = Math.toRadians(angle - 270);
        } else if (angle <= 0 && -90 <= angle) {
            angleInRadians = Math.toRadians(90 + angle);
        } else if (angle <= -90 && -180 <= angle) {
            angleInRadians = Math.toRadians(angle + 90);
        } else if (angle <= -180 && -270 <= angle) {
            angleInRadians = Math.toRadians(90 + (angle + 180));
        } else {
            angleInRadians = Math.toRadians(angle + 270);
        }
        double newDy = Math.sin(angleInRadians) * speed;
        double newDx = Math.cos(angleInRadians) * speed;

        return new Velocity(newDx, newDy);
    }

}
