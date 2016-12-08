package geometricshapes;

import gameopperating.GameLevel;
import sprites.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.Random;

/**
 * a circle as a sprite. draws a circle on the surface.
 */
public class Circle implements Sprite {
    private Color color;
    private int radius;
    private int x;
    private int y;

    /**
     * constructor.
     * @param color the color of the circle.
     * @param x the x of the center point.
     * @param y the y of the center point.
     * @param r the radius of the circle.
     */
    public Circle(Color color, int x, int y, int r) {
        this.color = color;
        this.radius = r;
        this.x = x;
        this.y = y;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d the draw surface to draw on.
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.drawCircle(this.x, this.y, this.radius);
    }

    /**
     * draws a filled circle.
     *
     * @param d the surface to draw on.
     */
    public void fillCircle(DrawSurface d) {
        d.setColor(this.color);
        d.fillCircle(this.x, this.y, this.radius);
    }

    /**
     * notify the sprite that time has passed.
     * @param dt the game time counter.
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

    /**
     * setting a random color for the circle.
     */
    public void setRandomColor() {
        Random rand = new Random();
        this.color = new Color(rand.nextInt(250), rand.nextInt(250), rand.nextInt(250));
    }
}

