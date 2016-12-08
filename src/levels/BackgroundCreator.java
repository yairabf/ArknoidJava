package levels;

import gameopperating.GameLevel;
import sprites.Sprite;
import sprites.SpriteCollection;
import biuoop.DrawSurface;

/**
 * a class that creates a background for the game.
 */
public class BackgroundCreator implements Sprite {
    private SpriteCollection spriteCollection;

    /**
     * constructor.
     * @param spriteCollection is the collection to use for creation.
     */
    public BackgroundCreator(SpriteCollection spriteCollection) {
        this.spriteCollection = spriteCollection;
    }

    /**
     * getter.
     * @return the sprite collection.
     */
    public SpriteCollection getSpriteCollection() {
        return this.spriteCollection;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d the draw surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        //d.setColor(this.backgroundColor);
        //d.fillRectangle(20, 40, 760, 580);
        this.spriteCollection.drawAllOn(d);
    }

    /**
     * notify the sprite that time has passed.
     * @param dt the dt.
     */
    @Override
    public void timePassed(double dt) {
    }

    /**
     * add a sprite into game object depends on his feature.
     *
     * @param g the game to add the sprite.
     */
    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}