//Aryeh bruce 209313907
import java.util.ArrayList;
import java.util.List;

/**
 * @author Aryeh Bruce
 * date - 22.04.2021
 * <p>
 * This class represents a rectangle, it ismade of four lines to represent its limits.
 * the class has the appropriate getters and a methoud in order to get all of its proproties,
 * </p>
 */
public class Rectangle {

    private Line upperLine;
    private Line lowerLine;
    private Line rightLine;
    private Line leftLine;

    /**
     * This method represents the rectangle constructor.
     *
     * @param upperLeft the leftmost point of the rectangle.
     * @param width rectangle width.
     * @param height rectangle height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        //calculate all lines of the rectangle
        Point upperRight = new Point(width + upperLeft.getX(), upperLeft.getY());
        upperLine = new Line(upperLeft, upperRight);
        lowerLine = new Line(upperLeft.getX(), height + upperLeft.getY(),
                upperRight.getX(), height + upperRight.getY());
        rightLine = new Line(upperRight, lowerLine.end());
        leftLine = new Line(upperLeft, lowerLine.start());
    }

    /**
     * This method will return a (possibly empty) List of intersection points
     * with the specified line ant the current rectangle.
     *
     * @param line line to compare rectangle to.
     * @return list of point which may be empty.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        //compare line to all sides of the rectangle and add to the list of points
        List<Point> listOfPoints = new ArrayList<>();
        if (upperLine.intersectionWith(line) != null) {
            listOfPoints.add(upperLine.intersectionWith(line));
        }
        if (lowerLine.intersectionWith(line) != null) {
            listOfPoints.add(lowerLine.intersectionWith(line));
        }
        if (rightLine.intersectionWith(line) != null) {
            listOfPoints.add(rightLine.intersectionWith(line));
        }
        if (leftLine.intersectionWith(line) != null) {
            listOfPoints.add(leftLine.intersectionWith(line));
        }
        return listOfPoints;
    }

    /**
     * This method will get the upper line of the rectangle.
     *
     * @return upper line of the rectangle.
     */
    public Line getUpperLine() {
        return upperLine;
    }
    /**
     * This method will get the lower line of the rectangle.
     *
     * @return lower line of the rectangle.
     */
    public Line getLowerLine() {
        return lowerLine;
    }
    /**
     * This method will get the right line of the rectangle.
     *
     * @return right line of the rectangle.
     */
    public Line getRightLine() {
        return rightLine;
    }
    /**
     * This method will get the right line of the rectangle.
     *
     * @return left line of the rectangle.
     */
    public Line getLeftLine() {
        return leftLine;
    }
    /**
     * This method will get the width of the rectangle.
     *
     * @return width line of the rectangle.
     */
    public double getWidth() {
        return rightLine.start().getX() - leftLine.start().getX();
    }
    /**
     * This method will get the height of the rectangle.
     *
     * @return height line of the rectangle.
     */
    public double getHeight() {
        return lowerLine.start().getY() - upperLine.start().getY();
    }

    /**
     * This method will get the upper left point of the rectangle.
     *
     * @return upper left point of the rectangle.
     */
    public Point getUpperLeft() {
        return upperLine.start();
    }
   }
