import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Level1 implements LevelInformation{
    private Background background;

    public Level1() {
        background = new Background(Color.black);
    }
    @Override
    public int numberOfBalls() {
        return 1;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocityList = new ArrayList<Velocity>();
        for (int i = 0; i < numberOfBalls(); i++) {
            velocityList.add(new Velocity(0, -3));
        }
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 125;
    }

    @Override
    public String levelName() {
        return "Level 1";
    }

    @Override
    public Sprite getBackground() {
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        Block block = new Block(new Rectangle(new Point(380,90),30,30));
        block.setColor(Color.RED);
        blockList.add(block);
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    @Override
    public int levelNumber() {
        return 1;
    }
}
