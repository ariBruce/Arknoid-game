//Aryeh bruce
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Aryeh Bruce
 * date - 04.04.2021
 * <p>
 * This class draws a bouncing ball animation while using the
 * velocity, ball, line and point classes and drawing them on a GUI.
 * this class positions and creats the ball
 * based on the argument values recived from the main.
 * </p>
 */
public class BouncingBallAnimation {

    /**
     * This method draws the balls on the screen based on each one's
     * location and velocity.
     * There is a infinite loop in order to guarantee and continue the movement of the ball.
     *
     * @param start is the starting point of the ball.
     * @param dx is the original velocity.
     * @param dy is the radius of the circle that is received.
     */
    private static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("Ball box", 200, 200); //set drawing platform
        Sleeper sleeper = new Sleeper(); //set speed on screen variable
        DrawSurface d = gui.getDrawSurface(); //set the base in order to draw
        Point edgeOfScreen = new Point(0, 0); //edge point of limit
        Point correctPoint = InputChecks.withinLimitsCheck(start, edgeOfScreen, 30, d.getWidth(), d.getHeight());
        Ball ball = new Ball(correctPoint.getX(), correctPoint.getY(), 30, java.awt.Color.BLACK);
        ball.setBallLimits(d.getHeight(), d.getWidth(), 0, 0); //set ball limits
        ball.setVelocity(dx, dy); //set correct velocity
        while (true) { //infinite loop to continue the bounce of the ball
            ball.moveOneStep(); //adjust the balls center
            d = gui.getDrawSurface();
            ball.drawOn(d); //draw the ball in the appropriate location
            gui.show(d); //present picture
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
            if (gui.getKeyboardSensor().isPressed("space")) {
                return;
            }
        }
    }
    /**
     * This is the main method that converts the received strings into arguments
     * and sends off te correct parameters in order to draw the ball.
     * If args is invalid it will return invalid input.
     *
     * @param args original java input for ball location and velocity.
     */
    public static void main(String[] args) {
        if (args.length != 4) { //validate input
            System.out.println("Invalid input, incorrect amount of numbers");
        } else if (!InputChecks.checkIfInt(args)) { //check if all inputs are numbers
            System.out.println("Invalid input, input is not an int");
        }  else {
            //pass string variables to double array
            double[] passedArguments = new double[4];
            for (int i = 0; i < 4; i++) {
                passedArguments[i] = Double.parseDouble(args[i]);
            }
            //set new point and call animation method
            Point start = new Point(passedArguments[0], passedArguments[1]);
            drawAnimation(start, passedArguments[2], passedArguments[3]);
        }
    }
}
