import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * this class is an animation decorator that stops animations.
 *
 */
public class KeyPressStoppableAnimation implements Animation {
    private Animation animation;
    private KeyboardSensor keyboard;
    private String endKey;
    private boolean stop;
    private boolean isAlreadyPressed;
    /**
     * constructs a Key Press Stoppable Animation from a given
     * animation, keyboard sensor and an end key.
     * @param animation the given animation.
     * @param keyboardSensor the given keyboard sensor.
     * @param endKey the key that stops the given animation.
     */
    public KeyPressStoppableAnimation(Animation animation,
                                      KeyboardSensor keyboardSensor, String endKey) {
        this.animation = animation;
        this.keyboard = keyboardSensor;
        this.endKey = endKey;
        this.stop = false;
        this.isAlreadyPressed = true;
    }
    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.keyboard.isPressed(endKey)
                && !this.isAlreadyPressed) {
            this.stop = true;
        }
        if (!this.keyboard.isPressed(endKey)) {
            this.isAlreadyPressed = false;
        }
    }

    /**
     * this method tells if the animation drawing should stop.
     * @return true if the animation drawing should stop, false otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}