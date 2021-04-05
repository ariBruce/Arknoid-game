

public class Frame {
    private double frameWidth; //the balls width movement limits
    private double frameHeight;  //the balls height movement limits
    private double leftmostPointX; //leftmost point of the frame
    private double leftmostPointY; //rightmost point of the frame

    //constructor
    public Frame(double height, double width, double leftmostPoint, double rightmostPoint) {
        frameHeight = height;
        frameWidth = width;
        this.leftmostPointX = leftmostPoint;
        this.leftmostPointY = rightmostPoint;
    }
    public Frame(double height, double width, Point leftmostPoint) {
        frameHeight = height;
        frameWidth = width;
        this.leftmostPointX = leftmostPoint.getX();
        this.leftmostPointY = leftmostPoint.getY();
    }
    //getters
    public double getFrameWidth() {
        return frameWidth;
    }
    public double getFrameHeight() {
        return frameHeight;
    }
    public double getLeftmostPointX() {
        return leftmostPointX;
    }
    public double getLeftmostPointY() {
        return leftmostPointY;
    }
}
