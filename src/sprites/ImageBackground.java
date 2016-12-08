package sprites;

import biuoop.DrawSurface;
import gameopperating.GameLevel;

import java.awt.*;

/**
 * Class that represent image for the background
 */
public class ImageBackground implements Sprite {
    private Image image;
    private int x;
    private int y;

    public ImageBackground (Image image, int x, int y) {
        this.image = image;
        this.x = x;
        this.y = y;
    }
    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage(this.x, this.y, this.image);
    }

    @Override
    public void timePassed(double dt) {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
