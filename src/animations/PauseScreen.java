package animations;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * a class that pauses the game.
 */
public class PauseScreen implements Animation {
    /**
     * constructor.
     */
    public PauseScreen() {
    }

    /**
     * draws a pause message while paused.
     *
     * @param d the draw surface to draw on.
     * @param dt the game time counter.
     */
    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        //the background
        d.setColor(new Color(0xAE417D));
        d.fillRectangle(0, 0, 800, 600);
        //the outside circle
        d.setColor(Color.BLACK);
        d.fillCircle(400, 150, 150);
        //the inner circle
        d.setColor(new Color(0x72FF56));
        d.fillCircle(400, 150, 125);
        //the small circle
        d.setColor(Color.blue);
        d.fillCircle(400, 150, 100);
        //the squares
        d.setColor(Color.red);
        d.fillRectangle(330, 105, 50, 100);
        d.fillRectangle(420, 105, 50, 100);
        //the shade for the text
        d.setColor(Color.blue);
        d.drawText(318, d.getHeight() / 2 + 100, "paused", 50);
        d.drawText(168, d.getHeight() / 2 + 150, "press space to continue", 50);
        //the text
        d.setColor(Color.black);
        d.drawText(320, d.getHeight() / 2 + 100, "paused", 50);
        d.drawText(170, d.getHeight() / 2 + 150, "press space to continue", 50);

    }

    /**
     * determines whether the animation of pause should dtop or not.
     *
     * @return true if it should stop, otherwise false.
     */
    @Override
    public boolean shouldStop() {
        return false;
    }
}

