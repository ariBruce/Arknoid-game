//Aryeh bruce 209313907
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
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
public class GameLevel implements Animation {
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final int EDGE_BOX_WIDTH = 20;
    private static final int EDGE_BOX_HEIGHT = 45;
    private static final int BALL_START_POINT_X_AXIS = 394;
    private static final int BALL_START_POINT_Y_AXIS = 494;
    private static final int BLOCK_HEIGHT = 15;
    private static final int BALL_RADIUS = 8;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blocksInGame;
    private Counter ballsInGame;
    private Counter score;
    private Counter currentLevelNumber;
    private AnimationRunner runner;
    private boolean running;
    private biuoop.KeyboardSensor keyboard;
    private LevelInformation currentLevel;
    private BlockRemover br;
    ScoreTrackingListener scoreUpdater;
    boolean lastLevel;

    /**
     * This method represents the constructor fot the game.
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor ks, AnimationRunner ar, Counter score) {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        blocksInGame = new Counter();
        ballsInGame = new Counter();
        this.score = score;
        currentLevelNumber = new Counter();
        currentLevelNumber.increase(levelInfo.levelNumber());
        this.currentLevel = levelInfo;
        keyboard = ks;
        runner = ar;
        this.lastLevel = false;
    }

    public GameLevel(LevelInformation levelInfo, KeyboardSensor ks, AnimationRunner ar, Counter score, boolean lastLevel) {
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        blocksInGame = new Counter();
        ballsInGame = new Counter();
        this.score = score;
        currentLevelNumber = new Counter();
        currentLevelNumber.increase(levelInfo.levelNumber());
        this.currentLevel = levelInfo;
        keyboard = ks;
        runner = ar;
        this.lastLevel = lastLevel;
    }
    public boolean shouldStop() { return !this.running; }
    public void doOneFrame(DrawSurface d) {
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(new PauseScreen(), this.keyboard, "space"));
        }
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        //if statements in order to stop the game if needed
        if (blocksInGame.getValue() == 0) {
            score.increase(100);
            if (lastLevel) {
                this.runner.run(new KeyPressStoppableAnimation(new EndScreenWin(score), this.keyboard, "space"));
            }
            this.running = false;
        } else if (ballsInGame.getValue() == 0) {
            this.runner.run(new KeyPressStoppableAnimation(new EndScreenLose(score), this.keyboard, "space"));
            runner.getGui().close();
            this.running = false;
        }
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
    public BlockRemover getBr(){
        return br;
    }
    public ScoreTrackingListener getScoreUpdater(){
        return scoreUpdater;
    }
    /**
     * This method represents the getter for the counter of blocks in the game.
     *
     * @return the counter of the blocks in the game.
     */
    public Counter getBlocksInGame() {
        return blocksInGame;
    }
    /**
     * This method represents the getter for the counter of blocks in the game.
     *
     * @return the counter of the score of the game.
     */
    public Counter getScore() {
        return score;
    }
    public Counter getCurrentLevelNumber() {
        return currentLevelNumber;
    }
    public LevelInformation getCurrentLevel(){
        return currentLevel;
    }
    /**
     * This method represents the getter for the counter of balls in the game.
     *
     * @return the counter of the score of the game.
     */
    public Counter getBallsInGame() {
        return ballsInGame;
    }
    /**
     * This method will initialize the game, create all of its needed components
     * and add the as sprites, collidables of listeners as needed.
     */
    public void initialize() {
        /**if (currentLevelNumber.getValue() == 1) {
            this.currentLevel = new Level1(this);
        } else if (currentLevelNumber.getValue() == 2) {
            this.currentLevel = new Level2(this);
        } else if (currentLevelNumber.getValue() == 3){
            this.currentLevel = new Level3(this);
        } else if (currentLevelNumber.getValue() == 4) {
            this.currentLevel = new Level4(this);
        }
        sprites = new SpriteCollection();
        environment = new GameEnvironment();
        blocksInGame = new Counter();
        ballsInGame = new Counter();
        this.runner = new AnimationRunner(new GUI("Arkanoid", FRAME_WIDTH, FRAME_HEIGHT));
        this.keyboard =  runner.getGui().getKeyboardSensor();*/
        sprites.addSprite(currentLevel.getBackground());
        Paddle player = new Paddle(new Rectangle(new Point((double) FRAME_WIDTH / 2
                - (double)currentLevel.paddleWidth() / 2, FRAME_HEIGHT - EDGE_BOX_HEIGHT - BLOCK_HEIGHT)
                , currentLevel.paddleWidth(), BLOCK_HEIGHT),
               currentLevel.paddleSpeed(),keyboard);
        player.addToGame(this);
        initializeBlocks();
        initializeBalls();
        ScoreIndicator presentScore = new ScoreIndicator(this);
        sprites.addSprite(presentScore);
        LevelName presentLevelName = new LevelName(this);
        sprites.addSprite(presentLevelName);
    }
    /**
     * This method will initialize the balls of the game.
     */
    private void initializeBalls() {
        BallRemover ballr = new BallRemover(this);
        Ball []ballArray = new Ball[currentLevel.numberOfBalls()];
        for (int i = 0; i < currentLevel.numberOfBalls(); i++) {
            ballArray[i] = new Ball(BALL_START_POINT_X_AXIS, BALL_START_POINT_Y_AXIS, BALL_RADIUS, Color.BLUE, ballr);
            ballArray[i].setGameEnvironment(environment);
            ballArray[i].setVelocity(currentLevel.initialBallVelocities().get(i));
            ballArray[i].addToGame(this);
            ballsInGame.increase(1);
        }
    }
    /**
     * This method will initialize the blocks of the game.
     */
    private void initializeBlocks() {
        br = new BlockRemover(this, blocksInGame);
        scoreUpdater = new ScoreTrackingListener(score);
        Block bRight = new Block(new Rectangle(new Point(FRAME_WIDTH - EDGE_BOX_WIDTH, 0),
                EDGE_BOX_WIDTH, FRAME_HEIGHT), br,  scoreUpdater);
        bRight.setColor(Color.GRAY);
        bRight.addToGame(this);
        for (int i = 0; i < currentLevel.numberOfBlocksToRemove(); i++) {
            Block block = new Block(currentLevel.blocks().get(i).getCollisionRectangle(),br, scoreUpdater);
            block.setColor(currentLevel.blocks().get(i).getColor());
            block.addToGame(this);
            blocksInGame.increase(1);
        }
        Block bLeft = new Block(new Rectangle(new Point(0, 0), EDGE_BOX_WIDTH, FRAME_HEIGHT), br, scoreUpdater);
        bLeft.setColor(Color.GRAY);
        Block bTop = new Block(new Rectangle(new Point(0, 0), FRAME_WIDTH, EDGE_BOX_HEIGHT), br, scoreUpdater);
        bTop.setColor(Color.GRAY);
        //initialize a dead-zone block to remove the balls
        Block deadZone = new Block(new Rectangle(new Point(0, FRAME_HEIGHT - EDGE_BOX_WIDTH),
                FRAME_WIDTH, EDGE_BOX_HEIGHT), br, scoreUpdater);
        bTop.addToGame(this);
        bLeft.addToGame(this);
        deadZone.addToGame(this);
    }
    /**
     * This method will run the game using the proper animation loops and interfaces.
     */
        public void run() {
            this.running = true;
            // use our runner to run the current animation -- which is one turn of
            // the game.
            this.runner.run(this);
        }
    /**
     * This method remove a colidable from the game.
     *
     * @param c the colidable tho be removed.
     */
    public void removeCollidable(Collidable c) {
            environment.getObjectsOnScreen().remove(c);
    }
    /**
     * This method remove a sprite from the game.
     *
     * @param s the sprite to be removed.
     */
    public void removeSprite(Sprite s) {
            sprites.getSpriteCollection().remove(s);
    }
}