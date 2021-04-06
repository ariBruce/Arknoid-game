//Aryeh bruce 209313907
/**
 * @author Aryeh Bruce
 * date - 04.04.2021
 * <p>
 * This class has two variables as values, speed and angle.
 * Velocity specifies the change in position on the `x` and the `y` axes.
 * This class applies the volocity to a given object
 * and gives the ability to create more of it.
 * </p>
 */
public class Velocity {

    private double angle; // movement angle of the ball
    private double speed; // speed of the angle

    /**
     * This method represents the constructor and will receive the velocity variables,
     * and while doing so put them within range of 360.
     *
     * @param angle is the representation of the object on the x axis.
     * @param speed is the representation of the object on the y axis.
     */
    public Velocity(double angle, double speed) {
        //make angle within calculating range
        while (angle > 360) {
            angle = angle - 360;
        }
        while (angle < -360) {
            angle = angle + 360;
        }
        this.angle = angle;
        this.speed = speed;
    }

    /**
     * This method is the accessor to the angle of the velocity.
     *
     * @return the angle (x axis) of velocity.
     */
    public double getAngle() {
        return angle;
    }

    /**
     * This method is the accessor to the speed of the velocity.
     *
     * @return the speed (y axis) of velocity.
     */
    public double getSpeed() {
        return speed;
    }

    /**
     * This method takes a point with position the (x,y) and return a new point,
     * according to the velocity.
     * it calculates the vector of movement based on angle and length of vector and return's new point.
     *
     * @param p the point that needs to be changed.
     * @return a new point in the form of the old one plus the velocity.
     */
    public Point applyToPoint(Point p) { //fix
        double angleVectorX;
        double angleVectorY;
        if (((angle < 180 && angle > 0) //if ball angle is pointing upwards
            || (angle > -180 && angle < -360))) {
            angleVectorY = speed * Math.sin(90 - angle) * (-1);
            angleVectorX = speed * Math.cos(90 - angle);
        } else if ((angle > 180 && angle < 360) //if ball angle is pointing downwards
                || (angle > -180 && angle < 0)) {
                angleVectorY = speed * Math.sin(90 + angle);
                angleVectorX = speed * Math.cos(90 + angle);
        } else { //if ball is on the axis's
            angleVectorY = speed;
            angleVectorX = speed;
        }
        return new Point(angleVectorX + p.getX(), angleVectorY + p.getY());
    }

    /**
     * This method takes a point with position the (x,y) and return a new point,
     * according to the velocity.
     *
     * @param angle represents the pace along the x axis at which velocity takes affect.
     * @param speed represents the pace along the y axis at which velocity takes affect
     * @return a new point in the form of the old one plus the velocity.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        return new Velocity(angle, speed);
    }

}
