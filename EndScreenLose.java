import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class EndScreenLose implements Animation {
    private boolean stop;
    private Counter score;
    public EndScreenLose(Counter score) {
        this.stop = false;
        this.score = score;
    }
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2,
                "Game Over. Your score is " + score.getValue(), 32);
    }
    public boolean shouldStop() { return this.stop; }
}
