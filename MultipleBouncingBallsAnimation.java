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


    public static void drawMultipleBallAnimation(int[] radiusArray) {
    GUI gui = new GUI("Ball box", 300, 300); //set drawing platform
    Sleeper sleeper = new Sleeper(); //set speed on screen variable
    DrawSurface d = gui.getDrawSurface(); //set the base in order to draw
    Point leftmostPointOfFrame = new Point(0, 0);
    //create array of balls from array of radius's
    Ball[] ballArray = createArrayOfBalls(radiusArray, d.getWidth(), d.getHeight(), leftmostPointOfFrame);
        while (true) { //infinite loop to continue the bounce of the ball's
        for (int i = 0; i < radiusArray.length; i++) {
            ballArray[i].moveOneStep(); //adjust the balls center
        }
        d = gui.getDrawSurface();
        for (int i = 0; i < radiusArray.length; i++) {
            ballArray[i].drawOn(d); //draw the ball in the appropriate location
        }
        gui.show(d); //present picture
        sleeper.sleepFor(50);  // wait for 50 milliseconds.
    }
    }
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
            int ballStartX = rand.nextInt(boardWidth) + (int) leftmostPoint.getX(); // get integer in correct range
            int ballStartY = rand.nextInt(boardHeight) + (int) leftmostPoint.getY(); // get integer in correct range
            float redBase = rand.nextFloat(); //create red base for color
            float greenBase = rand.nextFloat(); //create green base for color
            float blueBase = rand.nextFloat(); //create blue base for color
            Color randomColor = new Color(redBase, greenBase, blueBase); //mix all bases to receive a color
            //insert new ball into array of balls
            ballArray[i] = new Ball(ballStartX, ballStartY, radiusArray[i], randomColor);
            ballArray[i].setBallLimits(boardHeight, boardWidth, leftmostPoint);
        }
        setTheVelocity(radiusArray, ballArray);
        return ballArray;
    }

    public static void setTheVelocity(int[] radiusArray, Ball[] ballArray) {
        int largeBallSize = 50;
        double largeBallSpeed = 1;
        double largeBallAngle = 1;
        double speedBonusForSmallestBall = radiusArray.length + 5;
        Random rand = new Random(); // create a random-number generator
        for (int i = 0; i < radiusArray.length; i++) {
            if (radiusArray[i] >= largeBallSize) { //all large balls have the same velocity
                ballArray[i].setVelocity(largeBallAngle, largeBallSpeed);
            } else if (i != 0 && radiusArray[i] == radiusArray[i - 1]) { //paste same speed for balls of the same size
                ballArray[i].setVelocity(ballArray[i - 1].getVelocity());
            } else { //set speed so that it gets increasingly slower
                if (i != 0) { //make sure as the balls get bigger the speed gets smaller
                    ballArray[i].setVelocity((double) (radiusArray.length * 2) / i,
                            (double) (radiusArray.length * 2) / i);
                } else { //make sure smallest ball is fastest
                    ballArray[i].setVelocity((radiusArray.length) + speedBonusForSmallestBall,
                            (radiusArray.length) + speedBonusForSmallestBall);
                }
            }
        }
    }

    public static void main(String[] args) {
        //pass string variables to double array
        int[] radiusArray = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            radiusArray[i] = Integer.parseInt(args[i]);
        }
        //draw the bouncing balls on screen
        drawMultipleBallAnimation(radiusArray);
    }
}

