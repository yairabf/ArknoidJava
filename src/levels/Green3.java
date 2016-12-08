package levels;

import ballattributes.Ball;
import ballattributes.Velocity;
import collidables.Block;
import geometricshapes.Point;
import sprites.Sprite;
import sprites.SpriteCollection;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * the third level of the game.
 */
public class Green3 implements LevelInformation {

    /**
     * @return the number of balls.
     */
    @Override
    public int numberOfBalls() {
        return 2;
    }

    /**
     * The initial velocity of each ball.
     *
     * @return a list of velocities for the balls.
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        List<Velocity> velocities = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Velocity v = Velocity.fromAngleAndSpeed(290 + (i * 10), 300);
            velocities.add(v);
        }
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
        return 150;
    }

    /**
     * the name of the level.
     *
     * @return the name of the level.
     */
    @Override
    public String levelName() {
        return "Green3";
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
        List<Block> blocks = new ArrayList<>();
        int counter = 1;
        for (int i = 10; i >= 6; i--) {
            for (int j = 0; j < i; j++) {
                Block block = new Block(730 - (j * 50), 120 + counter * 20, 50, 20, Color.white, Math.abs(counter - 6));
                block.setRandomColor();
                blocks.add(block);
            }
            counter++;
        }
        return blocks;
    }

    /**
     * the number of the blocks to be removed in order to finish the level.
     *
     * @return a number of blocks.
     */
    @Override
    public int numberOfBlocksToRemove() {
        return 40;
    }

    /**
     * creates a sprite collection for the background.
     *
     * @return a collection of sprites.
     */
    private SpriteCollection createSpritesForBackground() {
        //creates the background
        SpriteCollection sprites = new SpriteCollection();
        sprites.addSprite(new Block(20, 40, 760, 580, new Color(16, 98, 10), -1));
        //creates the tower of the drawing
        Block baseBlock = new Block(60, 450, 100, 150, new Color(0x282828), -1);
        sprites.addSprite(baseBlock);
        Block secondBlock = new Block(85, 370, 50, 80, new Color(0x484A46), -1);
        sprites.addSprite(secondBlock);
        Block pole = new Block(105, 170, 10, 200, Color.GRAY, -1);
        sprites.addSprite(pole);
        //creates the ball on top of the pole
        for (int i = 3; i >= 1; i--) {
            int shadeOfRed = 255 - ((i - 1) * 80);
            Ball b = new Ball(new Point(110, 155), i * 5, new Color(255, shadeOfRed, shadeOfRed));
            sprites.addSprite(b);
        }
        //creates the "windows inside the tower.
        for (int i = 0; i < 4; i++) {
            Point startOfRow = new Point(65, 455 + i * 30 + i * 5);
            this.createRowOfBlocks(sprites, 5, 14, 30, startOfRow);
        }
        return sprites;
    }

    /**
     * a method to create a row of blocks.
     *
     * @param sprites the sprites to add all the blocks to.
     * @param amount  the amount of blocks.
     * @param width   the width of every block.
     * @param height  the height of every block.
     * @param start   the stating point of every row.
     */
    private void createRowOfBlocks(SpriteCollection sprites, int amount, int width, int height, Point start) {
        for (int i = 0; i < amount; i++) {
            Block b = new Block(start.getX() + (i * width) + (i * 5), start.getY(), width, height, Color.BLACK, -1);
            b.setRandomColor();
            sprites.addSprite(b);
        }
    }
}
