//Aryeh bruce
/**
 * @author Aryeh Bruce
 * date - 04.04.2021
 * <p>
 * This class represents the frame limitations of the ball, it holds its upper leftmost point
 * its highet and width, that way it can be used to linit movment.
 * </p>
 */
public class Frame {
    private double frameWidth; //the balls width movement limits
    private double frameHeight;  //the balls height movement limits
    private double leftmostPointX; //leftmost point of the frame
    private double leftmostPointY; //rightmost point of the frame

    /**
     * This method is one of two constructors for the frame.
     *
     * @param height represents the height of the frame.
     * @param width represents the width of the frame.
     * @param leftmostPointX represents x point of the upper corner of the frame.
     * @param leftmostPointY represents x point of the upper corner of the frame.
     */
    public Frame(double height, double width, double leftmostPointX, double leftmostPointY) {
        frameHeight = height;
        frameWidth = width;
        this.leftmostPointX = leftmostPointX;
        this.leftmostPointY =  leftmostPointY;
    }
    /**
     * This method is the second of two constructors for the frame.
     *
     * @param height represents the height of the frame.
     * @param width represents the width of the frame.
     * @param leftmostPoint represents x point of the upper corner of the frame.
     */
    public Frame(double height, double width, Point leftmostPoint) {
        frameHeight = height;
        frameWidth = width;
        this.leftmostPointX = leftmostPoint.getX();
        this.leftmostPointY = leftmostPoint.getY();
    }
    /**
     * This method retrieves the width of the frame.
     *
     * @return width of the frame.
     */
    public double getFrameWidth() {
        return frameWidth;
    }
    /**
     * This method retrieves the height of the frame.
     *
     * @return height of the frame.
     */
    public double getFrameHeight() {
        return frameHeight;
    }
    /**
     * This method retrieves the x value of the leftmost point of the frame.
     *
     * @return x value of the leftmost point of the frame.
     */
    public double getLeftmostPointX() {
        return leftmostPointX;
    }
    /**
     * This method retrieves the y value of the leftmost point of the frame.
     *
     * @return y value of the leftmost point of the frame.
     */
    public double getLeftmostPointY() {
        return leftmostPointY;
    }
}
