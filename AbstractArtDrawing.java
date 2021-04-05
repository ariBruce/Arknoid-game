//Aryeh bruce 209313907
import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 * @author Aryeh Bruce
 * date - 01.04.2021
 * <p>
 * This class randomly places lines across a draw surface,
 * adds color and filles circels on the intersection points and middle points
 * </p>
 */
public class AbstractArtDrawing {
    /**
     * This method fills the intersections of all the lines with red circles
     * by going through all intersections and then painting circles where necessary.
     *
     * @param arrayOfLines receives the lines that need to be compared in order to find intersections.
     * @param page receives the surface that needs to be drawn on.
     * @param radius the correct radius of the circle.
     */
    public void fillIntersections(Line[] arrayOfLines, DrawSurface page, int radius) {

        page.setColor(Color.RED); //set color for the circles
        for (int i = 0; i < 10; i++) { //loop to test all 10 lines
            for (int j = 0; j < 10; j++) { //inner loop to compare it with all lines
                if (i == j) { //no need to compare to itself
                    continue;
                }
                if (arrayOfLines[i].isIntersecting(arrayOfLines[j])) {
                    //will only draw circle if intersection exists
                    Point intersection = arrayOfLines[i].intersectionPointCalculation(arrayOfLines[j]);
                    if (intersection != null) { //if only one intersection exists draw the circle
                        page.fillCircle((int) intersection.getX(), (int) intersection.getY(), radius);
                    }
                }
            }
        }
    }
    /**
     * This method creates random lines and blue circles at their centers and red at the intersections.
     * In addition to that it stores the lines in a array, and prints the image.
     */
    public void drawRandomLines() {
        Random rand = new Random(); // create a random-number generator
        // Create a window with the title "Random Circles Example"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random Circles Example", 400, 300);
        DrawSurface page = gui.getDrawSurface();
        Line[] arrayOfLines = new Line[10];
        int radius = 3; //correct circle radius
        //for loop for drawing ten lines
        for (int i = 0; i < 10; i++) {
            int x1 = rand.nextInt(400) + 1; // get integer in range 1-400
            int y1 = rand.nextInt(300) + 1; // get integer in range 1-300
            int x2 = rand.nextInt(400) + 1; // get integer in range 1-400
            int y2 = rand.nextInt(300) + 1; // get integer in range 1-300
            arrayOfLines[i] = new Line(x1, y1, x2, y2); //pass line into array
            page.setColor(Color.BLACK); //set color for the line
            page.drawLine(x1, y1, x2, y2);  //draw the line
            Point middle = arrayOfLines[i].middle(); //find the middle
            page.setColor(Color.BLUE); //set color for the circles
            page.fillCircle((int) middle.getX(), (int) middle.getY(), radius); //draw the circle
        }
        //method for finding and drawing circles on intersections
        fillIntersections(arrayOfLines, page, radius);
        //present picture
        gui.show(page);
    }

    /**
     * This method creates an abstract drawing environment and uses another method to draw random lines.
     * @param args original java input.
     */
    public static void main(String[] args) {
        AbstractArtDrawing randomLines = new AbstractArtDrawing();
        randomLines.drawRandomLines();
    }
}
