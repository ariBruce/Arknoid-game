//Aryeh bruce 209313907
/**
 * @author Aryeh Bruce
 * date - 12.04.2021
 * <p>
 * This class contains all of the input checks for the main methouds of other classes and random inputs.
 * </p>
 */
public class InputChecks {
    /**
     * This method checks to make sure the center point of the circle is within its frame.
     * If not the method will correct the point and return the corrected one.
     *
     * @param start the current center point of the circle
     * @param upperLeftmostPoint upperLeftmostPoint  of the circles frame.
     * @param radius radius of the circle.
     * @param frameWidth width of the balls frame.
     * @param frameHeight width of the balls frame.
     * @return corrected center of the circle if needed correcting.
     */
    public static Point withinLimitsCheck(Point start, Point upperLeftmostPoint,
                                          double radius, int frameWidth, int frameHeight) {
        Point afterFix = start;
        while (afterFix.getX() + radius > frameWidth + upperLeftmostPoint.getX()) {
            afterFix = new Point(afterFix.getX() - 1, afterFix.getY());
        }
        while (afterFix.getX() - radius < upperLeftmostPoint.getX()) {
            afterFix = new Point(afterFix.getX() + 1, afterFix.getY());
        }
        while (afterFix.getY() - radius < upperLeftmostPoint.getY()) {
            afterFix = new Point(afterFix.getX(), afterFix.getY() + 1);
        }
        while (afterFix.getY() + radius > frameHeight + upperLeftmostPoint.getX()) {
            afterFix = new Point(afterFix.getX(), afterFix.getY() - 1);
        }
        return afterFix;
    }

    /**
     * This method checks to make sure the radius is not larger then frame.
     * If it is so the method will correct the radius and return the corrected one.
     *
     * @param radius current radius of the circle.
     * @param frameWidth width of the balls frame.
     * @param frameHeight width of the balls frame.
     * @return corrected radius of the circle if needed correcting.
     */
    public static double radiusCheck(double radius, int frameWidth, int frameHeight) {
        double radiusAfterFix = radius;
        while (radiusAfterFix > (double) frameWidth / 2) {
            radiusAfterFix = radiusAfterFix - 1;
        }
        while (radiusAfterFix > (double) frameHeight / 2) {
            radiusAfterFix = radiusAfterFix - 1;
        }
        return radiusAfterFix;
    }
    /**
     * This method checks to make sure the radius positive and visible.
     * If it is so the method will correct the radius and return the corrected one.
     *
     * @param radius current radius of the circle.
     * @return corrected radius of the circle if needed correcting.
     */
    public static double radiusSizeCheck(double radius) {
        if (radius < 0) {
            return radius * -1;
        } else if (radius < 10) {  //Any radius smaller then 10 will be increased to 10 in order to be visible.
            return 10;
        } else {
            return radius;
        }
    }
    /**
     * This method checks to make sure the members of the string received are int's.
     *
     * @param radius current string of radius's.
     * @return weather or not it is a number or not.
     */
    //check if int was recived
    public static boolean checkIfInt(String[] radius) {
        int[] radiusArray = new int[radius.length];
            for (int i = 0; i < radius.length; i++) {
                try {
                    radiusArray[i] = Integer.parseInt(radius[i]);
                } catch (NumberFormatException exception) {
                    return false;
                }
            }
            return true;
    }
}
