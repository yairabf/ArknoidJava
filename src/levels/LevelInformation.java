package levels;

import ballattributes.Velocity;
import collidables.Block;
import sprites.Sprite;

import java.util.List;

/**
 * an interface giving information for a level of the game.
 */
public interface LevelInformation {
    /**
     * @return the number of balls.
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     *
     * @return a list of velocities for the balls.
     */
    List<Velocity> initialBallVelocities();

    /**
     * returns the speed of the paddle.
     *
     * @return the speed of the paddle.
     */
    int paddleSpeed();

    /**
     * returns the paddle width.
     *
     * @return the paddle width.
     */
    int paddleWidth();

    /**
     * the name of the level.
     *
     * @return the name of the level.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return a sprite representing the background.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return a list of Blocks.
     */
    List<Block> blocks();

    /**
     * the number of the blocks to be removed in order to finish the level.
     *
     * @return a number of blocks.
     */
    int numberOfBlocksToRemove();
}
