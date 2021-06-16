import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level4 implements LevelInformation {
    private Background background;

    public Level4() {
        background = new Background(Color.cyan);
    }
    @Override
    public int numberOfBalls() {
        return 3;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        java.util.List<Velocity> velocityList = new ArrayList<Velocity>();
        for (int i = 0; i < numberOfBalls(); i++) {
            velocityList.add(Velocity.fromAngleAndSpeed(315 + i * 45,2));
        }
        return velocityList;
    }

    @Override
    public int paddleSpeed() {
        return 5;
    }

    @Override
    public int paddleWidth() {
        return 85;
    }

    @Override
    public String levelName() {
        return "Level 4";
    }

    @Override
    public Sprite getBackground() {
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        for (int i = 0; i < 7; i++) {
            Random rand = new Random();
            Color randomColor;
            do {
                float redBase = rand.nextFloat(); //create red base for color
                float greenBase = rand.nextFloat(); //create green base for color
                float blueBase = rand.nextFloat(); //create blue base for color
                randomColor = new Color(redBase, greenBase, blueBase); //mix all bases to receive a color
            } while (randomColor == Color.GREEN);
            for (int j = 0; j < 10; j++) {
                Block block = new Block(new Rectangle(new Point(20 + (76 * j), 100 + (22 * i)), 76, 22));
                block.setColor(randomColor);
                blockList.add(block);
            }
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 70;
    }

    @Override
    public int levelNumber() {
        return 4;
    }
}
