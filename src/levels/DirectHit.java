package levels;

import ballattributes.Velocity;
import collidables.Block;
import geometricshapes.Circle;
import geometricshapes.Line;
import geometricshapes.Point;
import sprites.Sprite;
import sprites.SpriteCollection;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * the first level of the game.
 */
public class DirectHit implements LevelInformation {
    private Point centerOfDrawing = new Point(400, 150);

    /**
     * @return the number of balls.
     */
    @Override
    public int numberOfBalls() {
        return 1;
    }

    /**
     * The initial velocity of each ball.
     *
     * @return a list of velocities for the balls.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        velocities.add(Velocity.fromAngleAndSpeed(270, 300));
        return velocities;
    }

    /**
     * returns the speed of the paddle.
     *
     * @return the speed of the paddle.
     */
    @Override
    public int paddleSpeed() {
        return 200;
    }

    /**
     * returns the paddle width.
     *
     * @return the paddle width.
     */
    @Override
    public int paddleWidth() {
        return 200;
    }

    /**
     * the name of the level.
     *
     * @return the name of the level.
     */
    @Override
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return a sprite representing the background.
     */
    @Override
    public Sprite getBackground() {
        return new BackgroundCreator(this.createSpritesForBackground());
    }

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return a list of Blocks.
     */
    @Override
    public List<Block> blocks() {
        List<Block> blockList = new ArrayList<>();
        blockList.add(new Block(this.centerOfDrawing.getX() - 25, this.centerOfDrawing.getY() - 25,
                50, 50, Color.red, 1));
        return blockList;
    }

    /**
     * the number of the blocks to be removed in order to finish the level.
     *
     * @return a number of blocks.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 1;
    }

    /**
     * creates a sprite collection for the background.
     *
     * @return a collection of sprites.
     */
    private SpriteCollection createSpritesForBackground() {
        //creating the three circles
        SpriteCollection spriteCollection = new SpriteCollection();
        spriteCollection.addSprite(new Block(20, 40, 760, 580, Color.black, -1));
        for (int i = 0; i < 3; i++) {
            Circle circle = new Circle(Color.blue, (int) this.centerOfDrawing.getX(), (int) this.centerOfDrawing.getY(),
                    (i * 20) + 70);
            spriteCollection.addSprite(circle);
        }
        //creating the lines
        //the right line
        Line line1 = new Line(this.centerOfDrawing.getX() + 27, this.centerOfDrawing.getY(),
                this.centerOfDrawing.getX() + 127, this.centerOfDrawing.getY());
        line1.setColor(Color.blue);
        spriteCollection.addSprite(line1);
        //the bottom line
        Line line2 = new Line(this.centerOfDrawing.getX(), this.centerOfDrawing.getY() + 27,
                this.centerOfDrawing.getX(), this.centerOfDrawing.getY() + 127);
        line2.setColor(Color.blue);
        spriteCollection.addSprite(line2);
        //the left line
        Line line3 = new Line(this.centerOfDrawing.getX() - 27, this.centerOfDrawing.getY(),
                this.centerOfDrawing.getX() - 127, this.centerOfDrawing.getY());
        line3.setColor(Color.blue);
        spriteCollection.addSprite(line3);
        //the top line
        Line line4 = new Line(this.centerOfDrawing.getX(), this.centerOfDrawing.getY() - 27,
                this.centerOfDrawing.getX(), this.centerOfDrawing.getY() - 127);
        line4.setColor(Color.blue);
        spriteCollection.addSprite(line4);
        return spriteCollection;
    }
}