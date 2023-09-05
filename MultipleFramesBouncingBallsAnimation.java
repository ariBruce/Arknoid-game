//Aryeh bruce
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

/**
 * @author Aryeh Bruce
 * date - 05.04.2021
 * <p>
 * This class draws a bouncing balls animation while using the
 * velocity class, an array of ball's, line and point classes and drawing them on a GUI.
 * This class splits the array of balls and sets thier limit to a grey or yellow box.
 * This class displays the balls on the screen within thier box while using methods from
 * MultipleBouncingBallsAnimation class.
 * All of this is based on the argument values recived from the main.
 * </p>
 */
public class MultipleFramesBouncingBallsAnimation {

    /**
     * This method draws the balls on the screen based on each one's
     * location and velocity after placing them into a array of balls and drawing their frames.
     * There is a infinite loop in order to guarantee and continue the movement of the ball.
     *
     * @param radiusArray is the radius's of all the ball's in the array.
     */
    public static void multipleFramesBouncingBallsAnimation(int[] radiusArray) {
        GUI gui = new GUI("Ball box's", 650, 650); //set drawing platform
        Sleeper sleeper = new Sleeper(); //set speed on screen variable
        DrawSurface screen = gui.getDrawSurface(); //set the base in order to draw
        Ball[] ballArray1 = splitArray(radiusArray, 0, Color.GRAY);
        Ball[] ballArray2 = splitArray(radiusArray, radiusArray.length / 2, Color.YELLOW);
        while (true) { //infinite loop to continue the bounce of the ball's in rectangle's
            screen = gui.getDrawSurface(); //set the base in order to draw
            for (int i = 0; i < radiusArray.length / 2; i++) {
                ballArray1[i].moveOneStep(); //adjust the balls center
                ballArray2[i].moveOneStep(); //adjust the balls center
            }
            screen.setColor(Color.GRAY); //set color for the board
            screen.fillRectangle(50, 50, 450, 450);
            for (int i = ballArray1.length - 1; i >= 0; i--) {
                ballArray1[i].drawOn(screen); //draw the ball in the appropriate location
            }
            screen.setColor(Color.YELLOW); //set color for the board
            screen.fillRectangle(450, 450, 150, 150);
            for (int i = ballArray2.length - 1; i >= 0; i--) {
               ballArray2[i].drawOn(screen); //draw the ball in the appropriate location
            }
            gui.show(screen); //present first screen
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
            if (gui.getKeyboardSensor().isPressed("space")) {
                return;
            }
        }
    }
    /**
     * This method takes half of the original radius array and sets each half's limits by its screen color
     * and uses MultipleBouncingBallsAnimation class to create array of balls.
     *
     * @param radiusArray is the radius's of all the ball's in the array.
     * @param locationInArray is the width limit of each ball.
     * @param color is the height limit of each ball.
     * @return array of balls based on radiusArray and the frame's color.
     */
    public static Ball[] splitArray(int[] radiusArray, int locationInArray, java.awt.Color color) {
        int[] arrayHalf = new int[radiusArray.length / 2];
        for (int i = 0; i < radiusArray.length / 2; i++) { //copy fist half of array into new array
            arrayHalf[i] = radiusArray[locationInArray + i];
        }
        //return array of balls from array of radius's based of color
        if (color == Color.YELLOW) {
            Point edgePoint = new Point(450, 450);
            for (int i = 0; i < arrayHalf.length; i++) {
                arrayHalf[i] = (int) InputChecks.radiusCheck(arrayHalf[i], 150, 150);
                if (i != 0 && arrayHalf[i] == arrayHalf[i - 1] && arrayHalf[i] == 75) {
                    arrayHalf[i - 1] = arrayHalf[i - 1] - 5; //make sure no max sized balls block each other
                }
            }
            return MultipleBouncingBallsAnimation.createArrayOfBalls(arrayHalf,
                    150, 150, edgePoint);
        } else {
            Point edgePoint = new Point(50, 50);
            for (int i = 0; i < arrayHalf.length; i++) {
                arrayHalf[i] = (int) InputChecks.radiusCheck(arrayHalf[i], 450, 450);
                if (i != 0 && arrayHalf[i] == arrayHalf[i - 1] && arrayHalf[i] == 225) {
                    arrayHalf[i - 1] = arrayHalf[i - 1] - 5; //make sure no max sized balls block each other
                }
            }
            return MultipleBouncingBallsAnimation.createArrayOfBalls(arrayHalf,
                    450, 450, edgePoint);
        }
    }
    /**
     * This method receives the radius's of all the ball's and passes
     * them on to be drawn within their respective rectangle.
     *
     * @param args is the radius's of all the ball's in the array.
     */
    public static void main(String[] args) {
        if (args.length == 0 || args.length % 2 != 0) {
            System.out.println("must input a valid number of radius's");
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
            multipleFramesBouncingBallsAnimation(radiusArray);
        }
    }
}
