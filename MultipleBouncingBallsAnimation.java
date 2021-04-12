//Aryeh bruce 209313907
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.util.Random;
import java.awt.Color;

/**
 * @author Aryeh Bruce
 * date - 05.04.2021
 * <p>
 * This class draws a bouncing balls animation while using the
 * velocity class, an array of ball's, line and point classes and drawing them on a GUI.
 * This class creats the array of ball's applies to them difrent velocity's according to size,
 * and displays them on the screen.
 * all of this is based on the argument values recived from the main.
 * </p>
 */
public class MultipleBouncingBallsAnimation {

    private static final double LARGEBALLSIZE = 50; //From this size a ball is considered large
    private static final double LARGEBALLSPEED = 1; //large balls speed
    private static final double NUMBERFORSPEED = 12; //base number for setting speed.
    /**
     * This method draws the balls on the screen based on each one's
     * location and velocity and as it does so it holds the ball within the limits
     * of its rectangles.
     * There is a infinite loop in order to guarantee and continue the movement of the ball.
     *
     * @param radiusArray is the starting point of the ball.
     */
    public static void drawMultipleBallAnimation(int[] radiusArray) {
    GUI gui = new GUI("Ball box", 450, 450); //set drawing platform
    Sleeper sleeper = new Sleeper(); //set speed on screen variable
    DrawSurface d = gui.getDrawSurface(); //set the base in order to draw
    Point leftmostPointOfFrame = new Point(0, 0);
    for (int i = 0; i < radiusArray.length; i++) {
        radiusArray[i] = (int) InputChecks.radiusCheck(radiusArray[i], d.getWidth(), d.getHeight());
    }
    //create array of balls from array of radius's
    Ball[] ballArray = createArrayOfBalls(radiusArray, d.getWidth(), d.getHeight(), leftmostPointOfFrame);
        while (true) { //infinite loop to continue the bounce of the ball's
        for (int i = ballArray.length - 1; i >= 0; i--) {
            ballArray[i].moveOneStep(); //adjust the balls center
        }
        d = gui.getDrawSurface();
        for (int i = ballArray.length - 1; i >= 0; i--) {
            ballArray[i].drawOn(d); //draw the ball in the appropriate location
        }
        gui.show(d); //present picture
        sleeper.sleepFor(50);  // wait for 50 milliseconds.
            if (gui.getKeyboardSensor().isPressed("space")) {
                return;
            }
    }
    }
    /**
     * This method sorts the array of radius's by size
     * and then in order gives each ball a color, center frame and velocity.
     *
     * @param radiusArray is the starting point of the ball.
     * @param boardWidth is the width limit f0r the movement of the ball.
     * @param boardHeight is the height limit f0r the movement of the ball.
     * @param leftmostPoint is the upper left point of the balls limits.
     * @return will return the new array of balls.
     */
    public static Ball[] createArrayOfBalls(int[] radiusArray, int boardWidth,
                                            int boardHeight, Point leftmostPoint) {
        Random rand = new Random(); // create a random-number generator
        Ball[] ballArray = new Ball[radiusArray.length]; //create array of balls
        // bubble sort radiusArray
        for (int i = 0; i < radiusArray.length - 1; i++) {
            for (int j = 0; j < radiusArray.length - i - 1; j++) {
                if (radiusArray[j] > radiusArray[j + 1]) {
                    // swap radiusArray[j+1] and radiusArray[j]
                    int temp = radiusArray[j];
                    radiusArray[j] = radiusArray[j + 1];
                    radiusArray[j + 1] = temp;
                }
            }
        }
        for (int i = 0; i < radiusArray.length; i++) { //fill ball array
            int ballStartX = rand.nextInt(boardWidth); // get integer in correct range
            int ballStartY = rand.nextInt(boardHeight); // get integer in correct range
            float redBase = rand.nextFloat(); //create red base for color
            float greenBase = rand.nextFloat(); //create green base for color
            float blueBase = rand.nextFloat(); //create blue base for color
            Color randomColor = new Color(redBase, greenBase, blueBase); //mix all bases to receive a color
            //insert new ball into array of balls
            Point centerOfBall = new Point(ballStartX, ballStartY);
            centerOfBall = InputChecks.withinLimitsCheck(centerOfBall, leftmostPoint,
                    radiusArray[i], boardWidth, boardHeight);
            if (i != 0) { //change starting location of the balls in order to prevent constant overlap
                if (centerOfBall.equals(ballArray[i - 1].getCenter())) {
                    centerOfBall = new Point(centerOfBall.getX() + boardWidth, centerOfBall.getY() + boardWidth);
                    centerOfBall = InputChecks.withinLimitsCheck(centerOfBall, leftmostPoint,
                            radiusArray[i], boardWidth, boardHeight);
                    if (centerOfBall.equals(ballArray[i - 1].getCenter())) { //if still overlapping
                        ballArray[i - 1] = new Ball(ballArray[i - 1].getCenter(),
                                ballArray[i - 1].getSize() - 1, ballArray[i - 1].getColor());
                        ballArray[i - 1].setBallLimits(boardHeight, boardWidth, leftmostPoint);
                    }
                }
            }
            ballArray[i] = new Ball(centerOfBall, radiusArray[i], randomColor);
            ballArray[i].setBallLimits(boardHeight, boardWidth, leftmostPoint);
        }
        setTheVelocity(radiusArray, ballArray);
        return ballArray;
    }

    /**
     * This method gives each ball a velocity by giving it a random angle,
     * it gives each ball speed according to the size of the ball.
     *
     * @param radiusArray is the starting point of the ball.
     * @param ballArray is the width limit for the movement of the ball.
     */
    public static void setTheVelocity(int[] radiusArray, Ball[] ballArray) {
        Random rand = new Random(); // create a random-number generator
        double ballAngle;
        double speedBonusForSmallestBall = 5; //in order to make sure smallest ball is fastest
        for (int i = 0; i < radiusArray.length; i++) {
            if (radiusArray[i] >= LARGEBALLSIZE) { //all large balls have the same velocity
                ballAngle = rand.nextInt(360); //any angle smaller then 360
                ballArray[i].setVelocity(Velocity.fromAngleAndSpeed(ballAngle, LARGEBALLSPEED));
            } else if (i != 0 && radiusArray[i] == radiusArray[i - 1]) { //paste same speed for balls of the same size
                //the same size will be received here and there is no more need to check because the array is in order
                ballArray[i].setVelocity(ballArray[i - 1].getVelocity().getdx(),
                        ballArray[i - 1].getVelocity().getdy());
            } else if (i != 0) { //set speed so that ist gets increasingly slower
                    //make sure as the balls get bigger the speed gets smaller
                    ballAngle = rand.nextInt(360); //any angle smaller then 360
                    ballArray[i].setVelocity(Velocity.fromAngleAndSpeed(ballAngle, NUMBERFORSPEED / (1 + 0.1 * i)));
                } else { //make sure smallest ball is fastest
                    ballAngle = rand.nextInt(360); //any angle smaller then 360
                    ballArray[i].setVelocity(Velocity.fromAngleAndSpeed(ballAngle,
                            NUMBERFORSPEED + speedBonusForSmallestBall));
                }
            }
        }

    /**
     * This method transfers all string form radius's to doubles and sends them on to be drawn as circles.
     *
     * @param args parameters received from command line, in this case array of radius's
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Invalid input, must input a valid number of radius's");
        } else if (!InputChecks.checkIfInt(args)) {
        System.out.println("Invalid input, input is not an int");
    } else {
            //pass string variables to double array
            int[] radiusArray = new int[args.length];
            for (int i = 0; i < args.length; i++) {
                radiusArray[i] = Integer.parseInt(args[i]);
                radiusArray[i] = (int) InputChecks.radiusSizeCheck(radiusArray[i]);
            }
            //draw the bouncing balls on screen
            drawMultipleBallAnimation(radiusArray);
        }
    }
}

