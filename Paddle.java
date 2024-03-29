//Aryeh bruce
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import java.awt.Color;

/**
 * @author Aryeh Bruce
 * date - 25.04.2021
 * <p>
 * This class represents a paddle, it has a rectangle keyboard sensor and number constants.
 * the class has the appropriate getters and a methoud in order to draw the paddle,
 * move it and sets notify a hit.
 * </p>
 */
public class Paddle implements Sprite, Collidable {
    private static final int SCREEN_LIMITS_RIGHT = 780;
    private static final int SCREEN_LIMITS_LEFT = 20;
    private static final int PARTS_OF_THE_PADDLE = 5;
    private int speed;
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddleShape;

    /**
     * This method represents the constructor for the paddle.
     *
     * @param keyboard the holder that contains the keyboard sensors.
     * @param paddleShape the side's of the rectangle.
     */
    public Paddle(Rectangle paddleShape, int speed, biuoop.KeyboardSensor keyboard) {
        this.keyboard = keyboard;
        this.paddleShape = paddleShape;
        this.speed = speed;
    }
    /**
     * This method represents the movement ability of the paddle to the left and will allow it to move properly.
     */
    public void moveLeft() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            Point newUpperLeft = new Point(paddleShape.getUpperLeft().getX()
                    - speed, paddleShape.getUpperLeft().getY());
            if (newUpperLeft.getX() >= SCREEN_LIMITS_LEFT) {
                this.paddleShape = new Rectangle(newUpperLeft, paddleShape.getWidth(), paddleShape.getHeight());
            }
        }
    }
    /**
     * This method represents the movement ability of the paddle to the right and will allow it to move properly.
     */
    public void moveRight() {
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            Point newUpperLeft = new Point(paddleShape.getUpperLeft().getX()
                    + speed, paddleShape.getUpperLeft().getY());
            if (newUpperLeft.getX() + paddleShape.getWidth() <= SCREEN_LIMITS_RIGHT) {
                this.paddleShape = new Rectangle(newUpperLeft, paddleShape.getWidth(), paddleShape.getHeight());
            }
        }
    }

    /**
     * This method will notify and moves the paddle when needed.
     */
    public void timePassed() {
        moveLeft();
        moveRight();
    }
    /**
     * This method will draw the paddle when needed.
     *
     * @param d the surface needed to draw on the screen.
     */
    public void drawOn(DrawSurface d) {
        int upperXValue = (int) paddleShape.getUpperLine().start().getX();
        int upperYValue = (int) paddleShape.getUpperLine().start().getY();
        int width = (int) paddleShape.getWidth();
        int height = (int) paddleShape.getHeight();
        d.setColor(Color.ORANGE);
        d.fillRectangle(upperXValue, upperYValue, width, height);
        d.setColor(Color.BLACK);
        d.drawRectangle(upperXValue, upperYValue, width, height);
    }

    /**
     * This method is the getter for the shape of the paddle.
     *
     * @return the rectangle that forms the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return paddleShape;
    }
    /**
     * This method splits the paddle into five different
     * sections it can be hit on and will change the angle of the ball accordingly when hit.
     * this method also lets the ball escape the paddle if it got trapped in it.
     *
     *
     * @param hitter the ball hitting the paddle.
     * @param collisionPoint the point of collision with the paddle.
     * @param currentVelocity the velocity the might need to be changed.
     * @return the new and correct velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Line pointIntoLine = new Line(collisionPoint, collisionPoint);
        Velocity newCorrectVelocity = currentVelocity;
        //if the collision is not happening from the bottom of the top line
        if (paddleShape.getUpperLine().intersectionWith(pointIntoLine) != null
                && currentVelocity.getdy() > 0) {
            //check every side of the paddle to see if it has been collided with and make the angle "fun"
            if (paddleShape.getUpperLine().intersectionWith(pointIntoLine) != null
                    && (((paddleShape.getWidth() / PARTS_OF_THE_PADDLE) * 2) + paddleShape.getUpperLine().start().getX()
                    <= collisionPoint.getX())
                    && (((paddleShape.getWidth() / PARTS_OF_THE_PADDLE) * 3) + paddleShape.getUpperLine().start().getX()
                    >= collisionPoint.getX())) {
                newCorrectVelocity = new Velocity(currentVelocity.getdx(), currentVelocity.getdy() * (-1));
            }
            if (paddleShape.getUpperLine().intersectionWith(pointIntoLine) != null
                    && (((paddleShape.getWidth() / PARTS_OF_THE_PADDLE) * 3) + paddleShape.getUpperLine().start().getX()
                    <= collisionPoint.getX())
                    && (((paddleShape.getWidth() / PARTS_OF_THE_PADDLE) * 4) + paddleShape.getUpperLine().start().getX()
                    >= collisionPoint.getX())) {
                newCorrectVelocity = Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
            }
            if (paddleShape.getUpperLine().intersectionWith(pointIntoLine) != null
                    && (((paddleShape.getWidth() / PARTS_OF_THE_PADDLE) * 4) + paddleShape.getUpperLine().start().getX()
                    <= collisionPoint.getX())
                    && (((paddleShape.getWidth() / PARTS_OF_THE_PADDLE) * 5) + paddleShape.getUpperLine().start().getX()
                    >= collisionPoint.getX())) {
                newCorrectVelocity = Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
            }
            if (paddleShape.getUpperLine().intersectionWith(pointIntoLine) != null
                    && (((paddleShape.getWidth() / PARTS_OF_THE_PADDLE)) + paddleShape.getUpperLine().start().getX()
                    <= collisionPoint.getX())
                    && (((paddleShape.getWidth() / PARTS_OF_THE_PADDLE) * 2) + paddleShape.getUpperLine().start().getX()
                    >= collisionPoint.getX())) {
                newCorrectVelocity = Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
            }
            if (paddleShape.getUpperLine().intersectionWith(pointIntoLine) != null
                    && (paddleShape.getUpperLine().start().getX() <= collisionPoint.getX())
                    && (paddleShape.getWidth() / PARTS_OF_THE_PADDLE)
                    + paddleShape.getUpperLine().start().getX() >= collisionPoint.getX()) {
                newCorrectVelocity = Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
            }
        }
        if (paddleShape.getRightLine().intersectionWith(pointIntoLine) != null) {
            newCorrectVelocity = Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
        }
        if (paddleShape.getLeftLine().intersectionWith(pointIntoLine) != null) {
            newCorrectVelocity = Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
        }
        if (paddleShape.getLowerLine().intersectionWith(pointIntoLine) != null) {
            newCorrectVelocity = new Velocity(currentVelocity.getdx(), currentVelocity.getdy() * -1);
        }
        //other collisions with the paddle should not be counted
        return newCorrectVelocity;
    }

    /**
     * This method add the paddle to the game.
     *
     * @param gameLevel the holder of the spirit and environment collection that the paddle needs to be added to.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addCollidable(this);
        gameLevel.addSprite(this);
    }
}
