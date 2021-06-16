import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

public class EndScreenWin implements Animation {
    private boolean stop;
    private Counter score;

    public EndScreenWin(Counter score) {
        this.stop = false;
        this.score = score;
    }
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2,
                "You Win! Your score is " + score.getValue(), 32);
    }
    public boolean shouldStop() { return this.stop; }
}
