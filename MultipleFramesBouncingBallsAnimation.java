//Aryeh bruce 209313907
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
        GUI gui = new GUI("Ball box's", 700, 700); //set drawing platform
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
            for (int i = 0; i < radiusArray.length / 2; i++) {
                ballArray1[i].drawOn(screen); //draw the ball in the appropriate location
            }
            screen.setColor(Color.YELLOW); //set color for the board
            screen.fillRectangle(450, 450, 150, 150);
            for (int i = 0; i < radiusArray.length / 2; i++) {
                ballArray2[i].drawOn(screen); //draw the ball in the appropriate location
            }
            gui.show(screen); //present first screen
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
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
        //return array of balls from array of radius's based ov color
        if (color == Color.YELLOW) {
            Point edgePoint = new Point(450, 450);
            return MultipleBouncingBallsAnimation.createArrayOfBalls(arrayHalf,
                    150, 150, edgePoint);
        } else {
            Point edgePoint = new Point(50, 50);
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
        //pass string variables to double array
        int[] radiusArray = new int[args.length];
        for (int i = 0; i < args.length; i++) {
            radiusArray[i] = Integer.parseInt(args[i]);
        }
        //draw the bouncing balls on screen
        multipleFramesBouncingBallsAnimation(radiusArray);
    }
}
