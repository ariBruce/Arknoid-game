import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Level3 implements LevelInformation {
    private Background background;

    public Level3() {
        background = new Background(Color.GREEN);
    }

    @Override
    public int numberOfBalls() {
        return 2;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        java.util.List<Velocity> velocityList = new ArrayList<Velocity>();
        for (int i = 0; i < numberOfBalls(); i++) {
            velocityList.add(Velocity.fromAngleAndSpeed(315 + i * 90,2));
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
        return "Level 3";
    }

    @Override
    public Sprite getBackground() {
        return background;
    }

    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<Block>();
        for (int i = 0; i < 6; i++) {
            Random rand = new Random();
            Color randomColor;
            do {
                float redBase = rand.nextFloat(); //create red base for color
                float greenBase = rand.nextFloat(); //create green base for color
                float blueBase = rand.nextFloat(); //create blue base for color
                randomColor = new Color(redBase, greenBase, blueBase); //mix all bases to receive a color
            } while (randomColor == Color.GREEN);
            for (int j = 10 - i; j > 0; j--) {
                Block block = new Block(new Rectangle(new Point(780 - (50 * j), 125 + (25 * i)),
                        50, 25));
                block.setColor(randomColor);
                blockList.add(block);
            }
        }
        return blockList;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }

    @Override
    public int levelNumber() {
        return 3;
    }
}
