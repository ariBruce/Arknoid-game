//Aryeh bruce 209313907
/**
 * @author Aryeh Bruce
 * date - 30.03.2021
 * <p>
 * This class has an x and a y value, and can measure the distance to other points,
 * if it is equal to another point and can retun its own values.
 * This class represents a point.
 * </p>
 */
public class Point {
    private double x;  //x coordinate for point
    private double y;  //y coordinate for point

    /**
     * This method is the constructor and receives the values of this point.
     *
     * @param x receives the x coordinate for point.
     * @param y receives the y coordinate for point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * This method returns the distance of between this point and the other point.
     *
     * @param other receives the point the that the distance needs to be calculated from.
     * @return the correct distance from the other point.
     */
    public double distance(Point other) {
        //Receive the x and y values of other point
        double x2 = other.getX();
        double y2 = other.getY();
        double sumToSqrt = ((x - x2) * (x - x2)) + ((y - y2) * (y - y2)); //sqrt sum for distance
        return Math.sqrt(sumToSqrt); //return distance from other point
    }

    /**
     * This method returns a boolean value based on weather the point it is compared to is in the same location.
     *
     * @param other receives the point that must be compared to.
     * @return will return a boolean value based on if the points are in the same location .
     */
    public boolean equals(Point other) {
        //Receive the x and y values of other point
        double x2 = other.getX();
        double y2 = other.getY();
        //return true if both statements are correct
        return x == x2 && y == y2;
    }

    /**
     * This method returns the value of this points x coordinate.
     *
     * @return the value of this points x coordinate.
     */
    public double getX() {
        return x;
    }
    /**
     * This method returns the value of this points y coordinate.
     *
     * @return the value of this points y coordinate.
     */
    public double getY() {
        return y;
    }
}
