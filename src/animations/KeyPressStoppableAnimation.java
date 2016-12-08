package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Class that represent a object that charge of the continuance of animations that their ending depends on some key
 * pressing.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * @param sensor    the keyboard.
     * @param key       the kay that resume the animation.
     * @param animation the animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
    }

    /**
     * @param d  a given surface to run on.
     * @param dt the time that takes to do the operation.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        if (this.isAlreadyPressed) {
            this.animation.doOneFrame(d, dt);
        } else {
            this.isAlreadyPressed = false;
        }
        if (this.sensor.isPressed(this.key)) {
            this.stop = true;
        }
    }


    /**
     * the method tells us if the animation should stop.
     *
     * @return true false.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }


}
