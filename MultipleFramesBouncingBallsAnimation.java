//Aryeh bruce 209313907
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;

public class MultipleFramesBouncingBallsAnimation {

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
