import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Level2 implements LevelInformation{
    private Background background;

    public Level2() {
        background = new Background(Color.PINK);
    }
    @Override
    public int numberOfBalls() {
        return 10;
    }

    @Override
    public java.util.List<Velocity> initialBallVelocities() {
        java.util.List<Velocity> velocityList = new ArrayList<Velocity>();
        for (int i = 0; i < numberOfBalls(); i++) {
            velocityList.add(Velocity.fromAngleAndSpeed(305 + i * 12,2));
        }
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 500;
    }

    @Override
    public String levelName() {
        return "Level 2";
    }

    @Override
    public Sprite getBackground() {
        return background;
    }

    @Override
    public java.util.List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        for (int i = 0; i < numberOfBlocksToRemove(); i++) {
            Block block = new Block(new Rectangle(new Point(20 + (50*i), 200), 60, 20));
            block.setColor(Color.GREEN);
            blockList.add(block);
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 15;
    }

    @Override
    public int levelNumber() {
        return 2;
    }
}
