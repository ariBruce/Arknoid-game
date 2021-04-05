//Aryeh bruce 209313907
/**
 * @author Aryeh Bruce
 * date - 01.04.2021
 * <p>
 * This class has two points as values,can measure its own distance, can mesure its middle point,
 * can be compared with other lines to see if there is a intersecting point,
 * and if the intersecting point is singular it can be caculated.
 * this class represent a line.
 * </p>
 */
public class Line {
    //Both points that the line is located in between
    private Point start;
    private Point end;

    /**
     * This constructor sets the start and end point of the line while receiving points.
     *
     * @param start receives start point of the line.
     * @param end receives pnd point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * This constructor sets the start and end point of the line while receiving 4 coordinates.
     *
     * @param x1 receives x coordinate of start point of the line.
     * @param y1 receives y coordinate of start point of the line.
     * @param x2 receives x coordinate of end point of the line.
     * @param y2 receives y coordinate of end point of the line.
     */
    public Line(double x1, double y1, double x2, double y2) {
       start = new Point(x1, y1);
       end = new Point(x2, y2);
    }

    /**
     * This method returns the length of the line, using the points distance function.
     *
     * @return the line's length.
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * This method returns the angle of the line.
     *
     * @return the line's angle.
     */
    public double lineAngle() {
        if (start.getX() - end.getX() == 0) {
            return 0;
        } else {
            return (start.getY() - end.getY()) / (start.getX() - end.getX());
        }
    }

    /**
     * This method returns the middle point of the line, using the correct formula.
     *
     * @return the line's middle point.
     */
    public Point middle() {
        double midX = (start.getX() + end.getX()) / 2;
        double midY = (start.getY() + end.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * This method returns the start point of the line.
     *
     * @return the line's start point.
     */
    public Point start() {
        return start;
    }

    /**
     * This method returns the end point of the line.
     *
     * @return the line's end point.
     */
    public Point end() {
        return end;
    }
    /**
     * This method calculates if the two lines intersect based on their angle.
     * First it will check if the lines are equal adn if they are they will intersect.
     * Afterward it will check weather the lines are on top of each other, or the edges collide,
     * and that is for whenever the angles are the same.
     * Next it will test if the line is a dot on the other line.
     * If the case is none of the above it will compare the above it will test the potential intersection
     * and check if it is within range of the line
     *
     * @param other is the line that is checked to see if intersection point exists.
     * @return true or false based on weather the lines intersect or not.
     */
        public boolean isIntersecting(Line other) {
            //if both lines are the same they intersect
            if (equals(other)) {
                return true;
            }
            if (lineAngle() == other.lineAngle()) {
                //in case one line is lying on the other
                  if ((Math.max(start.getX(), end.getX()) >= Math.min(other.start.getX(), other.end.getX()))
                          && (Math.min(start.getX(), end.getX()) <= Math.max(other.start.getX(), other.end.getX()))
                          && (Math.max(start.getY(), end.getY()) >= Math.min(other.start.getY(), other.end.getY()))
                          && (Math.min(start.getY(), end.getY()) <= Math.max(other.start.getY(), other.end.getY()))) {
                      return true;
                  }
                //find n in y = m*x + n equation for this line
                double n = start.getY() - lineAngle() * start.getX();
                //check if line edges are on one of the lines do this by seeing if line equation are the same
                //and if so weather or not
                return ((other.start.getY() == other.start.getX() * lineAngle() + n)
                        && (Math.max(start.getX(), end.getX()) >= Math.min(other.start.getX(), other.end.getX()))
                        && (Math.min(start.getX(), end.getX()) <= Math.max(other.start.getX(), other.end.getX())));
            } else {
                //potential Point of intersect is calculated if point is within range of the line
                // then the segments must intersect, return true or false based on that check.
                double n;
                if (end.equals(start)) {
                    //if a line is a dot its the only potential point
                    //therefore see if it is within the function
                    n = other.start.getY() - other.lineAngle() * other.start.getX();
                    if (end.getY() == other.lineAngle() * end.getX() + n) {
                        return (!(end.getX() > Math.max(start.getX(), end.getX()))
                                && !(end.getX() < Math.min(start.getX(), end.getX()))
                                && !(end.getY() > Math.max(other.start.getY(), other.end.getY()))
                                && !(end.getY() < Math.min(other.start.getY(), other.end.getY())));
                    } else {
                        return false;
                    }
                } else if (other.end.equals(other.start)) {
                    //if a line is a dot its the only potential point
                    n = start.getY() - lineAngle() * start.getX();
                    if (other.end.getY() == lineAngle() * other.end.getX() + n) {
                        return (!(other.end.getX() > Math.max(start.getX(), end.getX()))
                                && !(other.end.getX() < Math.min(start.getX(), end.getX()))
                                && !(other.end.getY() > Math.max(other.start.getY(), other.end.getY()))
                                && !(other.end.getY() < Math.min(other.start.getY(), other.end.getY())));
                    } else {
                        return false;
                    }
                } else {
                    Point potentialPoint = potentialIntersectionPointCalculation(other);
                    //check if potential intersection point is within lines range
                    return (!(potentialPoint.getX() > Math.max(start.getX(), end.getX()))
                            && !(potentialPoint.getX() < Math.min(start.getX(), end.getX()))
                            && !(potentialPoint.getX() > Math.max(other.start.getX(), other.end.getX()))
                            && !(potentialPoint.getX() < Math.min(other.start.getX(), other.end.getX())));
                }
            }
        }
    /**
     * This method returns the intersecting point of this line with another.
     * It does so by checking weather the points intersect and if they do -
     * returns the point by sending the line to another calculation method.
     * If there is no intersection null is returned.
     *
     * @param other the given line to check intersection point with.
     * @return the line's intersection point.
     */
    public Point intersectionWith(Line other) {
        if (!isIntersecting(other)) {
            return null;
        } else {
            return intersectionPointCalculation(other);
        }
    }

    /**
     * This method returns the intersecting point of this line with another.
     * It does so by checking 4 different cases and each one has its own method-
     * Case 1: if one line is a dot and it is resting on a line, here the dot is returned.
     * Case 2: if the lines intersect but at more then one point, here null is returned.
     * Case 3: if the angle is the same and just the edges collide, will return correct edge.
     * Case 4: for any other case normal intersect calculation can be used, will return intersection point.
     *
     * @param other the given line to check intersection point with.
     * @return the line's intersection point.
     */
    public Point intersectionPointCalculation(Line other) {
        if (length() == 0 || other.length() == 0) { //check if its a dot on a line
            if (dotLyingOnLine(other) != null) {
                return dotLyingOnLine(other);
            }
        }
        if (moreThenOneIntersect(other)) { //if there are several intersection points
            return null;
        }
        if (lineAngle() - other.lineAngle() == 0) { //if the angle is the same and edges collide return edges
            if (sameAngleEdgeCheck(other) != null) {
                return sameAngleEdgeCheck(other);
            }
        }
       return potentialIntersectionPointCalculation(other); //otherwise the potential point is the intersection
    }
    /**
     * This method is used when we know the lines intersect
     * we know the angle is the same and we know its at one point.
     * Therefore here all that is done is a comparison of the edge points in order to find the intersection.
     *
     * @param other the given line to check intersection point with.
     * @return the line's intersection point.
     */
    public Point sameAngleEdgeCheck(Line other) { //compare all edges to see if they collide
        if (other.start.equals(start)) {
            return start;
        } else if (other.end.equals(end)) {
            return end;
        } else if (other.start.equals(end)) {
            return end;
        } else if (other.end.equals(start)) {
            return start;
        }
        return null;
    }
    /**
     * This method is used in order to see if the dot is within range of the line.
     * We only use this method after we know the line and dot intersect.
     * If the dot is not within range of the line the method will return null.
     *
     * @param other the given line to check intersection point with.
     * @return the line's intersection point.
     */
    public Point dotLyingOnLine(Line other) {
        if (equals(other)) { //if both lines are the same dot
            return start;
        } else if (Math.min(other.start().getY(), other.end.getY()) <= start.getY()
                && Math.max(other.start().getY(), other.end.getY()) >= start.getY()) { //if a dot is on the line
            return start;
        } else if (other.length() == 0) {
            if (Math.min(start().getY(), end.getY()) <= other.start.getY()
                    && Math.max(start().getY(), end.getY()) >= other.start.getY()) { //if a dot is on the line
                return other.start;
            }
        }
        return null;
    }
    /**
     * This method is used in order to see if the two lines intersect at more then one point.
     * We only use this method after we know the lines intersect at the same angle.
     * If the lines edges are within range of each other and not equal the method will return true
     * otherwise it is false that the lines intersect at more then one point.
     *
     * @param other the given line to check if there are many intersection points with.
     * @return boolean value based on if the lines intersect at many points or not.
     */
    public boolean moreThenOneIntersect(Line other) {
        if (Math.max(other.start.getX(), other.end.getX()) > 0
            || Math.max(start.getX(), end.getX()) > 0
            || Math.max(other.start.getY(), other.end.getY()) > 0
            || Math.min(start().getY(), end.getY()) > 0) { //if there is a positive side to a line
            if (Math.min(start().getX(), end.getX()) > Math.max(other.start.getX(), other.end.getX())
                    && lineAngle() == other.lineAngle()
                    && lineAngle() != 0) { //if lines intersect at more then one point
                //Will not work on y axis if angle is zero
                return true;
            } else if (Math.min(start().getY(), end.getY()) < Math.max(other.start.getY(), other.end.getY())
                    && Math.max(start().getY(), end.getY()) > Math.min(other.start.getY(), other.end.getY())
                    && lineAngle() == other.lineAngle()) { //if lines intersect at more then one point
                //will check the y axis
                return true;
            } else if (Math.min(other.start().getX(), other.end.getX()) > Math.max(start.getX(), end.getX())
                    && lineAngle() == other.lineAngle()
                    && lineAngle() != 0) { //if lines intersect at more then one point
                //Will not work on y axis if angle is zero
                return true;
            } else if (Math.min(other.start().getY(), other.end.getY()) > Math.max(start.getY(), end.getY())
                    && Math.max(other.start().getY(), other.end.getY()) > Math.min(start.getY(), end.getY())
                    && lineAngle() == other.lineAngle()) { //if lines intersect at more then one point
                //will check the y axis
                return true;
            }
        } else { //if all lines are on the negative side
            if (Math.min(start().getX(), end.getX()) < Math.max(other.start.getX(), other.end.getX())
                    && lineAngle() == other.lineAngle()
                    && lineAngle() != 0) { //if lines intersect at more then one point
                //Will not work on y axis if angle is zero
                return true;
            } else if (Math.min(start().getY(), end.getY()) < Math.max(other.start.getY(), other.end.getY())
                    && lineAngle() == other.lineAngle()) { //if lines intersect at more then one point
                //will check the y axis
                return true;
            } else if (Math.min(other.start().getX(), other.end.getX()) < Math.max(start.getX(), end.getX())
                    && lineAngle() == other.lineAngle()
                    && lineAngle() != 0) { //if lines intersect at more then one point
                //Will not work on y axis if angle is zero
                return true;
            } else if (Math.min(other.start().getY(), other.end.getY()) < Math.max(start.getY(), end.getY())
                    && lineAngle() == other.lineAngle()) { //if lines intersect at more then one point
                //will check the y axis
                return true;
            }
        }
        return false;
    }
    /**
     * This method is used in order calculate potential or definite intersection point.
     * It will return the potential or definite intersection point.
     * This method calculates based on the linear line equation.
     * This method will not be reached if a number would be divided by zero.
     *
     * @param other the given line to check intersection point with.
     * @return the potential or definite point of intersection.
     */
    public Point potentialIntersectionPointCalculation(Line other) {
         double n;
         double n2;
         n = start.getY() - lineAngle() * start.getX();
         n2 = other.start.getY() - other.lineAngle() * other.start.getX();
         if (other.lineAngle() == 0 || lineAngle() == 0) {  //if angle is zero either y or x are always the same
            return potentialPointForZeroAngle(other, n, n2);
         }
         double intersectX;
         double intersectY;
         //compare the lines equations to find x and use x to find y
         intersectX = (n - n2) / (other.lineAngle() - lineAngle());
         intersectY = intersectX * lineAngle() + n;
         return new Point(intersectX, intersectY);
    }
    /**
     * This method is used in order to receive correct calculations -
     * from the potentialIntersectionPointCalculation method.
     * This method is used in any case where an angle of one of the lines is zero.
     * It will receive the line to be compared to and the n of both lines linear functions (y = m*x + n).
     *
     * @param other the given line to check intersection point with.
     * @param n the current line's n from the linear function (y = m*x + n).
     * @param n2 the given line's n from the linear function (y = m*x + n).
     * @return the potential or definite point of intersection.
     */
    public Point potentialPointForZeroAngle(Line other, double n, double n2) {
        //compare all edges to see if they collide
        double intersectX;
        double intersectY;
        if (other.start.getX() == other.end.getX()) {
            intersectX = other.start.getX();
            intersectY = intersectX * lineAngle() + n;
            return new Point(intersectX, intersectY);
        } else if (other.start.getY() == other.end.getY()) {
            //same check for y
            intersectY = other.start.getY();
            intersectX = (n - intersectY) / lineAngle();
            return new Point(intersectX, intersectY);
        } else if (start.getY() == end.getY()) {
            //same check for y
            intersectY = start.getY();
            intersectX = (n2 - intersectY) / other.lineAngle();
            return new Point(intersectX, intersectY);
        } else {
            intersectX = start.getX();
            intersectY = intersectX * other.lineAngle() + n2;
            return new Point(intersectX, intersectY);
        }
    }
    /**
     * This method returns true if the lines are equal and false in any other case.
     *
     * @param other is the line that needs to be compared to the current one.
     * @return weather lines are equal or not.
     */
        public boolean equals(Line other) {
        return (other.start.equals(start)
                && other.end.equals(end))
                || (other.start.equals(end)
                && other.end.equals(start));
        }
    }

