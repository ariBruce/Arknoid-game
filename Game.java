//Aryeh bruce
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * @author Aryeh Bruce
 * date - 25.04.2021
 * <p>
 * This class represents the holder of the full game and all of its colidebals and sprite's.
 * from here the loop animation will be activated and the game will be initilized.
 * </p>
 */
public class Game {
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final int EDGE_BOX_WIDTH = 30;
    private static final int EDGE_BOX_HEIGHT = 30;
    private static final int BOX_WIDTH = 50;
    private static final int PADDLE_BOX_WIDTH = 100;
    private static final int BALL_START_POINT_X_AXIS = 50;
    private static final int BALL_START_POINT_Y_AXIS = 50;
    private static final int BLOCK_HEIGHT = 15;
    private static final int UPPER_LINE_OF_BLOCKS = 100;
    private static final int BALL_RADIUS = 8;
    private static final int BALL_SPEED = 6;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;

    /**
     * This method represents the constructor fot the game.
     */
    public Game() {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
    }

    /**
     * This method will allow us to add a collidable to the game.
     *
     * @param c the collidable to be added.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }
    /**
     * This method will allow us to add a sprite to the game.
     *
     * @param s the sprite to be added.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }
    /**
     * This method represents the getter for the sprites of the game.
     *
     * @return the sprite collection of the game.
     */
    public SpriteCollection getsprites() {
        return sprites;
    }
    /**
     * This method represents the getter for the collidables of the game.
     *
     * @return the collidables collection of the game.
     */
    public GameEnvironment getenvironment() {
        return environment;
    }
    /**
     * This method will initialize the game and create all of its needed components
     * and add the as sprites or collidables as needed.
     */
    public void initialize() {
        gui = new GUI("SimpleBlockTester", FRAME_WIDTH, FRAME_HEIGHT);
        Paddle player = new Paddle(gui, new Rectangle(new Point((double) FRAME_WIDTH / 2,
                FRAME_HEIGHT - EDGE_BOX_HEIGHT - BLOCK_HEIGHT), PADDLE_BOX_WIDTH, BLOCK_HEIGHT));
        player.addToGame(this);
        Ball ball = new Ball(BALL_START_POINT_X_AXIS, BALL_START_POINT_Y_AXIS, BALL_RADIUS, Color.BLUE);
        ball.setGameEnvironment(environment);
        ball.setVelocity(Velocity.fromAngleAndSpeed(170, BALL_SPEED));
        ball.addToGame(this);
        Ball ball2 = new Ball(BALL_START_POINT_X_AXIS, BALL_START_POINT_Y_AXIS, BALL_RADIUS, Color.PINK);
        ball2.setGameEnvironment(environment);
        ball2.setVelocity(Velocity.fromAngleAndSpeed(190, BALL_SPEED));
        ball2.addToGame(this);
        for (int i = 0; i < 6; i++) {
            Random rand = new Random();
            float redBase = rand.nextFloat(); //create red base for color
            float greenBase = rand.nextFloat(); //create green base for color
            float blueBase = rand.nextFloat(); //create blue base for color
            Color randomColor = new Color(redBase, greenBase, blueBase); //mix all bases to receive a color
            for (int j = 12 - i; j > 0; j--) {
                Block block = new Block(new Rectangle(new Point(FRAME_WIDTH - EDGE_BOX_WIDTH
                        - (BOX_WIDTH * j), UPPER_LINE_OF_BLOCKS + (BLOCK_HEIGHT * i)), BOX_WIDTH, BLOCK_HEIGHT));
                block.setColor(randomColor);
                block.addToGame(this);
            }
        }
        Block bRight = new Block(new Rectangle(new Point(FRAME_WIDTH - EDGE_BOX_WIDTH, 0),
                EDGE_BOX_WIDTH, FRAME_HEIGHT));
        bRight.setColor(Color.GRAY);
        Block bLeft = new Block(new Rectangle(new Point(0, 0), EDGE_BOX_WIDTH, FRAME_HEIGHT));
        bLeft.setColor(Color.GRAY);
        Block bTop = new Block(new Rectangle(new Point(0, 0), FRAME_WIDTH, EDGE_BOX_HEIGHT));
        bTop.setColor(Color.GRAY);
        Block bBot = new Block(new Rectangle(new Point(0, FRAME_HEIGHT - EDGE_BOX_WIDTH),
                FRAME_WIDTH, EDGE_BOX_HEIGHT));
        bBot.setColor(Color.GRAY);
        bBot.addToGame(this);
        bTop.addToGame(this);
        bLeft.addToGame(this);
        bRight.addToGame(this);
    }


    /**
     * This method will run the game using the proper animation loops and interfaces.
     */
        public void run() {
            int framesPerSecond = 60;
            int millisecondsPerFrame = 1000 / framesPerSecond;
            Sleeper sleeper = new Sleeper();
            while (true) {
                long startTime = System.currentTimeMillis(); // timing
                DrawSurface d = gui.getDrawSurface();
                this.sprites.drawAllOn(d);
                gui.show(d);
                this.sprites.notifyAllTimePassed();
                // timing
                long usedTime = System.currentTimeMillis() - startTime;
                long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
                if (milliSecondLeftToSleep > 0) {
                    sleeper.sleepFor(milliSecondLeftToSleep);
                }
            }
        }

}
