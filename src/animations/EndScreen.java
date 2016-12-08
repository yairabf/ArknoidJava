package animations;

import biuoop.DrawSurface;

import java.awt.Color;


/**
 * a class that creates the end screen.
 */
public class EndScreen implements Animation {
    private String message;
    private String message2;

    /**
     * constructor.
     *
     * @param s1 the first message.
     * @param s2 the second message.
     */
    public EndScreen(String s1, String s2) {
        this.message = s1;
        this.message2 = s2;
    }

    /**
     * draws a pause message while paused.
     *
     * @param d  the draw surface to draw on.
     * @param dt the game time counter.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        //the background
        d.setColor(new Color(0xAE417D));
        d.fillRectangle(0, 0, 800, 600);
        //the shadow for the text
        d.setColor(Color.blue);
        d.drawText(8, d.getHeight() / 2, this.message, 50);
        d.drawText(8, d.getHeight() / 2 + 150, this.message2, 50);
        //the text
        d.setColor(Color.black);
        d.drawText(10, d.getHeight() / 2, this.message, 50);
        d.drawText(10, d.getHeight() / 2 + 150, this.message2, 50);
    }

    /**
     * determines whether the animation of pause should stop or not.
     *
     * @return true if it should stop, otherwise false.
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}
